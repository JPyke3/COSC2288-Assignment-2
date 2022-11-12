package main.java;

import java.util.List;

import javax.swing.ButtonModel;

public class UserView {
    private List<String> userOptions;
    private ButtonModel logoutButton;

    public List<String> getUserOptions() {
        return userOptions;
    }

    public void setUserOptions(List<String> userOptions) {
        this.userOptions = userOptions;
    }

    public ButtonModel getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(ButtonModel logoutButton) {
        this.logoutButton = logoutButton;
    }
}
