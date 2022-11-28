package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExportFileController {

    // Private class variables
    private Stage stage;
    private List<Record> healthRecords;

    // Constructor
    public ExportFileController(List<Record> healthRecords) {
        this.healthRecords = healthRecords;
    }

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private Button cancelButton;

    @FXML
    private TextField fileNameField;

    @FXML
    private Button filePathButton;

    @FXML
    private TextField filePathField;

    @FXML
    private Button okButton;

    @FXML
    private Label errorLabel;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {

        // Cancel Button
        // Close stage
        cancelButton.setOnAction(event -> {
            this.stage.close();
        });

        // File browse button
        // Open directory chooser and write the path a field
        filePathButton.setOnAction(event -> {

            // Create new directory chooser
            DirectoryChooser directoryChooser = new DirectoryChooser();

            // Show the directory dialogue and assign the path to a variable
            File selectedDirectory = directoryChooser.showDialog(this.stage);

            // Check directory was selected
            if (selectedDirectory == null) {
                return;
            } else {
                // Assign path to the field
                filePathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        // Okay Button
        okButton.setOnAction(event -> {
            // Check that a name exists
            if (!fileNameField.equals("")) {
                // Create a new file from the path assigned and the filename
                File csvFile = new File(filePathField.getText() + "/" + fileNameField.getText() + ".csv");

                // Create the CSV data via stream, map the results using a helper method and
                // collect the elements using a line seperator
                String csvData = this.healthRecords.stream()
                        .map(result -> result.toCSV())
                        .collect(Collectors.joining(System.getProperty("line.separator")));

                // Try writing the file to CSV
                try (PrintWriter pw = new PrintWriter(csvFile)) {
                    // Write the file
                    pw.write(csvData);
                } catch (FileNotFoundException e) {
                    errorLabel.setText("Unable to write to file, please try again");
                }

            } else {
                errorLabel.setText("Please add a name for the file");
            }
        });
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
