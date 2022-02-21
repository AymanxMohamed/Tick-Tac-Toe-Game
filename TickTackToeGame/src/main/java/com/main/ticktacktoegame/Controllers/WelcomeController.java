package com.main.ticktacktoegame.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.main.ticktacktoegame.App;
import static com.main.ticktacktoegame.Controllers.TicTackToeController.buttons;
import com.main.ticktacktoegame.Network.Client;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    Button soundBtn;

//    @FXML static Label onlinePlayer0;
    @FXML
    private void switchToSingleMode() {
        switchToChooseDifficultyView();

    }

    public void switchToChooseDifficultyView() {
        try {
            App.setRoot("chooseLevelView");
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
    private void switchToChooseHistoryModeView() {
        try {
            App.setRoot("chooseHistoryMode");
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
        //        AudioPlayer.changeAudio("welcomeSound.wav");
    }

    @FXML
    public void goToSingleModeHistory() {
        try {
//          App.setRoot("singleModeHistoryView");
            App.setRoot("multiModeHistoryView");
        } catch (IOException ex) {
            System.out.println("problem in WelcomeController");
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
