package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditRecordController {
    private Stage stage;
    private Record record;

    public EditRecordController(Record record) {
        this.record = record;
    }

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

    @FXML
    public void initialize() {

        datePicker.setValue(record.getRecordDate());
        highBloodField.setText(Integer.toString(record.getBloodPressureHigh()));
        lowBloodField.setText(Integer.toString(record.getBloodPressureLow()));
        noteField.setText(record.getNote());
        temperatureField.setText(Integer.toString(record.getTemperature()));
        weightField.setText(Integer.toString(record.getWeight()));

        okayButton.setOnAction(event -> {
            try {
                this.record = new Record(record.getId(), Integer.parseInt(weightField.getText()),
                        Integer.parseInt(temperatureField.getText()),
                        Integer.parseInt(highBloodField.getText()) + "-" + Integer.parseInt(lowBloodField.getText()),
                        Integer.parseInt(highBloodField.getText()), Integer.parseInt(lowBloodField.getText()),
                        noteField.getText(), datePicker.getValue());
                this.stage.close();
            } catch (NumberFormatException e) {
                errorLabel.setText("Please insure you enter valid numbers into the fields that require it.");
            }
        });

        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public Record getRecord() {
        return this.record;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
