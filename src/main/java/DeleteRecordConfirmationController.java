package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteRecordConfirmationController {
    private Stage stage;
    private Boolean delete;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        deleteButton.setOnAction(event -> {
            this.delete = true;
            this.stage.close();
        });

        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Boolean shouldDeleteRecord() {
        return this.delete;
    }
}
