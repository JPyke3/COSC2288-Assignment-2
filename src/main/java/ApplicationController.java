package main.java;

public class ApplicationController {
    private ApplicationView view = new ApplicationView();
    private Application model = new Application();

    public ApplicationController(ApplicationView view, Application model) {
        this.view = view;
        this.model = model;
    }

    public void registerUser(User user) {

    }

    public void updateDB() {
        // TODO: Implement DB Updating Logic
        model.setDb(null);
    }

    public UserController loginUser(User user) {
        // TODO: Implement Login Logic
        return null;
    }

}
