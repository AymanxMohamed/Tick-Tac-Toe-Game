/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;
import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.multiMove;
import static com.main.ticktacktoegame.Network.RequestCreator.singleMove;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static ArrayList<Button> buttons;
    
    public static ArrayList<Integer> gameMoves;

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
        drawMoves(gameMoves);
    }


    @FXML
    void quitGame() {
        try {
            App.setRoot("onlineHome");
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


    public static void drawMoves(ArrayList<Integer> gameMoves) {
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println();
            }
        };
        timer.schedule(task, 1000);
        int buttonIndex;
        for (int i = 0; i < gameMoves.size(); i++) {
            if (i % 2 == 0) {
                // x moves
                buttonIndex = gameMoves.get(i);
                buttons.get(buttonIndex).setText("X");
                buttons.get(gameMoves.get(i)).getStyleClass().add("xMove");
            } else {
                // O moves
                buttonIndex = gameMoves.get(i);
                buttons.get(buttonIndex).setText("O");
                buttons.get(gameMoves.get(i)).getStyleClass().add("oMove");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TicTackToeReplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            App.setRoot("gameReplayEnded");
        } catch (IOException ex) {
            Logger.getLogger(TicTackToeReplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
