package main.java;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class UserController {

    // Private Class Variables
    private User user;
    private Integer userIndex;
    private Stage stage;
    private Scene loginScene; // Scene from previous screen, used for the logout feature
    private ApplicationBackend backend;

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem deleteRecordButton;

    @FXML
    private MenuItem editRecordButton;

    @FXML
    private MenuItem exportButton;

    @FXML
    private MenuItem logoutButton;

    @FXML
    private MenuItem newRecordButton;

    @FXML
    private MenuItem updateProfileButton;

    @FXML
    private MenuItem viewProfileButton;

    @FXML
    private MenuItem viewRecordButton;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLastName;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {
        // Make the labels on the main menu the user's first and last name
        userFirstName.setText(user.getFirstName());
        userLastName.setText(user.getLastName());

        // About menu button
        // Show an about screen
        aboutButton.setOnAction(event -> {
            try {
                // Load the next screen About View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutView.fxml"));

                // Load the FXML and assign it a variable
                Parent aboutViewFXML = loader.load();

                // Create a new scene for the next window
                Scene aboutWindow = new Scene(aboutViewFXML);

                // Create new stage for the AboutView
                Stage stage = new Stage();

                // Assign the scene to the new stage
                stage.setScene(aboutWindow);

                // Give the stage a title
                stage.setTitle("About");

                // Show the stage
                stage.show();
            } catch (IOException e) {

            }
        });

        // Delete record buutton
        // Open new screen with a list, then the user will select the item and the item
        // will be deleted
        deleteRecordButton.setOnAction(event -> {
            try {

                // Load the next screen Delete Record View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecordListView.fxml"));

                // Initialise the controller for the view
                DeleteRecordController deleteRecordListController = new DeleteRecordController(
                        user.getHealthRecords());

                // Assign the FXML the controller
                loader.setController(deleteRecordListController);

                // Load the FXML and assign the result to a variable
                Parent deleteRecordListViewFXML = loader.load();

                // Create new Scene with the FXML
                Scene deleteRecordListWindow = new Scene(deleteRecordListViewFXML);

                // Create new stage for the window
                Stage stage = new Stage();

                // Assign the stage to the controller
                deleteRecordListController.setStage(stage);

                // Assign the scene to the stage
                stage.setScene(deleteRecordListWindow);

                // Give the stage a title
                stage.setTitle("Delete Record");

                // Show the stage and wait for user input
                stage.showAndWait();

                // Remove the health record from the local version of the db
                this.user.removeHealthRecord(deleteRecordListController.getRemovedRecord());
                try {

                    // Remove the health record from the SQLite database
                    backend.getDb().deleteUserRecord(user, userIndex, deleteRecordListController.getRemovedRecord());
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        // Edit Record menu button
        // Open list view, click the record to edit, show another screen and modify the
        // values, then save it and update the DB
        editRecordButton.setOnAction(event -> {
            try {

                // Load the next screen Delete Record View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRecordListView.fxml"));

                // Create the controller and pass the health records to the controller
                EditRecordListController EditRecordListController = new EditRecordListController(
                        user.getHealthRecords());

                // Assign the FXML loader the new controller
                loader.setController(EditRecordListController);

                // Load the FXML and assign it a variable
                Parent EditRecordListViewFXML = loader.load();

                // Create new Scene from the FXML
                Scene EditRecordListWindow = new Scene(EditRecordListViewFXML);

                // Create new stage
                Stage stage = new Stage();

                // Assign the controller the new stage
                EditRecordListController.setStage(stage);

                // Set the stage of the new scene
                stage.setScene(EditRecordListWindow);

                // Assign the stage a title
                stage.setTitle("Edit Record");
                // Show the stage and wait for user input
                stage.showAndWait();
                for (Record record : EditRecordListController.getItems()) {
                    // Update the user record via the DB (Updates in SQLite and the local DB)
                    try{
                    backend.getDb().updateUserRecord(user, userIndex, record);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        // Export Button
        // Creates a new window where you can define the directory and filename and
        // export a csv file
        exportButton.setOnAction(event -> {
            try {
                // Load the next screen Export File View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ExportFileView.fxml"));

                // Create a new controller for the next screen
                ExportFileController ExportFileController = new ExportFileController(this.user.getHealthRecords());

                // Assign the FXML loader the new controller
                loader.setController(ExportFileController);

                // Load the FXML and assign it a variable
                Parent ExportFileViewFXML = loader.load();

                // Create new scene from the FXML file
                Scene ExportFileWindow = new Scene(ExportFileViewFXML);

                // Create new stage for scene
                Stage stage = new Stage();

                // Assign stage to controller
                ExportFileController.setStage(stage);

                // Assign the scene to the stage
                stage.setScene(ExportFileWindow);

                // Give the stage a title
                stage.setTitle("Record list");

                // Show the stage
                stage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        });

        // Logout button
        // Go back to the login screen
        logoutButton.setOnAction(event -> {
            this.stage.setScene(loginScene);
            this.stage.show();
        });

        // Create new health record
        // Open new form that takes all the parameters required for a new health entry
        newRecordButton.setOnAction(event -> {
            try {
                // Load the next screen Create Record View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateRecordView.fxml"));

                // Create new controller
                CreateRecordController createRecordController = new CreateRecordController();

                // Assign the FXML the new controller
                loader.setController(createRecordController);

                // Load the FXML and assign it a variable
                Parent createRecordViewFXML = loader.load();

                // Create new scene
                Scene createRecordWindow = new Scene(createRecordViewFXML);

                // Create new stage
                Stage stage = new Stage();

                // Assign stage to controller
                createRecordController.setStage(stage);

                // Assign the scene to the stage
                stage.setScene(createRecordWindow);

                // Give the stage a title
                stage.setTitle("Create Record");

                // Show the stage and wait for user input
                stage.showAndWait();

                // Check if a new record was created
                if (createRecordController.wasRecordCreated()) {
                    try {
                        // Create a new user record (Will create in the SQLite database and the local db
                        // variable)
                        backend.getDb().createUserRecord(userIndex, createRecordController.getWeight(),
                                createRecordController.getBloodPressureString(),
                                createRecordController.getBloodPressureHigh(),
                                createRecordController.getBloodPressureLow(), createRecordController.getTemperature(),
                                createRecordController.getNote(), createRecordController.getDate());
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }
                System.out.println();
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        // Update Profile
        // Open new form with the existing profile parameters, the user can then modify
        // them
        updateProfileButton.setOnAction(event -> {
            try {
                // Load the next update profile view screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfileView.fxml"));

                // Create new controller
                UpdateProfileController updateProfileController = new UpdateProfileController(this.user);

                // Assign the FXML to the new controller
                loader.setController(updateProfileController);

                // load the FXML and store the value in a variable
                Parent updateProfileViewFXML = loader.load();

                // Create new scene
                Scene updateProfileWindow = new Scene(updateProfileViewFXML);

                // Create new stage
                Stage stage = new Stage();

                // Assign stage to new controller
                updateProfileController.setStage(stage);

                // Set the scene of the stage
                stage.setScene(updateProfileWindow);

                // Assign title to stage
                stage.setTitle("Update Profile");

                // Show the stage and wait for user input
                stage.showAndWait();

                // Create new user variable
                User updateUser = updateProfileController.getUser();

                // Set the first name to the changed name
                this.user.setFirstName(updateUser.getFirstName());

                // Set the last name to the changed name
                this.user.setLastName(updateUser.getLastName());

                // Update the menu text again
                userFirstName.setText(this.user.getFirstName());
                userLastName.setText(this.user.getLastName());

                // Update the user database entry (SQLite DB)
                try {
                backend.getDb().updateUser(updateUser, userIndex);
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        // View profile button
        // Very similiar to the update profile screen, just shows the information
        // instead of being able to update it
        viewProfileButton.setOnAction(event -> {
            try {
                // Load the next ProfileView screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileView.fxml"));

                // Create new controller
                ProfileController profileController = new ProfileController(this.user);

                // Assign FXML the new controller
                loader.setController(profileController);

                // Load the FXML and assign it to a variable
                Parent profileViewFXML = loader.load();

                // Create new scene
                Scene profileWindow = new Scene(profileViewFXML);

                // Create new stage
                Stage stage = new Stage();

                // Assign the stage to the controller
                profileController.setStage(stage);

                // Set the scene of the new stage
                stage.setScene(profileWindow);

                // Set the title of the new stage
                stage.setTitle("View Profile");

                // Show the new window
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        // View record button
        // Show a list of the health records
        viewRecordButton.setOnAction(event -> {
            try {
                // Load the next Record List View
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RecordListView.fxml"));

                // Create new controller
                RecordListController RecordListController = new RecordListController(this.user.getHealthRecords());

                // Assign FXML to the new controller
                loader.setController(RecordListController);

                // Load the FXML and assign it to a variable
                Parent RecordListViewFXML = loader.load();

                // Create new scene
                Scene RecordListWindow = new Scene(RecordListViewFXML);

                // Create new stage
                Stage stage = new Stage();

                // Assign stage to controller
                RecordListController.setStage(stage);

                // Assign scene to stage
                stage.setScene(RecordListWindow);

                // Give stage a title
                stage.setTitle("View Record");

                // Show the stage
                stage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    //  Constructor
    public UserController(User user, Scene loginScene, ApplicationBackend backend, Integer userIndex) {
        this.user = user;
        this.loginScene = loginScene;
        this.backend = backend;
        this.userIndex = userIndex;
    }

    // Getters
    public String getUserUsername() {
        return user.getUsername();
    }

    public String getUserFirstName() {
        return user.getFirstName();
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    // Setters
    public void setUserPassword(String password) {
        user.setPassword(password);
    }

    public void setUserFirstName(String firstName) {
        user.setFirstName(firstName);
    }

    public void setUserLastName(String lastName) {
        user.setLastName(lastName);
    }

    public void setUserUsername(String name) {
        user.setUsername(name);
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User returnUser() {
        return user;
    }
}
