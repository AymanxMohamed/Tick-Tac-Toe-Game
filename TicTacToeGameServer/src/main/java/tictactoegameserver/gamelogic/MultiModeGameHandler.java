/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.gamelogic;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Network.PlayerHandler;
import tictactoegameserver.Database.DatabaseManager;
import tictactoegameserver.Database.Entities.Player;
import tictactoegameserver.Network.ResponseCreator;
import static tictactoegameserver.Network.ResponseCreator.*;

/**
 *
 * @author ayman
 */
public class MultiModeGameHandler {
    private final String gameID;
    private final PlayerHandler playerXHandler;
    private final PlayerHandler playerOHandler;
    private String winner;
    private final ArrayList<Integer> gameMoves;
    public static ArrayList<MultiModeGameHandler> currentGames = new ArrayList<>();
    private boolean playerXTurn;
    
    public MultiModeGameHandler(String gameID, PlayerHandler playerXHandler, PlayerHandler playerOHandler) {
        this.gameID = gameID;
        this.playerXHandler = playerXHandler;
        this.playerOHandler = playerOHandler;
        this.winner = "";
        this.gameMoves = new ArrayList<>();
        currentGames.add(this);
        playerXTurn = true;
    }
    private void addMove(int index) {
        if (!gameMoves.contains(index)) {
            gameMoves.add(index);
        }
    }
    private boolean isGameEnded() {
        
        return false;
    }
    public void getMovesJsonObject(){
        for (var move : gameMoves){
            System.out.println(move);
        }
    }
    public void processMove(int index) {
        addMove(index);
        if (gameMoves.size() >= 5 && isGameEnded()) {
            handleEndGame();
        }
        if (playerXTurn) {
            
            
            
        } else {
            
        }
        
    }
    private void handleEndGame() {
        if (playerXTurn) {
            winner = playerXHandler.player.getUserName();
            playerXHandler.player.increaseBonusPoints();
            playerOHandler.player.decreaseBonusPoints();
        } else {
            winner = playerOHandler.player.getUserName();
            playerOHandler.player.increaseBonusPoints();
            playerXHandler.player.decreaseBonusPoints();
        }
        DatabaseManager.addMultiModeGameRecord(gameID, playerXHandler.player.getUserName(),playerOHandler.player.getUserName(), winner, ResponseCreator.createGameMovesJson(gameMoves));
        playerXHandler.inGame = false;
        playerOHandler.inGame = false;
        // addPlayerX and playerO To the avilable players
        ArrayList<String> XOPlayers = new ArrayList<>();
        XOPlayers.add(playerXHandler.player.getUserName());
        XOPlayers.add(playerOHandler.player.getUserName());
        
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(XOPlayers));
        
        playerXHandler.sendResponse(updatePlayerDataResponse(playerXHandler.player));
        
        playerOHandler.sendResponse(updatePlayerDataResponse(playerOHandler.player));

        playerXHandler.sendResponse(endMultiModeGameResponse(winner));
        playerOHandler.sendResponse(endMultiModeGameResponse(winner));
        currentGames.remove(this);
        
    }
      
    
    public String getGameID() { return gameID; }
    public PlayerHandler getplayerXHandler() { return playerXHandler; }
    public PlayerHandler getplayerOHandler() { return playerOHandler; }
    public String getWinner() { return winner; }
    public ArrayList<Integer> getGameRecord() { return gameMoves; }
}
