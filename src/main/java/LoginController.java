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
    private ApplicationBackend backend; // Backend database for the application

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
        // Code that is executed when user clicks "login"
        loginButton.setOnAction(event -> {
            int index = 0; // Loop Index
            // Iterate through all users in the backend
            for (User user : backend.getDb().getUsers()) {
                // Check if the user's username and password equal the username and password of
                // a user in the backend
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

                        // Load the FXML and assign it a variable
                        Parent userViewFXML = loader.load();

                        // Create a new scene for the next window
                        Scene userWindow = new Scene(userViewFXML);

                        // Assign the stage to the controllerr
                        userController.setStage(this.stage);

                        // Set the current scene to the new window
                        this.stage.setScene(userWindow);

                        // Assign the title of the next stage as the username of the user
                        this.stage.setTitle(user.getUsername());

                        // Switch to the new screen
                        this.stage.show();
                        return;
                    } catch (IOException e) {
                        // Show any errors on the UI using an error label
                        errorLabel.setText("Unexpected error occurred, please try again.");
                    }
                }
                index++; // Increase iterator
            }
            errorLabel.setText("Invalid username or password, please try again.");
        });

        // Metod for when the users clicks on the create account button
        createAccountButton.setOnAction(event -> {
            try {
                // Load the next screen UserView
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccountView.fxml"));

                // Create new controller insance for account screen
                CreateAccountController createAccountController = new CreateAccountController();

                // Assign the FXML loader thew new controller
                loader.setController(createAccountController);

                // Load the FXML and assign it a variable
                Parent createAccountViewFXML = loader.load();

                // Create new scene for Create account window
                Scene createAccountWindow = new Scene(createAccountViewFXML);

                // Create new stage for the window
                Stage stage = new Stage();

                // Set the stage of the controller to the new stage
                createAccountController.setStage(stage);

                // Set the scene off the new stage
                stage.setScene(createAccountWindow);

                // Assign the stage a new title
                stage.setTitle("Create Account");

                // Show the stage and wait for the user to navigate back
                                // Show the stage and wait for user input
                stage.showAndWait();

                // When the user navigates back, check if the user was created via the
                // controller
                if (createAccountController.wasUserCreated()) {
                    try {

                        // If the user was created, add the new user to the database (This will also add
                        // an entry to the SQLite DB)
                        this.backend.getDb().createUser(createAccountController.getCreatedUserName(),
                                createAccountController.getCreatedUserPassword(),
                                createAccountController.getCreatedUserFName(),
                                createAccountController.getCreatedUserLName());
                    } catch (SQLException e) {
                        // If the SQL didn't work, inform the user
                        this.errorLabel.setText("Unexpected SQL Error, please try again");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Constructor, backend needed from the main method after it was initialised via
    // SQL DB
    public LoginController(ApplicationBackend backend) {
        this.backend = backend;
    }

}
