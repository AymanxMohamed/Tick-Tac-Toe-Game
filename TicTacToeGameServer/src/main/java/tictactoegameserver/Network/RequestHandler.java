/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.DatabaseManager;
import static tictactoegameserver.Network.ResponseCreator.*;
import static tictactoegameserver.Network.Utility.*;
import  tictactoegameserver.gamelogic.MultiModeGameHandler;
import tictactoegameserver.gamelogic.SingleModeGameHandler;
/**
 *
 * @author ayman, shopaky
 */
public class RequestHandler {

    public static String handleRequest(String reqeustString, PlayerHandler playerHandler) {
        // this function will take a request and return a respons String
        String response = null;

        JSONObject requestObject = (JSONObject) JSONValue.parse(reqeustString);
        String request = (String) requestObject.get("request");
        JSONObject data = (JSONObject) requestObject.get("data");

        switch (request) {
            case "login":
                return handleLogin(data, playerHandler);
            case "register":
                return handleRegister(data);
            case "game invitation":
                return handleGameInvitation(data);
            case "acceptInvitation":
                return handleAcceptInvitation(data);
            case "rejectInvitation":
                return handleRejectInvitation(data);
            case "XorOChoise":
                return handleXOrOChoise(data);
            case "multiMove":
                return handleMultiModeGameMove(data);
            case "play single mode game":
                return handlePlaySingleModeGame(data, playerHandler);
            case "singleMove":
                return handleSingleModeGameMove(data, playerHandler);
        }
        return response;
    }
    
    /*_____ * _____ Login & Register Requests _____ * _____ */
    
    private static String handleLogin(JSONObject data, PlayerHandler playerHandler) {

        String userName = (String) data.get("username");
        String password = (String) data.get("password");

        DatabaseManager.openDataBaseConnection();
        if (!DatabaseManager.isPlayerExist(userName)) {
            return playerNotExistResponse();
        }
        if (!DatabaseManager.isValidPlayer(userName, password)) {
            return wrongPasswordResponse();
        }
        playerHandler.player = DatabaseManager.getPlayer(userName);
        DatabaseManager.togglePlayerStatus(playerHandler.player);
        DatabaseManager.closeDataBaseConnection();
        playerHandler.sendResponse(loginSuccessResponse(userName, playerHandler.player));
        PlayerHandler.broadcastResponse(addNewPlayerResponse(userName));
        return onlinePlayersListResponse();
    }
    
    public static String handleRegister(JSONObject data) {

        String userName = (String) data.get("username");
        String password = (String) data.get("password");
        DatabaseManager.openDataBaseConnection();
        if (DatabaseManager.isPlayerExist(userName)) {
            return playerExistResponse();
        
        } else {
            DatabaseManager.addNewPlayer(userName, password);
        }
        DatabaseManager.closeDataBaseConnection();
        return registerSuccessResponse();
    }
    /*_____ * _____ end of Login & Register Requests _____ * _____ */
    
    /*_____ * _____ Multi Mode Game Requests _____ * _____ */
    
    private static String handleGameInvitation(JSONObject data) {
        String invitationReciever = (String) data.get("invitationReciever");
        PlayerHandler receiverHandler = getPlayerHandler(invitationReciever);
        
        if (receiverHandler.inGame) {
            return playerInGameResponse(invitationReciever);
        }
        receiverHandler.sendResponse(invitationFromPlayerRequest(data));
        return invitationSendedResponse(data);
    }
             /*_____ * _____ receiver _____ * _____ */
    
    private static String handleAcceptInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        PlayerHandler senderHandler = getPlayerHandler(invitationSender);
        senderHandler.sendResponse(chooseXOrOResponse(data));
        return doNothingResponse();
    }
    
    private static String handleRejectInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");        
        PlayerHandler senderHandler = getPlayerHandler(invitationSender);
        senderHandler.sendResponse(invitationRejectedResponse(data));
        return doNothingResponse();
    }
                /*_____ * _____ sender _____ * _____ */
    
    private static String handleXOrOChoise(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        String invitationReciever = (String) data.get("invitationReciever");
        String choise = (String) data.get("choise");
        PlayerHandler recieverHandler = getPlayerHandler(invitationReciever);
        PlayerHandler playerXHandler;
        PlayerHandler playerOHandler;
        if (choise.equals("X")) {
            playerXHandler = getPlayerHandler(invitationSender);
            playerOHandler = getPlayerHandler(invitationReciever);
        } else {
            playerXHandler = getPlayerHandler(invitationReciever);
            playerOHandler = getPlayerHandler(invitationSender);
        }
        
        String gameID = generateUniqueId();
        new MultiModeGameHandler(gameID, playerXHandler, playerOHandler);
        playerXHandler.inGame = true;
        playerOHandler.inGame = true;
        
        // send update avilable players request
        ArrayList<String> XOPlayers = new ArrayList<>();
        XOPlayers.add(playerXHandler.player.getUserName());
        XOPlayers.add(playerOHandler.player.getUserName());
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(XOPlayers));
        
        recieverHandler.sendResponse(startMultiModeGameResponse(gameID, playerXHandler.player.getUserName(), playerOHandler.player.getUserName()));
        playerOHandler.sendResponse(disapleAllButtonsResponse());
        return startMultiModeGameResponse(gameID, playerXHandler.player.getUserName(), playerOHandler.player.getUserName());
    }
                    /*_____ * _____ game process _____ * _____ */
    
    private static String handleMultiModeGameMove(JSONObject data) {
        String gameID = (String) data.get("gameId");
        int index = ((Long) data.get("index")).intValue();
        MultiModeGameHandler gameHandler = Utility.getMultiModeGameHandler(gameID);
        gameHandler.processMove(index);
        return disapleAllButtonsResponse();
    }
    /*_____ * _____ end of  Multi Mode Game Requests _____ * _____ */
    
    /*_____ * _____  Single Mode Game Requests _____ * _____ */
    
    private static String handlePlaySingleModeGame(JSONObject data, PlayerHandler playerHandler) {
        String difficulty = (String) data.get("difficulty");
        String choice = (String) data.get("choice");
        String gameID = generateUniqueId();
        new SingleModeGameHandler(gameID, playerHandler, choice, difficulty);
        if (choice.equals("o")) {
            playerHandler.sendResponse(disapleAllButtonsSingleResponse());
        }
        return startSingleModeGameResponse(gameID, choice);
    }
    
    private static String handleSingleModeGameMove(JSONObject data, PlayerHandler playerHandler) {
        String gameID = (String) data.get("gameId");
        int index = ((Long) data.get("index")).intValue();
        SingleModeGameHandler gameHandler = Utility.getSingleModeGameHandler(gameID);
        gameHandler.processMove(index);
        return disapleAllButtonsResponse();
    }
    
    /*_____ * _____  end of Single Mode Game Requests _____ * _____ */
}
