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
