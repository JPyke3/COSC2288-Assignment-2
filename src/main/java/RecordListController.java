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
    private Stage stage;
    private List<Record> healthRecords;

    public RecordListController(List<Record> healthRecords) {
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
    public void initialize() {
        ObservableList<Record> recordObservableList = FXCollections.observableArrayList(healthRecords);
        weightColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("weight"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("recordDate"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("temperature"));
        bloodPressureColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("bloodPressure"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("note"));

        recordTableView.setItems(recordObservableList);

        closeButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
