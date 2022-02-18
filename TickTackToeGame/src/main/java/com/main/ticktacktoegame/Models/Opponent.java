/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Models;

import java.util.ArrayList;

/**
 *
 * @author ayman
 */
public class Opponent {
    private final String playerName;
    private int bonusPoints;
    private String playerRank;
    private boolean inGame;
    private boolean inChat;
    private boolean isOnline;
    private String inGameText;
    private String inChatText;
    private String isOnlineText;
    
    public Opponent(String playerName, int bonusPoints, String playerRank, String playerStatus,boolean inGame, boolean inChat) {
        this.playerName = playerName;
        this.bonusPoints = bonusPoints;
        this.playerRank = playerRank;
        
        this.isOnline = playerStatus.equals("Online");
        this.inGame = inGame;
        this.inChat = inChat;
        
        this.inGameText = inGame ? "yes" : "No";
        this.inChatText = inChat ? "yes" : "No";
        this.isOnlineText = playerStatus;
    }
    
    public static ArrayList<Opponent> opponentPlayers = new ArrayList<>();
    

    
    public static void addOpponent(String playerName, int bonusPoints, String playerRank, String playerStatus,boolean inGame, boolean inChat) {
       opponentPlayers.add(new Opponent(playerName, bonusPoints, playerRank, playerStatus, inGame, inChat));
    }
    public static void removeOpponent(String playerName) {
        opponentPlayers.remove(getOpponent(playerName));
    }
    public static Opponent getOpponent(String opponent) {
        for (var player : opponentPlayers) {
            if (player.playerName.equals(opponent)) {
                return player;
            }
        }
        return null;
    }
    public void togleInGameStatus() { 
        inGame = !inGame; 
        this.inGameText = inGame ? "yes" : "No";
    }
    public void toogleInChatStatus() { 
        inChat = !inChat; 
        this.inChatText = inChat ? "yes" : "No";
    }
    public void tooglePlayerStatus() {
        isOnline = !isOnline;
        this.isOnlineText = isOnline ? "Online" : "Offline";
        if (!isOnline) {
            inGame = false;
            inChat = false;
            this.inChatText = inChat ? "yes" : "No";
            this.inGameText = inGame ? "yes" : "No";
        } 
    }
    public void setPlayerRank(String value) { playerRank = value; }
    public void setBonusPoints(int value) { bonusPoints = value; }
    public String getPlayerName() { return playerName; }
    public String getInGameText() { return inGameText; }
    public String getInChatText() { return inChatText; }
    public String getIsOnlineText() { return isOnlineText; }
    public int getBonusPoints() { return bonusPoints; }
    public String getPlayerRank() { return playerRank; }
    public boolean isOnline() { return isOnline; }
    public boolean isInGame() { return inGame; }
    public boolean isInChat() { return inChat; }
}
