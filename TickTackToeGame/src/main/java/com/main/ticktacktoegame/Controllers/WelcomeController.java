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
    @FXML Label usernameLabel;
    @FXML Label bonusPointsLabel;
    @FXML Label rankLabel;
    @FXML Label registerDateLabel;
    
    @FXML
    private void switchToPlayModeView() {
        try {
            App.setRoot("ChooseModeView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Client.player.getUserName());
        bonusPointsLabel.setText(String.valueOf(Client.player.getBonusPoints()));
        rankLabel.setText(Client.player.getPlayerRank());
        registerDateLabel.setText(Client.player.getRegisterDate());
    }
}
