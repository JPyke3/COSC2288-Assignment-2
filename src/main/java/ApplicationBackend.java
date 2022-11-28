package main.java;

public class ApplicationBackend {
    private Database db;

    public ApplicationBackend(Database db) {
        this.db = db;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

}
