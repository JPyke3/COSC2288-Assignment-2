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
    private Stage stage;
    private List<Record> healthRecords;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField fileNameField;

    @FXML
    private Button filePathButton;

    public ExportFileController(List<Record> healthRecords) {
        this.healthRecords = healthRecords;
    }

    @FXML
    private TextField filePathField;

    @FXML
    private Button okButton;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        cancelButton.setOnAction(event -> {
            this.stage.close();
        });

        filePathButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(this.stage);
            if (selectedDirectory == null) {
                return;
            } else {
                filePathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        okButton.setOnAction(event -> {
            if (!fileNameField.equals("")) {
                File csvFile = new File(filePathField.getText() + "/" + fileNameField.getText() + ".csv");
                System.out.println(healthRecords.get(0).toCSV());
                String csvData = this.healthRecords.stream()
                        .map(result -> result.toCSV())
                        .collect(Collectors.joining(System.getProperty("line.separator")));
                try (PrintWriter pw = new PrintWriter(csvFile)) {
                    pw.write(csvData);
                } catch (FileNotFoundException e) {
                    errorLabel.setText("Unable to write to file, please try again");
                }

            } else {
                errorLabel.setText("Please add a name for the file");
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
