package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    /***
     * Generate the Application's backend
     * 
     * This method will fetch the SQLite database and populate the in-app backend
     * variable
     * 
     * @return ApplicationBackend
     */
    public ApplicationBackend generateApplicationBackend() {
        // Initialize method variables
        List<User> users = new ArrayList<User>();
        Database db = new Database(users);
        // Create the SQL Query to fetch all the users from SQLite
        String userQuery = "SELECT * FROM Users";
        try {
            // Define the path to the SQLite database
            String url = "jdbc:sqlite:src/main/java/db.db";

            // Create the SQLite connection
            Connection connection = DriverManager.getConnection(url);

            // Create the SQL Statement
            Statement statement = connection.createStatement();

            // Execute the SQL statement
            ResultSet results = statement.executeQuery(userQuery);

            // Iterate through the results of the statement
            while (results.next()) {
                // Create another statement used for fetching the records
                String recordQuery = "SELECT * FROM Records WHERE UserID=" + results.getString("ID");
                Statement recordStatement = connection.createStatement();

                // Create the statement for the records
                ResultSet recordResults = recordStatement.executeQuery(recordQuery);

                // Create an arraylist to later add records to
                List<Record> records = new ArrayList<Record>();

                // Create the user object to return back to the database
                User user = new User(results.getInt("ID"), results.getString("Username"), results.getString("Password"),
                        results.getString("First_Name"), results.getString("Last_Name"));

                // Iterate through the resulting records from the SQL Statement
                while (recordResults.next()) {
                    // Add a record to the ArrayList from the SQLite database
                    records.add(new Record(recordResults.getInt("ID"), recordResults.getInt("Weight"),
                            recordResults.getInt("Temperature"),
                            recordResults.getString("Blood_Pressure_String"),
                            recordResults.getInt("Blood_Pressure_High"), recordResults.getInt("Blood_Pressure_Low"),
                            recordResults.getString("Note"), Instant.ofEpochSecond(recordResults.getInt("Date"))
                                    .atZone(ZoneId.systemDefault()).toLocalDate()));
                }

                // Iterate through all the records and add them to the user object
                for (Record record : records) {
                    user.addHealthRecord(record);
                }

                // Add the user to the array of users in the database
                db.addUserToArray(user);
            }
        } catch (SQLException e) {
            // Can safely throw this error in the console as the app won't boot if it's
            // triggered
            System.out.println("Unable to connect with the SQLite Database");
        }
        return new ApplicationBackend(db);
    }

    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Generate the backend of the app
        generateApplicationBackend();

        // Load the FXML of the ApplicationView (The login screen)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicationView.fxml"));

        // Initialise the controller for the login screen
        LoginController applicationController = new LoginController(generateApplicationBackend());

        // Set the stage of the controller to the primary stage (The one created upon
        // app launch)
        applicationController.setStage(primaryStage);

        // Set the controller of the FXML file
        loader.setController(applicationController);

        // Create a root box to store the FXML file
        VBox root = loader.load();

        // Create a new scene
        Scene scene = new Scene(root);

        // Set the primaryStage to the new scene
        primaryStage.setScene(scene);

        // Add title for the app
        primaryStage.setTitle("Login");

        // Show the app
        primaryStage.show();
    }
}
