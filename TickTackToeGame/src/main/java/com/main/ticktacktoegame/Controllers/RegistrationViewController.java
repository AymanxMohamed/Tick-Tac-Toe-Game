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
import javafx.scene.control.TextField;

/**
 *
 * @author elsho
 */
public class RegistrationViewController {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField confirmedPasswordField;
    private String username = null;
    private String password = null;
    private String confirmedPassword = null;
    @FXML
    private void sendRegisterRequest() {
        username = usernameField.getText();
        password = passwordField.getText();
        confirmedPassword = confirmedPasswordField.getText();
        try {
            if (Validator.validatePassword(password, confirmedPassword) && Validator.validateUserName(username)) {
                Client.sendRequest(RequestCreator.createRegisterJsonString(username, password));
            } else {
                App.setRoot("RegistrationView");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void switchToLoginView () {
        try {
            App.setRoot("LoginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
