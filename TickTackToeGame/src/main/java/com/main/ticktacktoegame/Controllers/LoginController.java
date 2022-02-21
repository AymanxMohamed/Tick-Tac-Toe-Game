/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import com.main.ticktacktoegame.Network.RequestCreator;
import com.main.ticktacktoegame.Utilites.Validator;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author elsho
 */
public class LoginController {

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    private String username = null;
    private String password = null;

    @FXML
    Label loginErrorLabel;

    @FXML
    public void sendLoginRequest() {
        username = usernameField.getText();
        password = passwordField.getText();
        try {
            if (Validator.isEmpty(username) && Validator.isEmpty(password)) {
                App.setRoot("LoginView");
            } else {
                Client.sendRequest(RequestCreator.login(username, password));
            }
        } catch (IOException ex) {
            System.out.println("problem in login controller");
        }
    }

    public void switchToRegistrationView() {
        try {
            App.setRoot("RegistrationView");
        } catch (IOException ex) {
            System.out.println("problem in login controller");
        }
    }
}
