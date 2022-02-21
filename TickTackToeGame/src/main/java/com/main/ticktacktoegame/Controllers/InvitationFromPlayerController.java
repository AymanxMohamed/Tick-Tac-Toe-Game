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
public class InvitationFromPlayerController {

    @FXML
    private Label playerName;
    
    @FXML
    public void rejectGameInvitation() {
        Client.sendRequest(rejectInvitation());
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            System.out.println("problem in invitation from player controller");
        }
    }
       @FXML
    public void sendRejectChatInvitation() {
        Client.sendRequest(rejectChatInvitation());
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            System.out.println("problem in invitation from player controller");
        }
    }
    
    @FXML
    public void acceptGameInvitation() {
        Client.sendRequest(acceptInvitation());
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            System.out.println("problem in invitation from player controller");
        }
    }
    @FXML
    public void sendAcceptChatInvitation() {
        Client.sendRequest(acceptChatInvitation());
    }
    @FXML void goToOnlinePlayersView() {
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            System.out.println("problem in invitation from player controller");
        }
    }

}
