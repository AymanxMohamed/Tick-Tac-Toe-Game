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
public class MultiModeGameModel {
    private final String gameDate;
    private final String playerType;
    private final String opponent;
    private final String playerCase;
    private final String gameRecordJsonString;
    
    public static ArrayList<MultiModeGameModel> multiModeHistory;
    
    public static void addMultiModeGame(String gameDate, String playerType, String opponent, String playerCase, String gameRecordJsonString) {
        multiModeHistory.add(new MultiModeGameModel(gameDate, playerType, opponent, playerCase, gameRecordJsonString));
    }
    
    public MultiModeGameModel(String gameDate, String playerType, String opponent, String playerCase, String gameRecordJsonString) {
        this.gameDate = gameDate;
        this.playerType = playerType;
        this.opponent = opponent;
        this.playerCase = playerCase;
        this.gameRecordJsonString = gameRecordJsonString;
    }
    
    public String getGameDate() {
        return gameDate;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getPlayerCase() {
        return playerCase;
    }

    public String getGameRecordJsonString() {
        return gameRecordJsonString;
    }
    public void playGame() {
        Client.cuurentCase = playerType;
        
        JSONObject data = (JSONObject) JSONValue.parse(gameRecordJsonString);
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
                playerOLabel.setText(opponent);
            } else {
                playerOLabel.setText(Client.player.getUserName());
                playerXLabel.setText(opponent);
            }
        } catch (IOException ex) {
            Logger.getLogger(SingleModeGameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printData() {
        System.out.println("############################################");
        System.out.println("gameDate: "  + gameDate);
        System.out.println("playerType: "  + playerType);
        System.out.println("opponent: "  + opponent);
        System.out.println("playerCase: "  + playerCase);
        System.out.println("gameRecordJsonString: "  + gameRecordJsonString);
        System.out.println("############################################");
    }
}
