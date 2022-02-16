/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.util.UUID;
import static tictactoegameserver.Network.PlayerHandler.playerHandlers;
import tictactoegameserver.chat.ChatRoomHandler;
import tictactoegameserver.gamelogic.MultiModeGameHandler;
import tictactoegameserver.gamelogic.SingleModeGameHandler;

/**
 *
 * @author ayman
 */
public class Utility {
    
    public static PlayerHandler getPlayerHandler(String playerName) {
        
        for (var playerHandler : playerHandlers) {
            if (playerHandler.player != null && playerHandler.player.getUserName().equals(playerName))
                return playerHandler;
        }
        return null;
    }
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
    public static MultiModeGameHandler getMultiModeGameHandler(String gameId) {
        for (var multiModeGameHandler : MultiModeGameHandler.currentGames) {
            if (multiModeGameHandler.getGameID().equals(gameId))
                return multiModeGameHandler;
        }
        return null;
    }
    public static SingleModeGameHandler getSingleModeGameHandler(String gameId) {
        for (var singleModeGameHandler : SingleModeGameHandler.currentGames) {
            if (singleModeGameHandler.getGameID().equals(gameId))
                return singleModeGameHandler;
        }
        return null;
    }
    public static ChatRoomHandler getChatRoomHandler(String chatId) {
        for (var roomHandler : ChatRoomHandler.currentRooms) {
            if (roomHandler.getChatID().equals(chatId))
                return roomHandler;
        }
        return null;
    }
}
