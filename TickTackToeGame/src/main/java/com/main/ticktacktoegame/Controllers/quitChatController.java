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
public class quitChatController {

    public static StringBuilder fieldContent = null;
    @FXML 
    void sendForceEndSingleGame() {
        Client.sendRequest(leaveChat());
        fieldContent = null;
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            System.out.println("problem in quitChatController");
        }
    }
    @FXML
    public void cancel() {
        try {
            App.setRoot("chat");
        } catch (IOException ex) {
            System.out.println("problem in quitGameController");
        }
    }
}
