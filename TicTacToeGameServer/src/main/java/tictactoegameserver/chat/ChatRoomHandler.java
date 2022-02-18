/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.chat;

import java.util.ArrayList;
import tictactoegameserver.Network.PlayerHandler;
import static tictactoegameserver.Network.ResponseCreator.*;
import tictactoegameserver.models.PlayerModel;

/**
 *
 * @author ayman
 */
public class ChatRoomHandler {
    private final String chatID;
    private final PlayerHandler senderHandler;
    private final PlayerHandler receiverHandler;
    public static ArrayList<ChatRoomHandler> currentRooms = new ArrayList<>();
    
    public static void addChatRoomHandler(String chatID, PlayerHandler senderHandler, PlayerHandler receiverHandler){
        currentRooms.add(new ChatRoomHandler(chatID, senderHandler, receiverHandler));
    }
    
    public ChatRoomHandler(String chatID, PlayerHandler senderHandler, PlayerHandler receiverHandler) {
        this.chatID = chatID;
        this.senderHandler = senderHandler;
        this.receiverHandler = receiverHandler;
    }

    public void processMessage(String message, String sender) {
        receiverHandler.sendResponse(addNewMessage(message, sender));
        senderHandler.sendResponse(addNewMessage(message, sender));	
    }
    public void forceEndChat(String playerName) {
        PlayerHandler stayerHandler;
        if (!senderHandler.player.getUserName().equals(playerName)) {
            stayerHandler = senderHandler;
        } else {
            stayerHandler = receiverHandler;
        }
        stayerHandler.sendResponse(playerLeftChatResponse(playerName));
        stayerHandler.inChat = false;
        ArrayList<String> chatPlayers = new ArrayList<>();
        chatPlayers.add(stayerHandler.player.getUserName());
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(chatPlayers, "inChat"));
        PlayerModel.getPlayerModel(stayerHandler.player.getUserName()).toogleInChatStatus();
        currentRooms.remove(this);
    }
    public void endChat(String playerName) {
        PlayerHandler stayerHandler;
        PlayerHandler leaverHandler;
        if (!senderHandler.player.getUserName().equals(playerName)) {
            stayerHandler = senderHandler;
            leaverHandler = receiverHandler;
        } else {
            leaverHandler = senderHandler;
            stayerHandler = receiverHandler;
        }
        
        
        leaverHandler.inChat = false;
        stayerHandler.inChat = false;
        ArrayList<String> chatPlayers = new ArrayList<>();
        chatPlayers.add(leaverHandler.player.getUserName());
        chatPlayers.add(stayerHandler.player.getUserName());
        
        PlayerHandler.broadcastResponse(updateAvilablePlayersList(chatPlayers, "inChat"));
        
        PlayerModel.getPlayerModel(stayerHandler.player.getUserName()).toogleInChatStatus();
        PlayerModel.getPlayerModel(leaverHandler.player.getUserName()).toogleInChatStatus();
        
        stayerHandler.sendResponse(updatePlayerDataResponse(stayerHandler.player));
        leaverHandler.sendResponse(updatePlayerDataResponse(leaverHandler.player));
        leaverHandler.sendResponse(goToWelcomeViewResponse());
        stayerHandler.sendResponse(playerLeftChatResponse(playerName));
        currentRooms.remove(this);
    }
    public String getChatID() { return chatID; }
    public PlayerHandler getSenderHandler() { return senderHandler; }
    public PlayerHandler getReceiverHandler() { return receiverHandler; }

}
