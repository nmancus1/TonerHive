package application;

import Objects.Admin;
import Objects.AdminBank;
import Objects.CampusContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;

/**
 * Controls the login screen
 */
public class LoginController {
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    CampusContext campusContext = CampusContext.CampusContext();

    public static Parent parent;

    public static void setParent(Parent parent) {
        LoginController.parent = parent;
    }

    public void initialize() {

        userNameTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        passwordField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String sessionID = authorize();
                if (sessionID != null) {

                    //New parent node
                    Parent root;

                    //Load fxml, create new stage, show stage
                    try {
                        root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("TonerHive");
                        stage.setScene(new Scene(root));

                        //Hide this node
                        parent.getScene().getWindow().hide();

                        //Set the parent of the next node to this node
                        AdminPageController.setParent(root);

                        //Show new node
                        stage.show();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                } else {
                    showNoLoginAlert();
                }
            }

        });
    }


    /**
     * This method displays an alert popup if the login credentials are incorrect
     */
    static void showNoLoginAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Warning!");
        alert.setContentText("Incorrect login credentials.");

        alert.showAndWait();
    }


    /**
     * Check authorization credentials.
     * <p>
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private String authorize() {

        AdminBank adminBank = new AdminBank();

        try {
            adminBank.loadAdminFile();
        } catch (EOFException e) {
            System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Admin admin = adminBank.contains(new Admin(userNameTextField.getText()));

        if ((admin) != null && admin.getPassword().equals(passwordField.getText())) {
            return generateSessionID();
        }

        return null;

    }

    private static int sessionID = 0;

    private String generateSessionID() {
        sessionID++;
        return "TonerHive - session " + sessionID;
    }
}
