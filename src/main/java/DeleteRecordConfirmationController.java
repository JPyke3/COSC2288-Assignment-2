package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteRecordConfirmationController {

    // Private class variables
    private Stage stage;
    private Boolean delete;

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Delete button
        deleteButton.setOnAction(event -> {
            // Set the delete variable to true then close the stage
            this.delete = true;
            this.stage.close();
        });

        // Cancel button
        // Do nothing and then close the stage
        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Getter for deletion variable
    public Boolean shouldDeleteRecord() {
        return this.delete;
    }
}
