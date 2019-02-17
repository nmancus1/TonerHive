package Interfaces;
/**
 * <h1>TonerAlertInterface<h1>
 * An interface that contains TonerHive email account and methods
 * implemented in the TonerAlert class.
 * <p>
 * <b>Note:</b>
 *
 * @author Nick Mancuso
 * @since 11/16/18
 */

import javax.mail.PasswordAuthentication;


public interface TonerAlertInterface {

    String senderEmail = "";
    String senderPassword = "";
    String emailSMTPserver = "smtp.gmail.com";
    String emailServerPort = "587";
    /**
     * Public sendEmail method, uses private sendEmailHelper method as
     * helper
     */
    boolean sendEmail();

    /**
     * Private inner class for authentication purposes
     */
    class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    }
}
