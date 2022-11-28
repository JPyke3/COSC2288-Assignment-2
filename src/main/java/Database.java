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

/***
 * Class used to store and manipulate database information
 */
public class Database {
    // Class Variables
    List<User> users;

    // Constructor
    public Database(List<User> users) {
        this.users = users;
    }

    // Getter
    public List<User> getUsers() {
        return users;
    }

    // Setter
    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Method to add users to the global users array
    public void addUserToArray(User user) {
        users.add(user);
    }

    /**
     * Creates a new user in the SQL Database and adds it to the DB
     * 
     * @param username New Username
     * @param password New Password
     * @param fName    New First Name
     * @param lName    New Last Name
     * @throws SQLException In case the SQL runs into any errors
     */
    public void createUser(String username, String password, String fName, String lName) throws SQLException {

        // SQL Query String
        String sql = "INSERT INTO Users (Username, Password, First_Name, Last_Name) VALUES ('" + username
                + "', '" + password + "', '" + fName + "', '" + lName + "')";

        // Create a connection to the DB
        Connection connection = this.connectToDB();

        // Prepare the SQL Statement
        PreparedStatement statement = connection.prepareStatement(sql);

        // Execute the SQL update
        int index = statement.executeUpdate();

        // Add User to ArrayList
        users.add(new User(index, username, password, fName, lName));
    }

    /**
     * Updates a given user in the SQL Database and the local DB List
     * 
     * @param user User with updates
     * @param index Index of the user in the DB
     */
    public void updateUser(User user, Integer index) throws SQLException {
        // Update List
        users.set(index, user);

        // Create SQL Statement
        String sql = "UPDATE Users SET First_Name = '" + user.getFirstName() + "', Last_Name = '" + user.getLastName()
                + "' WHERE ID = " + user.getId();
        // Create a connection to the DB
        Connection connection = this.connectToDB();

        // Prepare the SQL Statement
        PreparedStatement statement = connection.prepareStatement(sql);

        // Execute the update
        statement.executeUpdate();
    }

    /**
     * Update the User Record in the SQL Database
     * @param user User with updates
     * @param index Index of user
     * @param record The record with updates
     * @throws SQLException In case the SQL runs into any errors
     */
    public void updateUserRecord(User user, Integer index, Record record) throws SQLException {

        // Create SQL Statement
        String sql = "UPDATE Records SET Weight = " + record.getWeight() + ", Blood_Pressure_String = '"
                + record.getBloodPressureString() + "', Blood_Pressure_High = " + record.getBloodPressureHigh()
                + ", Blood_Pressure_Low =" + record.getBloodPressureLow() + ", Temperature = " + record.getTemperature()
                + ", Note = '" + record.getNote() + "', Date = " + record.getRecordDate() + " WHERE ID = "
                + record.getId();

        // Create a connection to the DB
        Connection connection = this.connectToDB();
       
        // Prepare the SQL Statement
        PreparedStatement statement = connection.prepareStatement(sql);

        // Execute the Update
        statement.executeUpdate();
    }

    /**
     * Delete user record in the SQL Database and the local db variable
     * @param user
     * @param index
     * @param record
     * @throws SQLException
     */
    public void deleteUserRecord(User user, Integer index, Record record) throws SQLException {

        // Create SQL Statement
        String sql = "DELETE FROM Records WHERE ID=" + record.getId();

        // Create connection to the SQL DB
        Connection connection = this.connectToDB();

        // Prepare statement
        PreparedStatement statement = connection.prepareStatement(sql);

        // Execute the update
        statement.executeUpdate();

        // Remove the record from the local DB File
        users.get(index).removeHealthRecord(record);
    }

    /**
     * Create a new record in the SQLite DB and the local DB
     * @param index User index 
     * @param weight 
     * @param bloodPressureString
     * @param bloodPressureHigh
     * @param bloodPressureLow
     * @param temperature
     * @param note
     * @param date
     * @throws SQLException In case the SQL runs into any errors
     */
    public void createUserRecord(Integer index, Integer weight, String bloodPressureString,
            Integer bloodPressureHigh, Integer bloodPressureLow, Integer temperature, String note, LocalDate date)
            throws SQLException {
        // Create SQL Statement
        String sql = "INSERT INTO Records (UserID, Weight, Blood_Pressure_String, Blood_Pressure_High, Blood_Pressure_Low, Temperature, Note, Date) VALUES ("
                + index
                + ", " + weight + ", '" + bloodPressureString + "', "
                + bloodPressureHigh + ", "
                + bloodPressureLow + ", " + temperature
                + ", '" + note + "', "
                + date.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN)
                + ")";
        // Create Connection to the DB
        Connection connection = this.connectToDB();
        
        // Prepare statement - This call ensures that the generated keys are returned
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Execute the update
        statement.executeUpdate();

        // Fetch the generated keys (Needed to obtain the ID of the record)
        ResultSet generatedKeys = statement.getGeneratedKeys();

        // Iterate through the generated keys
        if (generatedKeys.next()) {
            // Obtain the key at column index 1 (This will always be the ID column)
            System.out.println(generatedKeys.getLong(1));
            // Add Record to ArrayList
            users.get(index).addHealthRecord(
                    new Record((int) generatedKeys.getLong(1), weight, temperature, bloodPressureString,
                            bloodPressureHigh, bloodPressureLow, note, date));
        }

    }

    /**
     * Helper method to connect to the SQLite DB
     * @return The SQLite connection
     * @throws SQLException
     */
    private Connection connectToDB() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:src/main/java/db.db";

        return DriverManager.getConnection(url);
    }
}
