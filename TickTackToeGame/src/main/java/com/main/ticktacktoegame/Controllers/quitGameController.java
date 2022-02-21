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
public class quitGameController {

    @FXML 
    void sendForceEndSingleGame() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(forceEndSingleGame());
            Client.singleModeGameID = "";
        } else {
            Client.sendRequest(forceEndMultiGaame());
            Client.multiModeGameId = "";
        }
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            System.out.println("problem in quitGameController");
        }
    }
    @FXML
    public void cancel() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(cancelEndSingleGame());
        } else {
            Client.sendRequest(cancelEndMultiGame());
        }
    }
}
