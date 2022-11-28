package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
    private Stage stage;
    private Boolean newUserCreated = false;
    private String createdUserName;
    private String createdUserPassword;

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

    private String createdUserFName;
    private String createdUserLName;

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

    @FXML
    public void initialize() {
        createAccountButton.setOnAction(event -> {
            confirmationField.setText("Created User " + usernameField.getText());
            createdUserName = usernameField.getText();
            createdUserPassword = passwordField.getText();
            createdUserFName = firstNameField.getText();
            createdUserLName = lastNameField.getText();
            this.newUserCreated = true;
            this.stage.close();
        });

        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public Boolean wasUserCreated() {
        return newUserCreated;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
