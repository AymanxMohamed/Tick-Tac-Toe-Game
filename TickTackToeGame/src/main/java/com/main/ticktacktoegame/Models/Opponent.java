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
    
    
    public Opponent(String playerName, boolean inGame, boolean inChat) {
        this.playerName = playerName;
        this.inGame = inGame;
        this.inChat = inChat;
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
    public void togleInGameStatus() { inGame = !inGame; }
    public void toogleInChatStatus() { inChat = !inChat; }
}
