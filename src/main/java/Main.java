package main.java;
public class Main {

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        ApplicationView applicationView = new ApplicationView();

        ApplicationController applicationController = new ApplicationController(applicationView, application);

        applicationView.setVisible(true);
    }
}
