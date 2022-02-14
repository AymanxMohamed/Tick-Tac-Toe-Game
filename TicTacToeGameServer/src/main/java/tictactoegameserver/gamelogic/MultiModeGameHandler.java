/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.gamelogic;

import java.util.ArrayList;
import tictactoegameserver.Network.PlayerHandler;
import tictactoegameserver.Database.DatabaseManager;
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
    private  boolean isGameEnded() {
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            moves.add(i, "");
        }
        for (int i = 0; i < gameMoves.size(); i++) {
            if  (i % 2 == 0) {
                // x moves
                moves.set(gameMoves.get(i), "x");
            }
            else {
                // O moves
                moves.set(gameMoves.get(i), "o");
            }
        }
        for (int a = 0; a < 8; a++) {
            String line;
              switch(a) {
                case 0:
                    line = moves.get(0) + moves.get(1) + moves.get(2);
                    break;
                case 1:
                    line = moves.get(3) + moves.get(4) + moves.get(5);
                    break;
                case 2:
                    line = moves.get(6) + moves.get(7) + moves.get(8);
                    break;
                case 3:
                    line = moves.get(0) + moves.get(3) + moves.get(6);
                    break;
                case 4:
                    line = moves.get(1) + moves.get(4) + moves.get(7);
                    break;
                case 5:
                    line = moves.get(2) + moves.get(5) + moves.get(8);
                    break;
                case 6:
                    line = moves.get(0) + moves.get(4) + moves.get(8);
                    break;
                case 7:
                     line = moves.get(6) + moves.get(4) + moves.get(2);
                    break;
                default:
                    line = null;
                    break;
            }
            if (line.equals("xxx") || line.equals("ooo")) {
                return true;
            }
        }
        return false;
    }    
    public void getMovesJsonObject(){
        for (var move : gameMoves){
            System.out.println(move);
        }
    }
    public void processMove(int index) {
        addMove(index);
        playerXHandler.sendResponse(drawMultiMovesResponse(gameMoves));
        playerOHandler.sendResponse(drawMultiMovesResponse(gameMoves));
        if (gameMoves.size() >= 5 && isGameEnded()) {
            handleEndGame();
            return;
        }
        if (gameMoves.size() == 9) {
            handleEndGame();
            return;
        }
        playerXHandler.sendResponse(removeMultiButtonResponse(index));
        playerOHandler.sendResponse(removeMultiButtonResponse(index));
        if (playerXTurn) {
             playerOHandler.sendResponse(enableMultiButtonsResponse());
        } else {
             playerXHandler.sendResponse(enableMultiButtonsResponse());
        }
        playerXTurn = !playerXTurn;
    }
    private void handleEndGame() {
        if (!isGameEnded()) {
            winner = "draw";
        }
        else if (playerXTurn) {
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
