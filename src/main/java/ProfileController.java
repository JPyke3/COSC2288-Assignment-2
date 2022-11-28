package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {
    private Stage stage;
    private User user;

    public ProfileController(User user) {
        this.user = user;
    }

    @FXML
    private Label LastNameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label userNameLabel;

    @FXML
    public void initialize() {
        firstNameLabel.setText(user.getFirstName());
        LastNameLabel.setText(user.getLastName());
        userNameLabel.setText(user.getUsername());

        okButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
