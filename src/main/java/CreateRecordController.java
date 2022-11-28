package main.java;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateRecordController {
    private Stage stage;
    private Boolean wasRecordCreated = false;

    private Integer weight;
    private String bloodPressureString;
    private Integer bloodPressureHigh;
    private Integer bloodPressureLow;
    private Integer temperature;
    private String note;
    private LocalDate date;

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
        okayButton.setOnAction(event -> {
            try {
                this.weight = Integer.parseInt(weightField.getText());
                this.bloodPressureString = highBloodField.getText() + "-" + lowBloodField.getText();
                this.bloodPressureHigh = Integer.parseInt(highBloodField.getText());
                this.bloodPressureLow = Integer.parseInt(lowBloodField.getText());
                this.temperature = Integer.parseInt(temperatureField.getText());
                this.note = noteField.getText();
                this.date = datePicker.getValue();
                this.stage.close();
                this.wasRecordCreated = true;
                this.stage.close();
            } catch (NumberFormatException e) {
                errorLabel.setText("Please insure you enter valid numbers into the fields that require it.");
            }
        });

        cancelButton.setOnAction(event -> {
            this.stage.close();
        });
    }

    public Boolean wasRecordCreated() {
        return wasRecordCreated;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getBloodPressureString() {
        return bloodPressureString;
    }

    public Integer getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public Integer getBloodPressureLow() {
        return bloodPressureLow;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

}
