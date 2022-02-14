/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.gamelogic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import tictactoegameserver.Network.PlayerHandler;

/**
 *
 * @author ayman
 */
public class MultiModeGameHandler {
    private final String gameID;
    private final PlayerHandler playerXHandler;
    private final PlayerHandler playerOHandler;
    private final String winner;
    private final ArrayList<Integer> gameMoves;
    public static ArrayList<MultiModeGameHandler> currentGames = new ArrayList<>();
    
    public MultiModeGameHandler(String gameID, PlayerHandler playerXHandler, PlayerHandler playerOHandler) {
        this.gameID = gameID;
        this.playerXHandler = playerXHandler;
        this.playerOHandler = playerOHandler;
        this.winner = "";
        this.gameMoves = new ArrayList<>();
        currentGames.add(this);
    }
    public String getGameID() { return gameID; }
    public PlayerHandler getplayerXHandler() { return playerXHandler; }
    public PlayerHandler getplayerOHandler() { return playerOHandler; }
    public String getWinner() { return winner; }
    public ArrayList<Integer> getGameRecord() { return gameMoves; }
}
