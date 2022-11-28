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
    private Stage stage;
    private List<Record> healthRecords;

    public EditRecordListController(List<Record> healthRecords) {
        this.healthRecords = healthRecords;
    }

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

    @FXML
    public void initialize() {
        ObservableList<Record> recordObservableList = FXCollections.observableArrayList(healthRecords);
        weightColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("weight"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("recordDate"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("temperature"));
        bloodPressureColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("bloodPressure"));

        recordTableView.setItems(recordObservableList);

        closeButton.setOnAction(event -> {
            this.stage.close();
        });

        editButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateRecordView.fxml"));
                EditRecordController editRecordController = new EditRecordController(
                        recordTableView.getSelectionModel().getSelectedItem());
                loader.setController(editRecordController);
                Parent editRecordViewFXML = loader.load();
                Scene editRecordWindow = new Scene(editRecordViewFXML);
                Stage stage = new Stage();
                editRecordController.setStage(stage);
                stage.setScene(editRecordWindow);
                stage.setTitle("Edit Record");
                stage.showAndWait();
                healthRecords.set(recordTableView.getSelectionModel().getSelectedIndex(),
                        editRecordController.getRecord());
                recordTableView.setItems(recordObservableList);
                this.stage.close();
                // if (createRecordController.wasRecordCreated()) {
                // System.out.println("Fired");
                // user.addHealthRecord(createRecordController.getCreatedRecord());
                // }
                // System.out.println();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    public List<Record> getItems() {
        return healthRecords;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
