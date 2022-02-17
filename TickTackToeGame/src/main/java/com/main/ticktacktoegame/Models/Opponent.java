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
    private String playerName;
    private boolean inGame;
    private boolean inChat;
    private String inGameText;
    private String inChatText;
    
    public Opponent(String playerName, boolean inGame, boolean inChat) {
        this.playerName = playerName;
        this.inGame = inGame;
        this.inChat = inChat;
        this.inGameText = inGame ? "yes" : "No";
        this.inChatText = inGame ? "yes" : "No";
    }
    
    public static ArrayList<Opponent> onlinePlayers = new ArrayList<>();
    
    public static Opponent getOpponent(String opponent) {
        for (var player : onlinePlayers) {
            if (player.playerName.equals(opponent)) {
                return player;
            }
        }
        return null;
    }
    
    public static void addOpponent(String playerName, boolean inGame, boolean inChat) {
       onlinePlayers.add(new Opponent(playerName, inGame, inChat));
    }
    public static void removeOpponent(String playerName) {
        onlinePlayers.remove(getOpponent(playerName));
    }

    public String getPlayerName() { return playerName; }
    public void togleInGameStatus() { 
        inGame = !inGame; 
        this.inGameText = inGame ? "yes" : "No";
    }
    public void toogleInChatStatus() { 
        inChat = !inChat; 
        this.inChatText = inGame ? "yes" : "No";
    }
    public String getInGameText() { return inGameText; }
    public String getInChatText() { return inChatText; }
    public boolean isInGame() { return inGame; }
    public boolean isInChat() { return inChat; }
}
