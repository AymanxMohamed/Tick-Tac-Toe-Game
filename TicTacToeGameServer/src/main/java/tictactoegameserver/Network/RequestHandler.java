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
import static tictactoegameserver.chat.ChatRoomHandler.addChatRoomHandler;
import  tictactoegameserver.gamelogic.MultiModeGameHandler;
import static tictactoegameserver.gamelogic.MultiModeGameHandler.addMultiModeGameHandler;
import tictactoegameserver.gamelogic.SingleModeGameHandler;
import static tictactoegameserver.gamelogic.SingleModeGameHandler.addSingleModeGameHandler;
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
            case "chat invitation":
                return handleChatInvitation(data);
            case "acceptInvitation":
                return handleAcceptInvitation(data);
            case "rejectInvitation":
                return handleRejectInvitation(data);
            case "acceptChatInvitation":
                return handleAcceptChatInvitation(data);
            case "rejectChatInvitation":
                return handleRejectChatInvitation(data);
            case "XorOChoise":
                return handleXOrOChoise(data);
            case "multiMove":
                return handleMultiModeGameMove(data);
            case "play single mode game":
                return handlePlaySingleModeGame(data, playerHandler);
            case "singleMove":
                return handleSingleModeGameMove(data);
            case "logout":
                handleLogout(data, playerHandler);
                break;
            case "force end multi mode game":
                handleForceEndMultiModeGame(data, playerHandler);
                break;
            case "end single mode game":
                handleEndSingleModeGame(data);
                break;
            case "cancelEndSingleGame":
                handelCancelEndSingleGame(data, playerHandler);
                break;
            case "leave chat":
                handleEndChatRoom(data, playerHandler);
                break;
        }
        return response;
    }
    
    /*_____ * _____ Login & Register Requests _____ * _____ */
    
    private static String handleLogin(JSONObject data, PlayerHandler playerHandler) {
         System.out.println("in handel login");
        String userName = (String) data.get("username");
        String password = (String) data.get("password");
        if (getPlayerHandler(userName) != null) {
            return playerAlreadyOnlineResponse(userName);
        }
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
         System.out.println("in handel register");

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
         System.out.println("in handel game Invitation");
        String invitationReciever = (String) data.get("invitationReciever");
        PlayerHandler receiverHandler = getPlayerHandler(invitationReciever);
        
        if (receiverHandler.inGame) {
            return playerInGameResponse(invitationReciever);
        }
        if (receiverHandler.inChat) {
            return playerInChatResponse(invitationReciever);
        }
        receiverHandler.sendResponse(invitationFromPlayerRequest(data));
        return invitationSendedResponse(data);
    }
             /*_____ * _____ receiver _____ * _____ */
    
    private static String handleAcceptInvitation(JSONObject data) {
         System.out.println("in handel accept invitation");
         
        String invitationSender = (String) data.get("invitationSender");
        PlayerHandler senderHandler = getPlayerHandler(invitationSender);
        senderHandler.sendResponse(chooseXOrOResponse(data));
        return doNothingResponse();
    }
    
    private static String handleRejectInvitation(JSONObject data) {
        
         System.out.println("in handel reject invitation");
        String invitationSender = (String) data.get("invitationSender");        
        PlayerHandler senderHandler = getPlayerHandler(invitationSender);
        senderHandler.sendResponse(invitationRejectedResponse(data));
        return doNothingResponse();
    }
                /*_____ * _____ sender _____ * _____ */
    
    private static String handleXOrOChoise(JSONObject data) {
        
         System.out.println("in handel XorO Choise");
         
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
        addMultiModeGameHandler(gameID, playerXHandler, playerOHandler);
        playerXHandler.inGame = true;
        playerOHandler.inGame = true;
        
        // send update avilable players request
        ArrayList<String> XOPlayers = new ArrayList<>();
        XOPlayers.add(playerXHandler.player.getUserName());
        XOPlayers.add(playerOHandler.player.getUserName());
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(XOPlayers, "inGame"));

        playerXHandler.sendResponse(startMultiModeGameResponse(gameID, playerXHandler.player.getUserName(), playerOHandler.player.getUserName()));
        playerOHandler.sendResponse(startMultiModeGameResponse(gameID, playerXHandler.player.getUserName(), playerOHandler.player.getUserName()));
        playerOHandler.sendResponse(disapleAllButtonsResponse());
        return doNothingResponse();
    }
                    /*_____ * _____ game process _____ * _____ */
    
    private static String handleMultiModeGameMove(JSONObject data) {
        System.out.println("in handel multiModeGameMove");
        String gameID = (String) data.get("gameId");
        int index = ((Long) data.get("index")).intValue();
        MultiModeGameHandler gameHandler = Utility.getMultiModeGameHandler(gameID);
        gameHandler.processMove(index);
        return disapleAllButtonsResponse();
    }

    private static void handleForceEndMultiModeGameOnLogout(String multiGameId, String playerName) {
        
         System.out.println("in handel EndMultiiModeGameOnLogout");
        getMultiModeGameHandler(multiGameId).forceEndGameOnlogout(playerName);
    }

    private static void handleForceEndMultiModeGame(JSONObject data, PlayerHandler playerHandler) {
        
         System.out.println("in handel endMultiModeGame");
        String gameId = (String) data.get("gameId");
        getMultiModeGameHandler(gameId).forceEndGame(playerHandler.player.getUserName());
    }
    /*_____ * _____ end of  Multi Mode Game Requests _____ * _____ */
    
    /*_____ * _____  Single Mode Game Requests _____ * _____ */
    
    private static String handlePlaySingleModeGame(JSONObject data, PlayerHandler playerHandler) {
        
         System.out.println("in handel play single mode game");
        System.out.println("Request accepted: ");
        String difficulty = (String) data.get("difficulty");
        String choice = (String) data.get("choice");
        String gameID = generateUniqueId();
        playerHandler.sendResponse(startSingleModeGameResponse(gameID, choice));
        addSingleModeGameHandler(gameID, playerHandler, choice, difficulty);
        if (choice.equals("o")) {
            playerHandler.sendResponse(disapleAllButtonsSingleResponse());
        }
        return doNothingResponse();
    }
    
    private static String handleSingleModeGameMove(JSONObject data) {
        
         System.out.println("in handel single mode game move");
        String gameID = (String) data.get("gameId");
        int index = ((Long) data.get("index")).intValue();
        getSingleModeGameHandler(gameID).processMove(index);
        return doNothingResponse();
    }
    private static String handelCancelEndSingleGame(JSONObject data, PlayerHandler playerHandler) {
        System.out.println("in handel cancel end singlemode game");
        String gameID = (String) data.get("gameId");
        SingleModeGameHandler gameHandler = getSingleModeGameHandler(gameID);
        playerHandler.sendResponse(drawSingleMovesResponse(gameHandler.getGameMoves()));
        playerHandler.sendResponse(continueGameResponse(gameHandler.getGameMoves()));
        return enableSingleButtonsResponse();
    }
    private static void handleEndSingleModeGame(JSONObject data) {
        System.out.println("in handel end single game");
        String gameId = (String) data.get("gameId");
        getSingleModeGameHandler(gameId).endGame();
    }
    
    /*_____ * _____  end of Single Mode Game Requests _____ * _____ */
    
    /*_____ * _____  Chat Rooms Requests _____ * _____ */
    
                /*_____ * _____ sender _____ * _____ */
    
    private static String handleChatInvitation(JSONObject data) {
        String invitationReciever = (String) data.get("invitationReciever");
        PlayerHandler receiverHandler = getPlayerHandler(invitationReciever);

        if (receiverHandler.inGame) {
            return playerInGameResponse(invitationReciever);
        }
        if (receiverHandler.inChat) {
            return playerInChatResponse(invitationReciever);
        }
        receiverHandler.sendResponse(chatInvitationFromPlayerRequest(data));
        return invitationSendedResponse(data);
    }
    
               /*_____ * _____ receiver _____ * _____ */   
    
    private static String handleAcceptChatInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        String invitationReciever = (String) data.get("invitationReciever");
        PlayerHandler senderHandler = getPlayerHandler(invitationSender);
        PlayerHandler recieverHandler = getPlayerHandler(invitationReciever);
        
        String chatID = generateUniqueId();
        addChatRoomHandler(chatID, senderHandler, recieverHandler);
        senderHandler.inChat = true;
        recieverHandler.inChat = true;
        
        // send update avilable players request
        ArrayList<String> chatPlayers = new ArrayList<>();
        chatPlayers.add(senderHandler.player.getUserName());
        chatPlayers.add(recieverHandler.player.getUserName());
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(chatPlayers, "inChat"));
        
        senderHandler.sendResponse(openChatRoomResponse(chatID, senderHandler.player.getUserName(), recieverHandler.player.getUserName()));
        return openChatRoomResponse(chatID, senderHandler.player.getUserName(), recieverHandler.player.getUserName());
    }
    
    private static String handleRejectChatInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");        
        PlayerHandler senderHandler = getPlayerHandler(invitationSender);
        senderHandler.sendResponse(invitationRejectedResponse(data));
        return doNothingResponse();
    }

    private static void handleEndChatRoom(JSONObject data, PlayerHandler playerHandler) {
        String chatId = (String) data.get("chatId");
        getChatRoomHandler(chatId).endChat(playerHandler.player.getUserName());
    }
    
    private static void handleForceEndChatOnLogout(String chatId, String playerName) {
        getChatRoomHandler(chatId).forceEndChat(playerName);
    }

    /*_____ * _____  end of Chat Rooms Requests _____ * _____ */
    

    
    
    
    
    /*_____ * _____  Logout Requests _____ * _____ */
    private static void handleLogout(JSONObject data, PlayerHandler playerHandler) {
        String playerName = (String) data.get("playerName");
        String singleGameId = (String) data.get("singleGameId");
        String multiGameId = (String) data.get("multiGameId");
        String chatRoomId = (String) data.get("chatRoomId");
        if (playerHandler.inGame) {
            // 1- check if it's single mode game
            if (getSingleModeGameHandler(singleGameId) != null) {
                SingleModeGameHandler.currentGames.remove(getSingleModeGameHandler(singleGameId));
            } else if (getMultiModeGameHandler(multiGameId) != null) {
                handleForceEndMultiModeGameOnLogout(multiGameId, playerName);
            }
        }
        if (playerHandler.inChat) {
            handleForceEndChatOnLogout(chatRoomId, playerName);
        }
        playerHandler.closeEveryThing();
    }
    /*_*____ * _____  end of Logout Requests _____ * _____ */


}
