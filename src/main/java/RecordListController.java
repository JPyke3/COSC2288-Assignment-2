package main.java;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RecordListController {
    // Private class variables
    private Stage stage;
    private List<Record> healthRecords;

    // Constructor
    public RecordListController(List<Record> healthRecords) {
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

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Convert arraylist to an observable to be consumed via the table view
        ObservableList<Record> recordObservableList = FXCollections.observableArrayList(healthRecords);

        // Construct columns for the tableview using the property values
        weightColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("weight"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("recordDate"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("temperature"));
        bloodPressureColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("bloodPressure"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("note"));

        // Set the items of the table view
        recordTableView.setItems(recordObservableList);

        // Close Button
        // Close the stage without doing anything
        closeButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
