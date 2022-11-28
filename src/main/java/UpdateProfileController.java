package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProfileController {

    // Private Class Variables
    private Stage stage;
    private User user;

    // Constructor
    public UpdateProfileController(User user) {
        this.user = user;
    }

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private Button cancelButton;

    @FXML
    private TextField fistNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button okButton;

    @FXML
    private Label userNameLabel;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Assign the fields to the user's parameters
        fistNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        userNameLabel.setText(user.getUsername());

        // Okay Button
        okButton.setOnAction(event -> {
            // Set the private variables to the changed content
            user.setFirstName(fistNameField.getText());
            user.setLastName(lastNameField.getText());

            // Close the stage
            this.stage.close();
        });

        // Cancel button
        // Close the stage
        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    // Getter
    public User getUser() {
        return this.user;
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
