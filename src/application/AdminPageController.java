package application;

import Objects.Admin;
import Objects.AdminBank;
import Objects.Toner;
import Objects.TonerAlert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public static Parent parent;

    public static void setParent(Parent parent) {
        AdminPageController.parent = parent;
    }


    @FXML TextField adminName;
    @FXML TextField adminPassword;
    @FXML TextField adminEmail;
    @FXML TextField idNumber;
    @FXML Button addAdmin;

    @FXML TextField adminToRemove;
    @FXML Button removeAdmin;

    @FXML RadioButton emailAlertRadio;
    @FXML CheckBox loggingCheckBox;
    @FXML Button testEmailSystem;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loggingCheckBox.setSelected(false);
        loggingCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(loggingCheckBox.isSelected()) {
                    showLoggingAlert();
                }
            }
        });

        addAdmin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                AdminBank adminBank= getAdminBank();

                Admin newAdmin;

                try {
                    String name = adminName.getText();
                    String password = adminPassword.getText();
                    String email = adminEmail.getText();
                    Integer id = Integer.parseInt(idNumber.getText());

                    newAdmin = new Admin(name, email, password, id);

                    if(adminBank.add(newAdmin)) {

                        showAlertAdminSuccessAdd();
                    }


                    adminBank.toAdminFile();
                    System.out.println("Added admin");

                    adminName.clear();
                    adminEmail.clear();
                    adminPassword.clear();
                    idNumber.clear();

                } catch (Exception e) {
                    //TODO: Need alert here
                }


            }
        });

        removeAdmin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                AdminBank adminBank = getAdminBank();

                Admin admin2Remove = new Admin(adminToRemove.getText());

                if(adminBank.contains(admin2Remove) != null) {

                    if(adminBank.remove(admin2Remove)) {
                        showAlertAdminSuccessRemove(admin2Remove);
                    }

                    try {
                        adminBank.toAdminFile();
                    } catch(IOException e) {
                        //TODO: Need alert here
                    }
                } else {
                    showAdminNotInDatabase(admin2Remove);
                }

            }
        });

        testEmailSystem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TonerAlert tonerAlert = new TonerAlert(new Toner());
                if(tonerAlert.sendEmail()) {
                    showEmailSentAlert();
                }
            }
        });


        emailAlertRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!emailAlertRadio.isSelected()) {
                   showEmailRadioAlert();
                }
            }
        });

    }

    private AdminBank getAdminBank() {

        AdminBank adminBank = new AdminBank();


        //Load admin file from admins.byt
        try {
            adminBank.loadAdminFile();
        } catch (EOFException e) {
            System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return adminBank;
    }

    /**
     * This method displays an alert popup if the Admin has been successfully added
     */
    private static void showAlertAdminSuccessAdd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Success!");
        alert.setContentText("Administrator successfully added to database.");

        alert.showAndWait();
    }

    /**
     * This method displays an alert popup if the Admin has been successfully removed
     * @param adminToRemove
     */
    private static void showAlertAdminSuccessRemove(Admin adminToRemove) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Success!");
        alert.setContentText(adminToRemove.getName() + " successfully removed from database.");

        alert.showAndWait();
    }

    /**
     * This method displays an alert popup if the admin that is being removed doesn't exist
     * @param adminToRemove
     */
    private static void showAdminNotInDatabase(Admin adminToRemove) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Warning!");
        alert.setContentText(adminToRemove.getName() + " not in database.");

        alert.showAndWait();
    }

    /**
     * This method displays an alert popup if the test email was sent
     */
    private static void showEmailSentAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Test Email");
        alert.setContentText("Test email sent successfully.");

        alert.showAndWait();
    }

    /**
     * This method displays an alert popup if system logging is enabled
     */
    private static void showLoggingAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TonerHive");
        alert.setHeaderText("System Logging");
        alert.setContentText("System logging enabled.");

        alert.showAndWait();
    }

    /**
     * This method displays an alert popup if email alerts are disabled
     */
    private static void showEmailRadioAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Email System");
        alert.setContentText("Email system disabled.");

        alert.showAndWait();
    }
}
