package main.java;

import java.util.List;

public class UserController {
    private User model;
    private UserView view;

    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public void setUserUsername(String name) {
        model.setUsername(name);
    }

    public String getUserUsername() {
        return model.getUsername();
    }

    public void setUserPassword(String password) {
        model.setPassword(password);
    }

    public String getUserPassword() {
        return model.getPassword();
    }

    public void setUserFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public String getUserFirstName() {
        return model.getFirstName();
    }

    public void setUserLastName(String lastName)  {
        model.setLastName(lastName);
    }

    //TODO
    public void createNewRecord() {

    }

    //TODO
    public void editRecord() {
        
    }

    public void editProfile() {
        // TODO: Logic for profile editing
    }

    public void createRecord(Record record) {
        //TODO: Logic for adding record and saving to model
    }

    public void deleteRecord(Record record) {
        // TODO: Logic for deleting specific record
    }

    public List<Record> exportRecords() {
        // TODO: Return list of records
        return null;
    }
}
