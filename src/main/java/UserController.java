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

    private User user;
    private Integer userIndex;
    private Stage stage;
    private Scene loginScene;
    private ApplicationBackend backend;

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

    @FXML
    public void initialize() {
        userFirstName.setText(user.getFirstName());
        userLastName.setText(user.getLastName());

        aboutButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutView.fxml"));
                Parent aboutViewFXML = loader.load();
                Scene aboutWindow = new Scene(aboutViewFXML);
                Stage stage = new Stage();
                stage.setScene(aboutWindow);
                stage.setTitle("About");
                stage.show();
            } catch (IOException e) {

            }
        });

        deleteRecordButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecordListView.fxml"));
                DeleteRecordController deleteRecordListController = new DeleteRecordController(
                        user.getHealthRecords());
                loader.setController(deleteRecordListController);
                Parent deleteRecordListViewFXML = loader.load();
                Scene deleteRecordListWindow = new Scene(deleteRecordListViewFXML);
                Stage stage = new Stage();
                deleteRecordListController.setStage(stage);
                stage.setScene(deleteRecordListWindow);
                stage.setTitle("Delete Record");
                stage.showAndWait();
                this.user.removeHealthRecord(deleteRecordListController.getRemovedRecord());
                try {
                    backend.getDb().deleteUserRecord(user, userIndex, deleteRecordListController.getRemovedRecord());
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        editRecordButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRecordListView.fxml"));
                EditRecordListController EditRecordListController = new EditRecordListController(
                        user.getHealthRecords());
                loader.setController(EditRecordListController);
                Parent EditRecordListViewFXML = loader.load();
                Scene EditRecordListWindow = new Scene(EditRecordListViewFXML);
                Stage stage = new Stage();
                EditRecordListController.setStage(stage);
                stage.setScene(EditRecordListWindow);
                stage.setTitle("Edit Record");
                stage.showAndWait();
                for (Record record : EditRecordListController.getItems()) {
                    // this.user.addHealthRecord(record);
                    backend.getDb().updateUserRecord(user, userIndex, record);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        exportButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ExportFileView.fxml"));
                ExportFileController ExportFileController = new ExportFileController(this.user.getHealthRecords());
                loader.setController(ExportFileController);
                Parent ExportFileViewFXML = loader.load();
                Scene ExportFileWindow = new Scene(ExportFileViewFXML);
                Stage stage = new Stage();
                ExportFileController.setStage(stage);
                stage.setScene(ExportFileWindow);
                stage.setTitle("Record list");
                stage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        });

        logoutButton.setOnAction(event -> {
            this.stage.setScene(loginScene);
            this.stage.show();
        });

        newRecordButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateRecordView.fxml"));
                CreateRecordController createRecordController = new CreateRecordController();
                loader.setController(createRecordController);
                Parent createRecordViewFXML = loader.load();
                Scene createRecordWindow = new Scene(createRecordViewFXML);
                Stage stage = new Stage();
                createRecordController.setStage(stage);
                stage.setScene(createRecordWindow);
                stage.setTitle("Create Record");
                stage.showAndWait();
                if (createRecordController.wasRecordCreated()) {
                    try {
                        backend.getDb().createUserRecord(user, userIndex, createRecordController.getWeight(),
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

        updateProfileButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfileView.fxml"));
                UpdateProfileController updateProfileController = new UpdateProfileController(this.user);
                loader.setController(updateProfileController);
                Parent updateProfileViewFXML = loader.load();
                Scene updateProfileWindow = new Scene(updateProfileViewFXML);
                Stage stage = new Stage();
                updateProfileController.setStage(stage);
                stage.setScene(updateProfileWindow);
                stage.setTitle("Update Profile");
                stage.showAndWait();
                User updateUser = updateProfileController.getUser();
                this.user.setFirstName(updateUser.getFirstName());
                this.user.setLastName(updateUser.getLastName());
                userFirstName.setText(this.user.getFirstName());
                userLastName.setText(this.user.getLastName());
                backend.getDb().updateUser(updateUser, userIndex);
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        viewProfileButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileView.fxml"));
                ProfileController profileController = new ProfileController(this.user);
                loader.setController(profileController);
                Parent profileViewFXML = loader.load();
                Scene profileWindow = new Scene(profileViewFXML);
                Stage stage = new Stage();
                profileController.setStage(stage);
                stage.setScene(profileWindow);
                stage.setTitle("View Profile");
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        viewRecordButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RecordListView.fxml"));
                RecordListController RecordListController = new RecordListController(this.user.getHealthRecords());
                loader.setController(RecordListController);
                Parent RecordListViewFXML = loader.load();
                Scene RecordListWindow = new Scene(RecordListViewFXML);
                Stage stage = new Stage();
                RecordListController.setStage(stage);
                stage.setScene(RecordListWindow);
                stage.setTitle("View Record");
                stage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    public UserController(User user, Scene loginScene, ApplicationBackend backend, Integer userIndex) {
        this.user = user;
        this.loginScene = loginScene;
        this.backend = backend;
        this.userIndex = userIndex;
    }

    public void setUserUsername(String name) {
        user.setUsername(name);
    }

    public String getUserUsername() {
        return user.getUsername();
    }

    public void setUserPassword(String password) {
        user.setPassword(password);
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserFirstName(String firstName) {
        user.setFirstName(firstName);
    }

    public String getUserFirstName() {
        return user.getFirstName();
    }

    public void setUserLastName(String lastName) {
        user.setLastName(lastName);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User returnUser() {
        return user;
    }
}
