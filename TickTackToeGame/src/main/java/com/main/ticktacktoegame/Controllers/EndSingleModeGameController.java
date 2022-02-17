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
public class EndSingleModeGameController {

    @FXML
    private Label endGameMessage;
    
    @FXML
    public void sendRequestAgain() {
        if (!Client.multiModeGameId.equals("")) {
            Client.sendRequest(invitePlayer(Client.opponnentName));
        }
        if (!Client.singleModeGameID.equals("")) {
            try {
                App.setRoot("chooseXorOSingle");
            } catch (IOException ex) {
                Logger.getLogger(EndSingleModeGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML void goToOnlineView() {
        if (!Client.singleModeGameID.equals(""))
            Client.singleModeGameID = "";
        if (!Client.multiModeGameId.equals(""))
            Client.multiModeGameId = "";
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            Logger.getLogger(EndSingleModeGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
