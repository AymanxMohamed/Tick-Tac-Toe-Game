/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.SingleModeGameModel;
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
                System.out.println("problem in end single mode game");
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
                System.out.println("problem in end single mode game");
        }
    }
    
    @FXML
    void goToWelcomeView() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
                System.out.println("problem in end single mode game");
        }
    }
    
    
    @FXML
    void goToTicTackToeReplay() {
         try {
            App.setRoot("TicTackToeReplay");
            Label playerXLabel = (Label)App.scene.lookup("#playerX");
            Label playerOLabel = (Label)App.scene.lookup("#playerO");
            
            if (Client.cuurentCase.equals("X")) {
                playerXLabel.setText(Client.player.getUserName());
                playerOLabel.setText("Computer");
            } else {
                playerOLabel.setText(Client.player.getUserName());
                playerXLabel.setText("Computer");
            }
        } catch (IOException ex) {
            System.out.println("problem in end single mode game");
        }
    }

}
