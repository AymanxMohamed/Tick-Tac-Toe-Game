/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.invitePlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    TableView<Opponent> onlinePlayersTable;
    
    @FXML
    TableColumn<Opponent, String> playerName;

    @FXML
    TableColumn<Opponent, String> InGame;
    
    @FXML
    TableColumn<Opponent, String> InChat;
    
    ObservableList<Opponent> opponentList = FXCollections.observableArrayList(Opponent.onlinePlayers);
    
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
        if (!Opponent.onlinePlayers.isEmpty()) {
            playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
            InGame.setCellValueFactory(new PropertyValueFactory<>("inGameText"));
            InChat.setCellValueFactory(new PropertyValueFactory<>("inChatText"));
            onlinePlayersTable.setItems(opponentList);
        }
        else 
            onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
    }
    @FXML
    public void exit(){
        try {
            App.setRoot("exitView");
        } catch (IOException ex) {
            Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    public void goToAvilableToChatWith() {
        Opponent selectedOpponent = onlinePlayersTable.getSelectionModel().getSelectedItem();
        if (selectedOpponent != null) {
            System.out.println(selectedOpponent.getPlayerName());
              if (!selectedOpponent.isInChat() && !selectedOpponent.isInGame()) {
                Client.opponnentName = selectedOpponent.getPlayerName();
            } else {
                try {
                    if (selectedOpponent.isInGame()) {
                        App.setRoot("PlayerIsCurrentlyInGameView");
                    } else {
                        App.setRoot("PlayerIsCurrentlyInChatView");
                    }
                    Label playerName = (Label)App.scene.lookup("#playerName");
                    playerName.setText(selectedOpponent.getPlayerName());
                } catch (IOException ex) {
                    Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @FXML
    public void goToAvilableToPlayWith() {
        Opponent selectedOpponent = onlinePlayersTable.getSelectionModel().getSelectedItem();
        if (selectedOpponent != null) {
            System.out.println(selectedOpponent.getPlayerName());
              if (!selectedOpponent.isInChat() && !selectedOpponent.isInGame()) {
                Client.opponnentName = selectedOpponent.getPlayerName();
                Client.sendRequest(invitePlayer(selectedOpponent.getPlayerName()));
            } else {
                try {
                 if (selectedOpponent.isInGame()) {
                        App.setRoot("PlayerIsCurrentlyInGameView");
                    } else {
                        App.setRoot("PlayerIsCurrentlyInChatView");
                    }
                    Label playerName = (Label)App.scene.lookup("#playerName");
                    playerName.setText(selectedOpponent.getPlayerName());
                } catch (IOException ex) {
                    Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
