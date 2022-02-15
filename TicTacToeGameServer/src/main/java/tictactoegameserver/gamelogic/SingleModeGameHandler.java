/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.gamelogic;

import tictactoegameserver.Database.Entities.Enums.MappingFunctions;


import java.util.ArrayList;
import tictactoegameserver.Database.DatabaseManager;
import tictactoegameserver.Database.Entities.Enums.DIFFICULTY;
import tictactoegameserver.Network.PlayerHandler;
import tictactoegameserver.Network.ResponseCreator;
import static tictactoegameserver.Network.ResponseCreator.drawSingleMovesResponse;
import static tictactoegameserver.Network.ResponseCreator.enableSingleButtonsResponse;
import static tictactoegameserver.Network.ResponseCreator.endMultiModeGameResponse;
import static tictactoegameserver.Network.ResponseCreator.removeSingleButtonResponse;
import static tictactoegameserver.Network.ResponseCreator.updateAvilablePlayersList;
import static tictactoegameserver.Network.ResponseCreator.updatePlayerDataResponse;

/**
 *
 * @author ayman
 */
public class SingleModeGameHandler {
    private final String gameID;
    private final PlayerHandler playerHandler;
    private final String playerType;
    private final DIFFICULTY difficulty;
    private  String playerCase;
    private final ArrayList<Integer> gameMoves;
    public static ArrayList<SingleModeGameHandler> currentGames = new ArrayList<>();
    private ArrayList<Integer> avilableMoves;
    
    public SingleModeGameHandler(String gameID, PlayerHandler playerHandler, String playerType, String difficulty) {
        this.gameID = gameID;
        this.playerHandler = playerHandler;
        this.playerType = playerType;
        this.difficulty = MappingFunctions.mapDifficulty(difficulty);
        this.playerCase = "";
        this.gameMoves = new ArrayList<>();
        avilableMoves = new ArrayList<>();
        avilableMoves.add(0);
        avilableMoves.add(1);
        avilableMoves.add(2);
        avilableMoves.add(3);
        avilableMoves.add(4);
        avilableMoves.add(5);
        avilableMoves.add(6);
        avilableMoves.add(7);
        avilableMoves.add(8);
        currentGames.add(this);
        if (playerType.equals("o")) {
            processAiMove();
        }
    }
    
    private void addMove(int index) {
        if (!gameMoves.contains(index)) {
            gameMoves.add(index);
        }
    }
    
    private  boolean isGameEnded() {
        if (gameMoves.size() < 5) { return false; }
        
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            moves.add(i, "");
        }
        for (int i = 0; i < gameMoves.size(); i++) {
            if  (i % 2 == 0) {
                // x moves
                // [1,3,6]
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
            if (line.equals("xxx")) {
                playerCase = playerType.equals("x") ? "winner" : "loser";
                return true;
            } else if (line.equals("ooo")) {
                playerCase = playerCase = playerType.equals("o") ? "winner" : "loser";
                return true;
            } else if (gameMoves.size() == 9) {
                playerCase = "draw";
                return true;
            }
        }
        return false;
    } 
    
    public void processMove(int index) {
        addMove(index);
        removeMove(index);
        playerHandler.sendResponse(drawSingleMovesResponse(gameMoves));
        playerHandler.sendResponse(removeSingleButtonResponse(index));
        if (isGameEnded()) {
            handleEndGame();
            return;
        }
        processAiMove();
    }
    private void processAiMove() {
        int aiIndex = generateMove();
        addMove(aiIndex);
        if (isGameEnded()) {
            handleEndGame();
            return;
        }
        playerHandler.sendResponse(drawSingleMovesResponse(gameMoves));
        playerHandler.sendResponse(removeSingleButtonResponse(aiIndex));
        playerHandler.sendResponse(enableSingleButtonsResponse());
    }
    private void handleEndGame() {
        if (playerCase.equals("winner")) {
            playerHandler.player.increaseBonusPoints();
        }
        else if (playerCase.equals("loser")) {
            playerHandler.player.decreaseBonusPoints();
        }
        DatabaseManager.addSingleModeGameRecord(gameID, playerHandler.player.getUserName(), playerType, difficulty, playerCase, ResponseCreator.createGameMovesJson(gameMoves));

        playerHandler.inGame = false;
        
        // addPlayerX and playerO To the avilable players
        ArrayList<String> XOPlayers = new ArrayList<>();
        XOPlayers.add(playerHandler.player.getUserName());
        
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(XOPlayers));
        
        playerHandler.sendResponse(updatePlayerDataResponse(playerHandler.player));
        

        playerHandler.sendResponse(endMultiModeGameResponse(playerCase));
        currentGames.remove(this);
    }

    private int generateMove() {
        switch(difficulty) {
            case EASY:
                return generateEasyMove();
            case MEDIUM:
                return generateMediumMove();
            case HARD:
                return generateHardMove();
        }
        return -1;
    }
    private void removeMove(int move) {
        avilableMoves.remove((Integer) move);
    }

    private int generateEasyMove() {
        int index = (int)(Math.random() * avilableMoves.size());
        int move = avilableMoves.get(index);
        removeMove(index);
        return move;
    }

    private int generateMediumMove() {
        int index = (int)(Math.random() * avilableMoves.size());
        int move = avilableMoves.get(index);
        removeMove(index);
        return move;
    }

    private int generateHardMove() {
        int index = (int)(Math.random() * avilableMoves.size());
        int move = avilableMoves.get(index);
        removeMove(index);
        return move;
    }
    public String getGameID() { return this.gameID; }
}
