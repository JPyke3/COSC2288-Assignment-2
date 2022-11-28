package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Record {

    // Private Class Variables
    private Integer id;

    private Integer weight;
    private Integer temperature;
    private String bloodPressureString;
    private Integer bloodPressureHigh;
    private Integer bloodPressureLow;
    private String note;
    private LocalDate recordDate;

    // Constructor
    public Record(Integer id, Integer weight, Integer temperature, String bloodPressureString,
            Integer bloodPressureHigh,
            Integer bloodPressureLow, String note, LocalDate recordDate) {
        this.id = id;
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressureString = bloodPressureString;
        this.bloodPressureHigh = bloodPressureHigh;
        this.bloodPressureLow = bloodPressureLow;
        this.note = note;
        this.recordDate = recordDate;
    }

    // Geters
    public String getBloodPressureString() {
        return bloodPressureString;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getNote() {
        return note;
    }

    public String getBloodPressure() {
        return bloodPressureString;
    }

    public Integer getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public Integer getBloodPressureLow() {
        return bloodPressureLow;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public Integer getId() {
        return id;
    }

    // Setters
    public void setBloodPressureString(String bloodPressureString) {
        this.bloodPressureString = bloodPressureString;
    }

    public void setBloodPressureHigh(Integer bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public void setBloodPressureLow(Integer bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressureString = bloodPressure;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Helper method to create a stream of data for an individual Record Item
     * @return
     */
    public String toCSV() {
        return Stream
                .of(this.recordDate.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")), Integer.toString(this.weight),
                        Integer.toString(this.temperature), this.bloodPressureString, this.note)
                .map(value -> value.replaceAll("\"", "\"\""))
                .map(value -> Stream.of("\"", ",").anyMatch(value::contains) ? "\"" + value + "\"" : value)
                .collect(Collectors.joining(","));
    }

}
