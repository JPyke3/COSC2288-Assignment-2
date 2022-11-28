package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
    // Class Variables
    private Stage stage;
    private Boolean newUserCreated = false;
    private String createdUserName;
    private String createdUserPassword;
    private String createdUserFName;
    private String createdUserLName;

    // Getters
    public String getCreatedUserName() {
        return createdUserName;
    }

    public String getCreatedUserPassword() {
        return createdUserPassword;
    }

    public String getCreatedUserFName() {
        return createdUserFName;
    }

    public String getCreatedUserLName() {
        return createdUserLName;
    }

    // FXML Variables
    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label confirmationField;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        createAccountButton.setOnAction(event -> {
            // Assign the new user's details to private variables
            createdUserName = usernameField.getText();
            createdUserPassword = passwordField.getText();
            createdUserFName = firstNameField.getText();
            createdUserLName = lastNameField.getText();
            // Set a boolean so the previous screen knows the new user is created
            this.newUserCreated = true;
            // Close the stage
            this.stage.close();
        });

        // When the cancel button is clicked, just close the stage without doing
        // anything
        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    // Method so that the previous screen can check if the new user was created
    public Boolean wasUserCreated() {
        return newUserCreated;
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
