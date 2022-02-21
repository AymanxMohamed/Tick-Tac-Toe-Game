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
            System.out.println("problem in exit controller");
        }
    }

    @FXML void sendLogout() {
        try {
            Client.sendRequest(logout());
            App.setRoot("index");
        } catch (IOException ex) {
            System.out.println("problem in exit controller");
        }
    }
    @FXML
    void sendExit() {
        Client.sendRequest(logout());
        System.exit(0);
    }
}
