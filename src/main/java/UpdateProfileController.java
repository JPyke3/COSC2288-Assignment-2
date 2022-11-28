package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProfileController {
    private Stage stage;
    private User user;

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

    @FXML
    public void initialize() {
        fistNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        userNameLabel.setText(user.getUsername());

        okButton.setOnAction(event -> {
            user.setFirstName(fistNameField.getText());
            user.setLastName(lastNameField.getText());
            this.stage.close();
        });

        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public UpdateProfileController(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
