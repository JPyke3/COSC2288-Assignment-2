package main.java;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/***
 * Controller class used for the login screen
 */
public class LoginController {

    private Stage stage; // Stage for this controller
    private ApplicationBackend backend; //Backend database for the application

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private Label errorLabel;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Code that is executed when user clicks  "login"
        loginButton.setOnAction(event -> {
            int index = 0; // Loop Index
            // Iterate through all users in the backend
            for (User user : backend.getDb().getUsers()) {
                // Check if the user's username and password equal the username and password of a user in the backend
                if (usernameField.getText().equals(user.getUsername())
                        && passwordField.getText().equals(user.getPassword())) {
                    try {
                        // Load the next screen UserView
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));

                        // Initialise the UserView Controller
                        UserController userController = new UserController(user, this.stage.getScene(), this.backend,
                                index);

                        // Set the controller for the FXML Loader
                        loader.setController(userController);
                        
                        
                        Parent userViewFXML = loader.load();
                        Scene userWindow = new Scene(userViewFXML);
                        userController.setStage(this.stage);
                        this.stage.setScene(userWindow);
                        this.stage.setTitle(user.getUsername());
                        this.stage.show();
                        return;
                    } catch (IOException e) {
                        errorLabel.setText("Unexpected error occurred, please try again.");
                    }
                }
                index++;
            }
            errorLabel.setText("Invalid username or password, please try again.");
        });

        createAccountButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccountView.fxml"));
                CreateAccountController createAccountController = new CreateAccountController();
                loader.setController(createAccountController);
                Parent createAccountViewFXML = loader.load();
                Scene createAccountWindow = new Scene(createAccountViewFXML);
                Stage stage = new Stage();
                createAccountController.setStage(stage);
                stage.setScene(createAccountWindow);
                stage.setTitle("Create Account");
                stage.showAndWait();
                if (createAccountController.wasUserCreated()) {
                    try {
                    this.backend.getDb().createUser(createAccountController.getCreatedUserName(),
                            createAccountController.getCreatedUserPassword(),
                            createAccountController.getCreatedUserFName(),
                            createAccountController.getCreatedUserLName());
                    } catch (SQLException e) {
                        System.out.println(e);
                        this.errorLabel.setText("Unexpected SQL Error, please try again");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LoginController(ApplicationBackend backend) {
        this.backend = backend;
    }

}
