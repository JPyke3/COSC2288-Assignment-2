package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditRecordController {

    // Private class variables
    private Stage stage;
    private Record record;

    // Constructor
    public EditRecordController(Record record) {
        this.record = record;
    }

    // Add FXML Variables for manipulating the UI from the controller
    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField highBloodField;

    @FXML
    private TextField lowBloodField;

    @FXML
    private TextField noteField;

    @FXML
    private Button okayButton;

    @FXML
    private TextField temperatureField;

    @FXML
    private TextField weightField;

    @FXML
    private Label errorLabel;

    // Method executed when the UI is drawn
    @FXML
    public void initialize() {

        // Set the value of the fields to the existing records values
        datePicker.setValue(record.getRecordDate());
        highBloodField.setText(Integer.toString(record.getBloodPressureHigh()));
        lowBloodField.setText(Integer.toString(record.getBloodPressureLow()));
        noteField.setText(record.getNote());
        temperatureField.setText(Integer.toString(record.getTemperature()));
        weightField.setText(Integer.toString(record.getWeight()));


        // Okay Button
        okayButton.setOnAction(event -> {
            try {
                // Create a new record using the values and assign to private variable
                this.record = new Record(record.getId(), Integer.parseInt(weightField.getText()),
                        Integer.parseInt(temperatureField.getText()),
                        Integer.parseInt(highBloodField.getText()) + "-" + Integer.parseInt(lowBloodField.getText()),
                        Integer.parseInt(highBloodField.getText()), Integer.parseInt(lowBloodField.getText()),
                        noteField.getText(), datePicker.getValue());
                // Close stage
                this.stage.close();
            } catch (NumberFormatException e) {
                errorLabel.setText("Please insure you enter valid numbers into the fields that require it.");
            }
        });

        // Cancel Button
        // Close stage
        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    // Getter
    public Record getRecord() {
        return this.record;
    }

    // Define function to assign stage to the controller (Helpful for backwards
    // navigation)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
