/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.MultiModeGameModel;
import com.main.ticktacktoegame.Models.SingleModeGameModel;
import com.main.ticktacktoegame.Network.Client;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
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
public class MultiModeHistoryController implements Initializable {

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
    TableView<MultiModeGameModel> multiModeHistoryTabel;

    @FXML
    TableColumn<MultiModeGameModel, String> gameDate;

    @FXML
    TableColumn<MultiModeGameModel, String> playerType;

    @FXML
    TableColumn<MultiModeGameModel, String> opponent;

    @FXML
    TableColumn<MultiModeGameModel, String> playerCase;

    @FXML
    Button soundBtn;

    ObservableList<MultiModeGameModel> multiModeHistory = FXCollections.observableArrayList(MultiModeGameModel.multiModeHistory);

    @FXML
    public void switchToWelcomeView() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            System.out.println("problem in multi mode history controller");
        }
    }

    @FXML
    public void SwitchToPreviousView() {
        try {
            App.setRoot("chooseHistoryMode");
        } catch (IOException ex) {
            System.out.println("problem in multi mode history controller");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Client.player.getUserName());
        bonusPointsLabel.setText(String.valueOf(Client.player.getBonusPoints()));
        rankLabel.setText(Client.player.getPlayerRank());
        refreshTable();
    }

    public void refreshTable() {
        new Thread(() -> {
            while (true) {
                refresh();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("problem in multi mode history controller");
                }
            }
        }).start();
    }

    private void refresh() {
        if (!SingleModeGameModel.singleModeHistory.isEmpty()) {
            gameDate.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
            playerType.setCellValueFactory(new PropertyValueFactory<>("playerType"));
            opponent.setCellValueFactory(new PropertyValueFactory<>("opponent"));
            playerCase.setCellValueFactory(new PropertyValueFactory<>("playerCase"));
            multiModeHistoryTabel.setItems(multiModeHistory);
        } else {
            multiModeHistoryTabel.setPlaceholder(new Label("You didn't play any multi mode game"));
        }
    }

    @FXML
    public void exit() {
        try {
            App.setRoot("exitView");
        } catch (IOException ex) {
            System.out.println("problem in multi mode history controller");
        }
    }

    @FXML
    public void playGame() {
        MultiModeGameModel selectedGame = multiModeHistoryTabel.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            selectedGame.playGame();
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
