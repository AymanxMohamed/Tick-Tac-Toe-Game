package com.main.ticktacktoegame.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author elsho
 */
public class WelcomeController implements Initializable {

    @FXML
    Label usernameLabel;
    @FXML
    Label bonusPointsLabel;
    @FXML
    Label rankLabel;
//    @FXML Label registerDateLabel;

    @FXML
    Label usernameWelcomeLabel;

//    @FXML static Label onlinePlayer0;
    @FXML
    private void switchToPlayModeView() {
        try {
            App.setRoot("ChooseModeView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void switchToOnlineView() {
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void exit() {
        try {
            App.setRoot("exitView");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Client.player.getUserName());
        bonusPointsLabel.setText(String.valueOf(Client.player.getBonusPoints()));
        rankLabel.setText(Client.player.getPlayerRank());
        usernameWelcomeLabel.setText(Client.player.getUserName());
    }
    
    @FXML
    public void goToSingleModeHistory() {
        try {
//          App.setRoot("singleModeHistoryView");
            App.setRoot("multiModeHistoryView");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
