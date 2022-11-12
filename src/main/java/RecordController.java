package main.java;

import java.util.List;

public class RecordController {
    private Record model;
    private RecordView view;

    public RecordController(Record model, RecordView view) {
        this.model = model;
        this.view = view;
    }

    public void setWeight(Integer weight) {
        model.setWeight(weight);
    }

    public Integer getWeight() {
        return model.getWeight();
    }

    public void setTemperature(Integer temperature) {
        model.setTemperature(temperature);
    }

    public Integer getTemperature() {
        return model.getTemperature();
    }

    public void setBloodPressure(Integer bloodPressure) {
        model.setBloodPressure(bloodPressure);
    }

    public double getBloodPressure() {
        return model.getBloodPressure();
    }

    public void setNote(String note) {
        model.setNote(note);
    }

    public String getNote() {
        return model.getNote();
    }

    public Record editRecord(Record record) {
        // TODO: Implement record editing logic
        return null;
    }

    public void deleteRecord(Record record) {
        // TODO: Implement record deleting logic
    }

    public List<Record> exportRecords() {
        // TODO: Implement record export logic
        return null;
    }

}
