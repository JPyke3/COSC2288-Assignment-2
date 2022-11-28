package main.java;

/***
 * Class for storing the application database information
 */
public class ApplicationBackend {
    // Private class variables
    private Database db; // Variable to store the database

    // Constructor
    public ApplicationBackend(Database db) {
        this.db = db;
    }

    // Getter
    public Database getDb() {
        return db;
    }

    // Setter
    public void setDb(Database db) {
        this.db = db;
    }

}
