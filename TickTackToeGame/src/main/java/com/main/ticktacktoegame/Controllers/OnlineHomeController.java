/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.invitePlayer;
import static com.main.ticktacktoegame.Network.RequestCreator.invitePlayerForChat;
import com.main.ticktacktoegame.Network.ResponseHandler;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
//import static com.main.ticktacktoegame.Utilites.AudioPlayer.changeAudio;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    TableView<Opponent> onlinePlayersTable;

    @FXML
    TableColumn<Opponent, String> playerName;

    @FXML
    TableColumn<Opponent, Integer> bonusPoints;

    @FXML
    TableColumn<Opponent, String> playerRank;

    @FXML
    TableColumn<Opponent, String> isOnline;

    @FXML
    TableColumn<Opponent, String> InGame;

    @FXML
    TableColumn<Opponent, String> InChat;

    @FXML
    Button soundBtn;

    ObservableList<Opponent> opponentList = FXCollections.observableArrayList(Opponent.opponentPlayers);

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
        ResponseHandler.onlineController = this;
        usernameLabel.setText(Client.player.getUserName());
        bonusPointsLabel.setText(String.valueOf(Client.player.getBonusPoints()));
        rankLabel.setText(Client.player.getPlayerRank());
        refreshTable();
//        AudioPlayer.changeAudio("welcomeSound.wav");
    }

    public void refreshTable() {
        new Thread(() -> {
            while (true) {
                refresh();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();
    }

    private void refresh() {
        if (!Opponent.opponentPlayers.isEmpty()) {
            playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
            bonusPoints.setCellValueFactory(new PropertyValueFactory<>("bonusPoints"));
            playerRank.setCellValueFactory(new PropertyValueFactory<>("playerRank"));
            InGame.setCellValueFactory(new PropertyValueFactory<>("inGameText"));
            InChat.setCellValueFactory(new PropertyValueFactory<>("inChatText"));
            isOnline.setCellValueFactory(new PropertyValueFactory<>("isOnlineText"));
            onlinePlayersTable.setItems(opponentList);
        } else {
            onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
        }
    }

    @FXML
    public void exit() {
        try {
            App.setRoot("exitView");
        } catch (IOException ex) {
            Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void sendInvitationForChat() {
        Opponent selectedOpponent = onlinePlayersTable.getSelectionModel().getSelectedItem();
        if (selectedOpponent != null) {
            System.out.println(selectedOpponent.getPlayerName());
            if (selectedOpponent.isOnline() && !selectedOpponent.isInChat() && !selectedOpponent.isInGame()) {
                System.out.println(selectedOpponent.isOnline());
                Client.opponnentName = selectedOpponent.getPlayerName();
                Client.sendRequest(invitePlayerForChat(selectedOpponent.getPlayerName()));
            } else {
                try {
                    if (!selectedOpponent.isOnline()) {
                        App.setRoot("PlayerIsOfflineView");
                    } else if (selectedOpponent.isInGame()) {
                        App.setRoot("PlayerIsCurrentlyInGameView");
                    } else {
                        App.setRoot("PlayerIsCurrentlyInChatView");
                    }
                    Label playerName = (Label) App.scene.lookup("#playerName");
                    playerName.setText(selectedOpponent.getPlayerName());
                } catch (IOException ex) {
                    Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @FXML
    public void sendInvitationForPlay() {
        Opponent selectedOpponent = onlinePlayersTable.getSelectionModel().getSelectedItem();
        if (selectedOpponent != null) {
            System.out.println(selectedOpponent.getPlayerName());
            if (selectedOpponent.isOnline() && !selectedOpponent.isInChat() && !selectedOpponent.isInGame()) {
                Client.opponnentName = selectedOpponent.getPlayerName();
                Client.sendRequest(invitePlayer(selectedOpponent.getPlayerName()));
            } else {
                try {
                    if (!selectedOpponent.isOnline()) {
                        App.setRoot("PlayerIsOfflineView");
                    } else if (selectedOpponent.isInGame()) {
                        App.setRoot("PlayerIsCurrentlyInGameView");
                    } else {
                        App.setRoot("PlayerIsCurrentlyInChatView");
                    }
                    Label playerName2 = (Label) App.scene.lookup("#playerName");
                    playerName2.setText(selectedOpponent.getPlayerName());
                } catch (IOException ex) {
                    System.out.println("problem in onlineHomeController");
                }
            }
        }
    }

    @FXML
    public void toogleAudio() {
        if (soundBtn.getStyleClass().contains("muteSound")) {
            soundBtn.getStyleClass().remove("muteSound");
            soundBtn.getStyleClass().add("unmuteSound");
        } else {
            soundBtn.getStyleClass().remove("unmuteSound");
            soundBtn.getStyleClass().add("muteSound");
        }
        AudioPlayer.toogleAudio();
    }

}
