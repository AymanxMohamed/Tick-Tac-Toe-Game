/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        // set Root To TicTackToeReplay
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
