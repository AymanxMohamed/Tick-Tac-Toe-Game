/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.multiMove;
import static com.main.ticktacktoegame.Network.RequestCreator.singleMove;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ayman
 */
public class TicTackToeController implements Initializable {

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
    }

    @FXML
    void quitGame() {
        try {
            if (!Client.singleModeGameID.equals("")) {
                App.setRoot("Force End Game");
            } else {
                App.setRoot("Force End Game");
            }
        } catch (IOException ex) {
            Logger.getLogger(TicTackToeController.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void enableAllButtons() {
        buttons.forEach(button -> {
            if (button.getText().equals("")) {
                button.setDisable(false);
            } else {
                button.setDisable(true);
            }
        });
    }

    public static void drawMoves(ArrayList<Integer> gameMoves) {
        int buttonIndex;
        for (int i = 0; i < gameMoves.size(); i++) {
            if (i % 2 == 0) {
                // x moves
                buttonIndex = gameMoves.get(i);
                buttons.get(buttonIndex).setText("X");
                buttons.get(gameMoves.get(i)).getStyleClass().remove("oMove");
                buttons.get(gameMoves.get(i)).getStyleClass().add("xMove");
            } else {
                // O moves
                buttonIndex = gameMoves.get(i);
                buttons.get(buttonIndex).setText("O");
                buttons.get(gameMoves.get(i)).getStyleClass().remove("xMove");
                buttons.get(gameMoves.get(i)).getStyleClass().add("oMove");
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

    @FXML
    void send0Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(0));
        } else {
            Client.sendRequest(multiMove(0));
        }

    }

    @FXML
    void send1Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(1));
        } else {
            Client.sendRequest(multiMove(1));
        }
    }

    @FXML
    void send2Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(2));
        } else {
            Client.sendRequest(multiMove(2));
        }
    }

    @FXML
    void send3Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(3));
        } else {
            Client.sendRequest(multiMove(3));
        }
    }

    @FXML
    void send4Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(4));
        } else {
            Client.sendRequest(multiMove(4));
        }
    }

    @FXML
    void send5Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(5));
        } else {
            Client.sendRequest(multiMove(5));
        }
    }

    @FXML
    void send6Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(6));
        } else {
            Client.sendRequest(multiMove(6));
        }
    }

    @FXML
    void send7Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(7));
        } else {
            Client.sendRequest(multiMove(7));
        }
    }

    @FXML
    void send8Move() {
        if (!Client.singleModeGameID.equals("")) {
            Client.sendRequest(singleMove(8));
        } else {
            Client.sendRequest(multiMove(8));
        }
    }
    
}
