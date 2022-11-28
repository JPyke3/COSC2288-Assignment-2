package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

public class Database {
    List<User> users;

    public Database(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void createUser(String username, String password, String fName, String lName) throws SQLException {
        String sql = "INSERT INTO Users (Username, Password, First_Name, Last_Name) VALUES ('" + username
                + "', '" + password + "', '" + fName + "', '" + lName + "')";
        System.out.println(sql);
        Connection connection = this.connectToDB();
        PreparedStatement statement = connection.prepareStatement(sql);
        int index = statement.executeUpdate();

        // Add User to ArrayList
        users.add(new User(index, username, password, fName, lName));
    }

    public void addUserToArray(User user) {
        users.add(user);
    }

    public void updateUser(User user, Integer index) {
        // Update List
        users.set(index, user);

        // Update
        String sql = "UPDATE Users SET First_Name = '" + user.getFirstName() + "', Last_Name = '" + user.getLastName()
                + "' WHERE ID = " + user.getId();
        System.out.println(sql);
        try {
            Connection connection = this.connectToDB();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateUserRecord(User user, Integer index, Record record) {
        String sql = "UPDATE Records SET Weight = " + record.getWeight() + ", Blood_Pressure_String = '"
                + record.getBloodPressureString() + "', Blood_Pressure_High = " + record.getBloodPressureHigh()
                + ", Blood_Pressure_Low =" + record.getBloodPressureLow() + ", Temperature = " + record.getTemperature()
                + ", Note = '" + record.getNote() + "', Date = " + record.getRecordDate() + " WHERE ID = "
                + record.getId();
        System.out.println(sql);

        try {
            Connection connection = this.connectToDB();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteUserRecord(User user, Integer index, Record record) throws SQLException {
        String sql = "DELETE FROM Records WHERE ID=" + record.getId();
        System.out.println(sql);
        Connection connection = this.connectToDB();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        users.get(index).removeHealthRecord(record);
    }

    public void createUserRecord(User user, Integer index, Integer weight, String bloodPressureString,
            Integer bloodPressureHigh, Integer bloodPressureLow, Integer temperature, String note, LocalDate date)
            throws SQLException {
        String sql = "INSERT INTO Records (UserID, Weight, Blood_Pressure_String, Blood_Pressure_High, Blood_Pressure_Low, Temperature, Note, Date) VALUES ("
                + index
                + ", " + weight + ", '" + bloodPressureString + "', "
                + bloodPressureHigh + ", "
                + bloodPressureLow + ", " + temperature
                + ", '" + note + "', "
                + date.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN)
                + ")";
        System.out.println(sql);
        Connection connection = this.connectToDB();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.println(generatedKeys.getLong(1));
            // Add Record to ArrayList
            users.get(index).addHealthRecord(
                    new Record((int) generatedKeys.getLong(1), weight, temperature, bloodPressureString,
                            bloodPressureHigh, bloodPressureLow, note, date));
        }

    }

    private Connection connectToDB() throws SQLException {
        String url = "jdbc:sqlite:src/main/java/db.db";

        return DriverManager.getConnection(url);
    }
}
