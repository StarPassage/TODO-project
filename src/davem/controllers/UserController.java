package davem.controllers;

import davem.Main;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    private Main mainApp;
    private boolean loginClicked = false;
    Stage dialogStage;

    public PasswordField passwordField;
    public TextField loginField;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void showRegistrationPage() throws IOException {
        mainApp.showRegistrationPage();
    }

    public void handleLoginButton() {
        if (checkUser()) {

            loginClicked = true;
            dialogStage.close();
        }
    }

    public boolean isLoginClicked() {
        return loginClicked;
    }

    private boolean checkUser(){
        return true;
    }
}
