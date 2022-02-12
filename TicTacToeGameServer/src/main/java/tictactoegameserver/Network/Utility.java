/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import static tictactoegameserver.Network.PlayerHandler.playerHandlers;

/**
 *
 * @author ayman
 */
public class Utility {
    
    public static PlayerHandler getPlayerHandler(String playerName) {
        
        for (var playerHandler : playerHandlers) {
            if (playerHandler.player.getUserName().equals(playerName))
                return playerHandler;
        }
        return null;
    }
}
