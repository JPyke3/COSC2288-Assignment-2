package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {

    // Private Class Variables
    private Stage stage;
    private User user;

    // Constructor
    public ProfileController(User user) {
        this.user = user;
    }

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private Label LastNameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label userNameLabel;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Set the labels to the user's parameters
        firstNameLabel.setText(user.getFirstName());
        LastNameLabel.setText(user.getLastName());
        userNameLabel.setText(user.getUsername());

        // Okay Button
        // Close the stage
        okButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
