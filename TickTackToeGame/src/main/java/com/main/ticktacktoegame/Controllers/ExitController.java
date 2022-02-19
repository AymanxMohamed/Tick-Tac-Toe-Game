/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author elsho
 */
public class ExitController {

    @FXML 
    void cancelExit() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            Logger.getLogger(ExitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML void sendLogout() {
        try {
            Client.sendRequest(logout());
            App.setRoot("index");
        } catch (IOException ex) {
            Logger.getLogger(ExitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void sendExit() {
        Client.sendRequest(logout());
        System.exit(0);
    }
}
