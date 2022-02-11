/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Models.Enums;

import static com.main.ticktacktoegame.Models.Enums.DIFFICULTY.*;
import static com.main.ticktacktoegame.Models.Enums.GAME_TYPE.*;
import static com.main.ticktacktoegame.Models.Enums.PLAYER_CASE.*;
import static com.main.ticktacktoegame.Models.Enums.PLAYER_RANK.*;

/**
 * @author ayman
 */

public class MappingFunctions {
    
    public static String mapPlayerCaseInSingle(PLAYER_CASE playerCase) {
        String databaseCase = null;
        switch (playerCase) {
            case WINNER:
                databaseCase = "winner";
                break;
            case LOSER:
                databaseCase = "loser";
                break;
            case TIED:
                databaseCase = "tied";
                break;
        }
        return databaseCase;
    }
    
        public static PLAYER_CASE mapPlayerCaseInSingle(String databasePlayerCase) {
        PLAYER_CASE playerCase = null;
        switch (databasePlayerCase) {
            case "winner":
                playerCase = WINNER;
                break;
            case "loser":
                playerCase = LOSER;
                break;
            case "tied":
                playerCase = TIED;
                break;
        }
        return playerCase;
    }
    public static String mapPlayerCaseInMulti(PLAYER_CASE firstPlayerCase, String firstPlayerName, String secondPLayerName) {
        String databaseCase = null;
        switch (firstPlayerCase) {
            case WINNER:
                databaseCase = firstPlayerName;
                break;
            case LOSER:
                databaseCase = secondPLayerName;
                break;
            case TIED:
                databaseCase = "tied";
                break;
        }
        return databaseCase;
    }
    public static DIFFICULTY mapDifficulty(String databaseDifficulty)
    {
        DIFFICULTY difficulty = null;
        switch(databaseDifficulty) {
            case "easy":
                difficulty = EASY;
                break;
            case "medium":
                difficulty = MEDIUM;
                break;
            case "hard":
                difficulty = HARD;
                break;
        }
        return difficulty;
    }
    
    public static String mapDifficulty(DIFFICULTY difficulty)
    {
        String databaseDifficulty = null;
        switch(difficulty) {
            case EASY:
                databaseDifficulty = "easy";
                break;
            case MEDIUM:
                databaseDifficulty = "medium";
                break;
            case HARD:
                databaseDifficulty = "hard";
                break;
        }
        return databaseDifficulty;
    }
    public static String mapGameType(GAME_TYPE gameType)
    {
        String databaseGameType = null;
        switch(gameType)
        {
            case LOCAL:
                databaseGameType = "local";
                break;
            case LAN:
                databaseGameType = "lan";
                break;
        }
        return databaseGameType;
    }
    public static GAME_TYPE mapGameType(String databaseGameType)
    {
        GAME_TYPE gameType = null;
        switch(databaseGameType)
        {
            case "local":
                gameType = LOCAL;
                break;
            case "lan":
                gameType = LAN;
                break;
        }
        return gameType;
    }
    public static PLAYER_RANK mapPlayerRank(String databasePlayerRank) {
        PLAYER_RANK playerRank = null;
        switch(databasePlayerRank) {
            case "Bronze":
                playerRank = BRONZE;
                break;
            case "Silver":
                playerRank = SILVER;
                break;
            case "Gold":
                playerRank = GOLD;
                break;
            case "Platinum":
                playerRank = PLATINUM;
                break;
            case "Master":
                playerRank = MASTER;
                break;
            case "Grand Master":
                playerRank = GRAND_MASTER;
                break;
            case "Challenger":
                playerRank = CHALLENGER;
                break;
        }
        return playerRank;
    }
    public static String mapPlayerRank(PLAYER_RANK playerRank) {
        String databasePlayerRank = null;
        switch(playerRank) {
            case BRONZE:
                databasePlayerRank = "Bronze";
                break;
            case SILVER:
                databasePlayerRank = "Silver";
                break;
            case GOLD:
                databasePlayerRank = "Gold";
                break;
            case PLATINUM:
                databasePlayerRank = "Platinum";
                break;
            case MASTER:
                databasePlayerRank = "Master";
                break;
            case GRAND_MASTER:
                databasePlayerRank = "Grand Master";
                break;
            case CHALLENGER:
                databasePlayerRank = "Challenger";
                break;
        }
        return databasePlayerRank;
    }
}
