package main.java;

public class Record {
    private Integer weight;
    private Integer temperature;
    private double bloodPressure;
    private String note;

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public String getNote() {
        return note;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
