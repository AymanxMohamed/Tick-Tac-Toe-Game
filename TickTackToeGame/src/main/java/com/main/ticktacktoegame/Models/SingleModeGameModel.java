/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Models;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Controllers.TicTackToeReplayController;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.Utility.getIntegerArray;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ayman
 */
public class SingleModeGameModel {
    private final String gameDate;
    private final String playerType;
    private final String difficulty;
    private final String playerCase;
    private final String gameMovesJsonString;
    
    public static ArrayList<SingleModeGameModel> singleModeHistory;
    
    public static void addSingleModeGame(String gameDate, String playerType, String difficulty, String player_case, String gameMovesJsonString) {
        singleModeHistory.add(new SingleModeGameModel(gameDate, playerType, difficulty, player_case, gameMovesJsonString));
    }
    public SingleModeGameModel(String gameDate, String playerType, String difficulty, String player_case, String gameMovesJsonString) 
    {
        this.gameDate = gameDate;
        this.playerType = playerType;
        this.difficulty = difficulty;
        this.playerCase = player_case;
        this.gameMovesJsonString = gameMovesJsonString;
    }

    public String getGameDate() {
        return gameDate;
    }
    public String getPlayerType() {
        return playerType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getPlayerCase() {
        return playerCase;
    }

    public String getGameMovesJsonString() {
        return gameMovesJsonString;
    }
    public void playGame() {
        Client.cuurentCase = playerType;

        JSONObject data = (JSONObject) JSONValue.parse(gameMovesJsonString);
        ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
        TicTackToeReplayController.gameMoves = getIntegerArray(objectArray);
        if (playerCase.equals("winner")) {
            TicTackToeReplayController.replayEndMessage = "You were Winner";
        } else if (playerCase.equals("draw")){
            TicTackToeReplayController.replayEndMessage = "The Game was draw";
        } else {
            TicTackToeReplayController.replayEndMessage = "You were loser";
        }
        try {
            App.setRoot("TicTackToeReplay");
            Label playerXLabel = (Label)App.scene.lookup("#playerX");
            Label playerOLabel = (Label)App.scene.lookup("#playerO");
            
            if (Client.cuurentCase.equals("X")) {
                playerXLabel.setText(Client.player.getUserName());
                playerOLabel.setText("Computer");
            } else {
                playerOLabel.setText(Client.player.getUserName());
                playerXLabel.setText("Computer");
            }
        } catch (IOException ex) {
            Logger.getLogger(SingleModeGameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
    }
    
    public void printData() {
        System.out.println("############################################");
        System.out.println("gameDate: "  + gameDate);
        System.out.println("playerType: "  + playerType);
        System.out.println("difficulty: "  + difficulty);
        System.out.println("playerCase: "  + playerCase);
        System.out.println("gameMovesJsonString: "  + gameMovesJsonString);
        System.out.println("############################################");
    }
}
