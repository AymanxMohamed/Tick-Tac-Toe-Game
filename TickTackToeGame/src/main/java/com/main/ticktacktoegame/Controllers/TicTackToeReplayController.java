/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;
import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ayman
 */
public class TicTackToeReplayController implements Initializable {

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;
    
    @FXML
    private Label playerX;
    
    @FXML
    private Label playerO;
    
    @FXML
    Button soundBtn;
    
    public static ArrayList<Button> buttons;
    public int index = 0;
    public static ArrayList<Integer> gameMoves;
    public static String replayEndMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
        disapleAllButtons();
    }


    @FXML
    void quitGame() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            Logger.getLogger(EndSingleModeGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            button.setDisable(true);
            button.setText("");
        });
    }

    public static void disapleAllButtons() {
        buttons.forEach(button -> button.setDisable(true));
    }

    @FXML
    public void DrawNextMove() {
        if (index == gameMoves.size()) {
            try {
                App.setRoot("gameReplayEnded");
                Label endGameMessage = (Label)App.scene.lookup("#endGameMessage");
                endGameMessage.setText(replayEndMessage);
                return;
            } catch (IOException ex) {
                System.out.println("problem in Tic Tac toe controller");
            }
        }
        int buttonIndex = gameMoves.get(index);
        if (index % 2 == 0) {
            // x moves 
            buttons.get(buttonIndex).setText("X");
            buttons.get(gameMoves.get(index)).getStyleClass().remove("oMove");
            buttons.get(gameMoves.get(index)).getStyleClass().add("xMove");
        } else {
            buttons.get(buttonIndex).setText("O");
            buttons.get(gameMoves.get(index)).getStyleClass().remove("xMove");
            buttons.get(gameMoves.get(index)).getStyleClass().add("oMove");
        }
        index++;
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
