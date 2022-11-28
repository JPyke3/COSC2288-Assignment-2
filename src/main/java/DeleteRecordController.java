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
    private Stage stage;
    private List<Record> healthRecords;

    public DeleteRecordController(List<Record> healthRecords) {
        this.healthRecords = healthRecords;
    }

    private Record removedRecord;

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

        deleteButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecordConfirmationView.fxml"));
                DeleteRecordConfirmationController deleteRecordConfirmationController = new DeleteRecordConfirmationController();
                loader.setController(deleteRecordConfirmationController);
                Parent deleteRecordConfirmationViewFXML = loader.load();
                Scene deleteRecordConfirmationWindow = new Scene(deleteRecordConfirmationViewFXML);
                Stage stage = new Stage();
                deleteRecordConfirmationController.setStage(stage);
                stage.setScene(deleteRecordConfirmationWindow);
                stage.setTitle("Edit Record");
                stage.showAndWait();
                if(deleteRecordConfirmationController.shouldDeleteRecord()) {
                    removedRecord = healthRecords.get(recordTableView.getSelectionModel().getSelectedIndex());
                }
                recordTableView.setItems(recordObservableList);
                this.stage.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    public Record getRemovedRecord() {
        return removedRecord;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
