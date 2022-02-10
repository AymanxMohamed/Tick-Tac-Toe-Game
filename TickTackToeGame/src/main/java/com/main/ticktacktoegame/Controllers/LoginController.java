/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.Network.Client;
import com.main.ticktacktoegame.Network.RequestCreator;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author elsho
 */
public class LoginController {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    private String username = null;
    private String password = null;
    @FXML
    private void sendLoginRequest() {
        username = usernameField.getText();
        password = passwordField.getText();
        try {
            Client.sendRequest(RequestCreator.createLoginJsonString(username, password));
        } catch (IOException ex) {
            
        }
    }
}
