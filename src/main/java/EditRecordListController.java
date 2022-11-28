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

public class EditRecordListController {

    // Private Class Variables
    private Stage stage;
    private List<Record> healthRecords;

    // Constructor
    public EditRecordListController(List<Record> healthRecords) {
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
    private Button editButton;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Convert the health records to an observable for the TableView
        ObservableList<Record> recordObservableList = FXCollections.observableArrayList(healthRecords);

        // Construct the columns for the tableview
        weightColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("weight"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("recordDate"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("temperature"));
        bloodPressureColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("bloodPressure"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("note"));

        // Set the items of the tableview to the observable created above
        recordTableView.setItems(recordObservableList);

        // Close button, just close the stage
        closeButton.setOnAction(event -> {
            this.stage.close();
        });

        // Edit Button
        // Open a new window with the fields to edit
        editButton.setOnAction(event -> {
            try {
                // Load the next screen Create REcord View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateRecordView.fxml"));

                // Create new controller
                EditRecordController editRecordController = new EditRecordController(
                        recordTableView.getSelectionModel().getSelectedItem());

                // Assign the FXML to the controller
                loader.setController(editRecordController);

                // Load the FXML and assign the result to a variable
                Parent editRecordViewFXML = loader.load();

                // Create new scene with the FXML
                Scene editRecordWindow = new Scene(editRecordViewFXML);

                // Create new stage for the window
                Stage stage = new Stage();

                // Assign the stage to the controller
                editRecordController.setStage(stage);

                // Assign the scene to the stage
                stage.setScene(editRecordWindow);

                // Give the stage a title
                stage.setTitle("Edit Record");

                // Show the stage and wait for user input
                stage.showAndWait();

                // Assign the selected index the new record
                healthRecords.set(recordTableView.getSelectionModel().getSelectedIndex(),
                        editRecordController.getRecord());

                // Refresh the items in the tableview
                recordTableView.setItems(recordObservableList);

                // Close the stage
                this.stage.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    // Getter
    public List<Record> getItems() {
        return healthRecords;
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
