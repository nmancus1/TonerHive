package Objects; /**
 * <h1>TONERALERT</h1>
 * The toneralert class implements the toneralert interface
 * <p>
 * <b>Note:</b> NOTES HERE
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */

import Interfaces.TonerAlertInterface;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class TonerAlert implements TonerAlertInterface {

    private final String senderEmail = "";
    private final String senderPassword = "";
    private final String emailSMTPserver = "smtp.gmail.com";
    private final String emailServerPort = "587";

    //Toner that is instantiated upon constructing a TonerAlert
    Toner toner;

    //Default constructor
    public TonerAlert(Toner toner) {
        this.toner = toner;
    }

    /**
     * Public sendEmail method, uses private sendEmailHelper method as helper
     */
    @Override
    public boolean sendEmail() {

        boolean emailSentFlag = false;

        //Create new adminbank
        AdminBank adminBank = new AdminBank();


        try {
            adminBank.loadAdminFile();

        } catch (EOFException eof) {
            System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Get arraylist
        ArrayList<Admin> admins = adminBank.toArrayList();

        //Construct a string from toner object
        String tonerAlertString;
        tonerAlertString = String.format("Low toner supply alert!  Please reorder:\n\n" +
                        "\t\tBrand: %s,  Toner Model# %s, Color: %s\n\n" +
                        "Currently, you have %d in stock, and %d are normally ordered.  Please " +
                        "reorder as soon as possible.\n\n" +
                        "~~sent via TonerHiveBot~~",
                this.toner.getBrand(),
                this.toner.getModel(),
                this.toner.getColor(),
                this.toner.getCurrentStock(),
                this.toner.getAverageAmountOrdered());

        //Iterate through admin list, emailing each one
        for (Admin admin : admins) {

            //Call helper method to send email
            if(this.sendEmailHelper(admin.getEmail(), tonerAlertString)) {
                emailSentFlag = true;
            }
        }

        return emailSentFlag;
    }

    //Private helper method
    private boolean sendEmailHelper(String receiverEmail, String message) {

        //Set up email properties
        Properties props = new Properties();
        props.put("mail.smtp.user", senderEmail);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        //SecurityManager security = System.getSecurityManager();

        //Send email
        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);

            //Build new message
            Message msg = new MimeMessage(session);
            msg.setText(message);
            msg.setSubject("Low Toner Alert!");
            msg.setFrom(new InternetAddress(senderEmail));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmail));
            //Send!
            Transport.send(msg);
            System.out.println("Email sent successfully");
            return true;
        } catch (Exception ex) {
            System.out.println("Email not sent successfully");
            return false;
        }
    }


    //Private inner class for authentication purposes
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    }


}
