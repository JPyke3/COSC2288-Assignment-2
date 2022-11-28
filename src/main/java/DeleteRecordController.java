package main.java;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DeleteRecordController {

    // Private class variables
    private Stage stage;
    private List<Record> healthRecords;
    private Record removedRecord;

    // Constructor
    public DeleteRecordController(List<Record> healthRecords) {
        this.healthRecords = healthRecords;
    }

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private TableColumn<Record, String> bloodPressureColumn;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Record, String> dateColumn;

    @FXML
    private TableView<Record> recordTableView;

    @FXML
    private TableColumn<Record, String> tempColumn;

    @FXML
    private TableColumn<Record, String> weightColumn;

    @FXML
    private TableColumn<Record, String> noteColumn;

    @FXML
    private Button deleteButton;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {

        // Create observable that can be consumed via the tableview
        ObservableList<Record> recordObservableList = FXCollections.observableArrayList(healthRecords);

        // Create the columns for the tableview
        weightColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("weight"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("recordDate"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("temperature"));
        bloodPressureColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("bloodPressure"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("note"));

        // Assign the items to the tableview
        recordTableView.setItems(recordObservableList);

        // Close button, just closes the stage
        closeButton.setOnAction(event -> {
            this.stage.close();
        });

        // Delete button
        // Opens a confirmation dialogue, if the user agrees then remove the record from
        // the local DB and the SQLite DB
        deleteButton.setOnAction(event -> {
            try {
                // Load the next screen Delete Record View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecordConfirmationView.fxml"));

                // Initialise the controller for the view
                DeleteRecordConfirmationController deleteRecordConfirmationController = new DeleteRecordConfirmationController();

                // Assign the FXML to the controller
                loader.setController(deleteRecordConfirmationController);

                // Load the FXML and assign the result to a variable
                Parent deleteRecordConfirmationViewFXML = loader.load();

                // Create new scene with the FXML
                Scene deleteRecordConfirmationWindow = new Scene(deleteRecordConfirmationViewFXML);

                // Create new stage
                Stage stage = new Stage();

                // Set the stage for the controller
                deleteRecordConfirmationController.setStage(stage);

                // Assign the scene ot the stage
                stage.setScene(deleteRecordConfirmationWindow);

                // Add the title for the window
                stage.setTitle("Edit Record");

                // Show the stage and wait for user input
                stage.showAndWait();

                // If the user has opted to delete the record
                if (deleteRecordConfirmationController.shouldDeleteRecord()) {

                    // Assign the removed record to a variable
                    removedRecord = healthRecords.get(recordTableView.getSelectionModel().getSelectedIndex());
                }

                // Reassign the tableview to refresh the items
                recordTableView.setItems(recordObservableList);

                // Close the stage
                this.stage.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    // Getters
    public Record getRemovedRecord() {
        return removedRecord;
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
