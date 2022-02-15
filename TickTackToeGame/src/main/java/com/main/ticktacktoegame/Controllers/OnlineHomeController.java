/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class OnlineHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label usernameLabel;
    @FXML
    Label bonusPointsLabel;
    @FXML
    Label rankLabel;
//    @FXML Label registerDateLabel;

    @FXML
    TableView onlinePlayersTable;

    @FXML
    public void switchToWelcomeView() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Client.player.getUserName());
        bonusPointsLabel.setText(String.valueOf(Client.player.getBonusPoints()));
        rankLabel.setText(Client.player.getPlayerRank());
        onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
        
//        registerDateLabel.setText(Client.player.getRegisterDate());
    }

}
