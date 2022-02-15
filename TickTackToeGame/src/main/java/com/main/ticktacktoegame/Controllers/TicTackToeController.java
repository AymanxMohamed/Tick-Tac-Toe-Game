/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
/**
 * FXML Controller class
 *
 * @author ayman
 */
public class TicTackToeController implements Initializable {

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
    private Button button0;

    @FXML
    private Text winnerText;

    private int playerTurn = 0;

    ArrayList<Button> buttons;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button0,button1,button2,button3,button4,button5,button6,button7,button8));
        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        button0.setStyle("xMove");
        button0.setText("XX");
        button1.setStyle("oMove");
        button1.setText("OO");
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        } else{
            button.setText("O");
            playerTurn = 0;
        }
    }

    public void checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line;
              switch(a) {
                case 0:
                    line = button0.getText() + button2.getText() + button1.getText();
                    break;
                case 1:
                    line = button4.getText() + button5.getText() + button3.getText();
                    break;
                case 2:
                    line = button7.getText() + button8.getText() + button6.getText();
                    break;
                case 3:
                    line = button0.getText() + button4.getText() + button8.getText();
                    break;
                case 4:
                    line = button2.getText() + button4.getText() + button6.getText();
                    break;
                case 5:
                    line = button0.getText() + button3.getText() + button6.getText();
                    break;
                case 6:
                    line = button1.getText() + button4.getText() + button7.getText();
                    break;
                case 7:
                    line =  button2.getText() + button5.getText() + button8.getText();
                    break;
                default:
                    line = null;
                    break;
            }
            //X winner
            if (line.equals("XXX")) {
                winnerText.setText("X WON!");
           }

            //O winner
           else if (line.equals("OOO")) {
                winnerText.setText("O WON!");
            }
        }
    }
}
    

