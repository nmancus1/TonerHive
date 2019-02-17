/**
 * <h1>Toner info page controller</h1>
 * Controls the toner info scene
 * <p>
 * <b>Note:</b> Solid
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/23/18
 */
package application;

import Objects.CampusContext;
import Objects.Toner;
import Objects.TonerFileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TonerInfoPageController implements Initializable {

    /**
     * Below are is how we can set the parent node of this Node, so that we can hide the previous window.
     * The static setParent() method is called from the previous controller (in this case, MainController)
     */
    public static Parent parent;

    public static void setParent(Parent parent) {
        TonerInfoPageController.parent = parent;
    }

    //Campus context allows us to transfer data between scenes
    CampusContext campusContext = CampusContext.CampusContext();

    //FXML Annotations for scenebuilder
    @FXML private TableView<Toner> tableView = new TableView<>();
    @FXML private TableColumn<Toner, String> brandColumn;
    @FXML private TableColumn<Toner, String> colorColumn;
    @FXML private TableColumn<Toner, String> modelColumn;
    @FXML private TableColumn<Toner, Integer> inStockColumn;
    @FXML private TableColumn<Toner, Integer> minimumStockColumn;



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

        //Set up cells for toner info
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        inStockColumn.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        minimumStockColumn.setCellValueFactory(new PropertyValueFactory<>("minimumStockAmount"));

        //Set list to visible
        tableView.setItems(getToners());

        //Handle mouse click on toner object
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {


                        //Get toner in question
                        Toner selectedToner = tableView.getSelectionModel().getSelectedItem();

                        if (selectedToner.getCurrentStock() < 1) {
                            showZeroStockAlert();

                        } else {

                            //Set up alert
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("TonerHive");
                            alert.setHeaderText("Confirm installation of this toner cartridge.");
                            alert.setContentText("Brand: " + selectedToner.getBrand() + "\n" +
                                    "Model: " + selectedToner.getModel() + "\n" +
                                    "Color: " + selectedToner.getColor() + "\n");

                            ButtonType okButton = new ButtonType("OK");
                            ButtonType cancelButton = new ButtonType("Cancel");
                            alert.getButtonTypes().setAll(cancelButton, okButton);
                            Optional<ButtonType> result = alert.showAndWait();

                            //Check which button on alert was pushed
                            if (result.get() == okButton) {

                                //Close alert dialog and deduct toner
                                alert.close();
                                selectedToner.deductOne();

                                //Refresh tableview after changes
                                tableView.refresh();

                                //Tryo to read file in
                                try {
                                    TonerFileWriter.write(campusContext.getTonerBank(), "TonerInventory.xlsx");
                                } catch (IOException e) {
                                    System.out.println();
                                }

                                //Set up ok dialog for login.
                                Alert okDialog = new Alert(Alert.AlertType.INFORMATION);
                                okDialog.setTitle("TonerHive");
                                okDialog.setHeaderText("One toner cartridge successfully \ndeducted from stock.");
                                okDialog.showAndWait();

                                //Check toner inventory situation
                                if (selectedToner.getCurrentStock() <= selectedToner.getMinimumStockAmount()
                                        && selectedToner.getIsOrdered() == 'n') {
                                    okDialog.setContentText("Low stock alert has been sent!");
                                }
                                okDialog.setContentText(selectedToner.getCurrentStock().toString() + " cartridges left in stock.");

                            } else if (result.get() == cancelButton) {

                                alert.close();
                            }

                        }

                    }
                }
            }
        });
    }

    /**
     * This method displays an alert popup if the requested toner is out of stock
     */
    private static void showZeroStockAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("TonerHive");
        alert.setHeaderText("Warning!");
        alert.setContentText("There are no remaining toner cartridges \nof this type in stock.");

        alert.showAndWait();
    }



    /**
     * Observable list here to return for populating the tableview
     * @return
     */
    private ObservableList<Toner> getToners() {

        ObservableList<Toner> toners = FXCollections.observableArrayList();
        ArrayList<Toner> compatibleTonerList = campusContext.getCompatibleTonerList();

        for(Toner t: compatibleTonerList) {

            toners.add(t);
        }

        return toners;


    }
}
