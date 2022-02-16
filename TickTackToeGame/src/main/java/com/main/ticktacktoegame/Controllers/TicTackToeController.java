/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.forceEndSingleGame;
import static com.main.ticktacktoegame.Network.RequestCreator.singleMove;
import com.main.ticktacktoegame.Network.ResponseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.swing.SwingUtilities;

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
    private Text winnerText;

    public ArrayList<Button> buttons;

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
        Client.currentGame = this;
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }

    @FXML
    void quitGame() {
        Client.sendRequest(forceEndSingleGame());
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            button.setDisable(true);
        });
    }

    public void setButtonText(Button button, String text) {
        button.setText(text);
    }

    public void disapleAllButtons() {
        buttons.forEach(button -> button.setDisable(true));
    }

    public void disableButton(int index) {
        System.out.println("button " + index + " disabled");
//        buttons.get(index).setDisable(true);
    }

    public void enableAllButtons() {
        buttons.forEach(button -> {
            if (button.getText().equals("")) {
                button.setDisable(false);
            } else {
                button.setDisable(true);
            }
        });
    }

    public void drawMoves(ArrayList<Integer> gameMoves) {
        int buttonIndex;
        for (int i = 0; i < gameMoves.size(); i++) {
            if (i % 2 == 0) {
                // x moves
                buttons.get(gameMoves.get(i)).getStyleClass().add("xMove");
                buttonIndex = gameMoves.get(i);
                System.out.println("place X on Button " + buttonIndex);
                buttons.get(buttonIndex).setText("X");

            } else {
                // O moves
                buttons.get(gameMoves.get(i)).getStyleClass().add("oMove");
                buttonIndex = gameMoves.get(i);
                buttons.get(buttonIndex).setText("O");
            }
        }
    }

    @FXML
    void send0Move() {
        Client.sendRequest(singleMove(0, Client.singleModeGameID));
    }

    @FXML
    void send1Move() {
        Client.sendRequest(singleMove(1, Client.singleModeGameID));
    }

    @FXML
    void send2Move() {
        Client.sendRequest(singleMove(2, Client.singleModeGameID));
    }

    @FXML
    void send3Move() {
        Client.sendRequest(singleMove(3, Client.singleModeGameID));
    }

    @FXML
    void send4Move() {
        Client.sendRequest(singleMove(4, Client.singleModeGameID));
    }

    @FXML
    void send5Move() {
        Client.sendRequest(singleMove(5, Client.singleModeGameID));
    }

    @FXML
    void send6Move() {
        Client.sendRequest(singleMove(6, Client.singleModeGameID));
    }

    @FXML
    void send7Move() {
        Client.sendRequest(singleMove(7, Client.singleModeGameID));
    }

    @FXML
    void send8Move() {
        Client.sendRequest(singleMove(8, Client.singleModeGameID));
    }

}
