package com.main.ticktacktoegame.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author elsho
 */
public class WelcomeController {
    @FXML static Label usernameLabel = new Label();
    @FXML Label bonusPointsLabel;
    @FXML Label rankLabel;
    @FXML Label registerDateLabel;
    
//    public WelcomeController(){
//        usernameLabel.setText("Hi");
//    }
    public static void setText(){
        usernameLabel.setText(Client.player.getUserName());
    }
    @FXML
    private void switchToPlayModeView() throws IOException {
//        usernameLabel.setText("Hi");
        App.setRoot("TicTackToe");
//        App.setRoot("PlayModeView");
    }
}
