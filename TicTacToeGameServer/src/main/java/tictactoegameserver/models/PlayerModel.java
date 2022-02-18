/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.models;

import tictactoegameserver.Database.DatabaseManager;
import java.util.ArrayList;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import tictactoegameserver.Database.Entities.Player;

/**
 *
 * @author ayman
 */
public class PlayerModel {
    private final String playerName;
    private int bonusPoints;
    private String playerRank;
    private boolean inGame;
    private boolean inChat;
    private boolean isOnline;
    private String inGameText;
    private String inChatText;
    private String isOnlineText;
    
    public PlayerModel(String playerName, int bonusPoints, String playerRank) {
        this.playerName = playerName;
        this.bonusPoints = bonusPoints;
        this.playerRank = playerRank;
        
        this.isOnline = false;
        this.inGame = false;
        this.inChat = false;
        
        this.inGameText = inGame ? "yes" : "No";
        this.inChatText = inChat ? "yes" : "No";
        this.isOnlineText = this.isOnline ? "Online" : "Offline";
    }
    
    public static ArrayList<PlayerModel> playersList = new ArrayList<>();
    
    public static void addPlayer(String playerName, int bonusPoints, String playerRank) {
       playersList.add(new PlayerModel(playerName, bonusPoints, playerRank));
    }
    public static PlayerModel getPlayerModel(String opponent) {
        for (var player : playersList) {
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
    
    public static void initilizePlayersList() {
        DatabaseManager.openDataBaseConnection();
        ArrayList<Player> playersList =  DatabaseManager.getAllPlayers();
        if (playersList != null) {
            for (var player : playersList) {
                addPlayer(player.getUserName(), player.getBonusPoints(), MappingFunctions.mapPlayerRank(player.getPlayerRank()));
            }
        }
        DatabaseManager.closeDataBaseConnection();
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
