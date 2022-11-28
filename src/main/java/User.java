package main.java;

import java.util.ArrayList;
import java.util.List;

public class User {

    // Private Class Variables
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private List<Record> healthRecords = new ArrayList<Record>();
    private String lastName;

    // Constructor
    public User(Integer id, String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public List<Record> getHealthRecords() {
        return healthRecords;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Add record to the health records set
     * 
     * @param record record to add
     */
    public void addHealthRecord(Record record) {
        this.healthRecords.add(record);
    }

    /**
     * Remove a health record from the records set
     * 
     * @param record record to remove
     */
    public void removeHealthRecord(Record record) {
        this.healthRecords.remove(record);
    }

}
