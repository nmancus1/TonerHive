/**
 * <h1>Start Page Controller</h1>
 * <p> This class controls the start page
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/22/18
 */

package application;

import Objects.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    //Set up static parent fields for simplifying focus transfer
    public static Parent parent;
    public static void setParent(Parent parent) {
        StartPageController.parent = parent;
    }

    //Campus context for keeping track of info
    CampusContext campusContext = CampusContext.CampusContext();

    //FXML annotations for scenebuilder
    @FXML Label label;
    @FXML TextField textField;
    @FXML Button tonerLookup;
    @FXML Button adminLogin;

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

        //Create builder objects
        TonerBank tonerBank = new TonerBank();
        PrinterBank printerBank = new PrinterBank();
        Campus campus = new Campus("Wilmington");


        //Build inventories, passing in spreadsheet files
        try {
            tonerBank = TonerBuilder.build("TonerInventory.xlsx");
            printerBank = PrinterBuilder.build("PrinterInventory.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File read error!");
        }

        //Set up campusContext
        campusContext.printerBank = printerBank;
        campusContext.tonerBank = tonerBank;
        campusContext.campus = campus;

        //Set up action for toner lookup button
        tonerLookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                //Handle asset tag input
                String assetTag = String.valueOf(textField.getCharacters());

                //Get printerbank and find correct printer model
                Printer printer = campusContext.printerBank.getPrinter(assetTag);

                //Get tonerbank and try to get compatible toner cartridges
                ArrayList compatibleTonerList = new ArrayList();

                if (printer != null) {
                    compatibleTonerList = campusContext.tonerBank.getCompatibleTonerArrayList(printer);
                }

                //If we find compatible toners
                if (compatibleTonerList != null && compatibleTonerList.size() != 0) {

                    /**
                     * Start tonerInfoPage, passing in compatible toner list
                     */

                    //Pass the compatible toner list to campus context for passing between scenes
                    campusContext.setCompatibleTonerList(compatibleTonerList);

                    //New parent node
                    Parent root;

                    //Load fxml, create new stage, show stage
                    try {
                        root = FXMLLoader.load(getClass().getResource("TonerInfoPage.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("TonerHive");
                        stage.setScene(new Scene(root));

                        //Hide this node
                        parent.getScene().getWindow().hide();

                        //Set the parent of the next node to this node
                        TonerInfoPageController.setParent(root);

                        //Show new node
                        stage.show();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    /**
                     * Popup window alerting that this tag doesn't exist
                     */

                    showAlertWithHeaderText();
                }
            }
        });

        //Set up admin login scene
        adminLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //New parent node
                Parent root;

                //Load fxml, create new stage, show stage
                try {
                    root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("TonerHive");
                    stage.setScene(new Scene(root));

                    //Hide this node
                    parent.getScene().getWindow().hide();

                    //Set the parent of the next node to this node
                    LoginController.setParent(root);

                    //Show new node
                    stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }

    /**
     * This method displays an alert popup if the requested printer is not in the database
     */
    private static void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Warning!");
        alert.setContentText("Printer not in database.\nCheck asset tag and re-enter.");

        alert.showAndWait();
    }


}
