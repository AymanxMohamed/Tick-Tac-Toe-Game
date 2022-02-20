/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoegameserver.Database.DatabaseManager;
import tictactoegameserver.Database.Entities.Player;
import static tictactoegameserver.Network.ResponseCreator.playerLeftTheGameResponse;

/**
 *
 * @author ayman
 */
public class PlayerHandler {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public Player player;
    public boolean inGame;
    public boolean inChat;
    volatile public static ArrayList<PlayerHandler> playerHandlers;
    
    public static void addPlayerHandler(Socket socket) {
        playerHandlers.add(new PlayerHandler(socket));
    }
    
    public PlayerHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inGame = false;
            this.inChat = false;
           AcceptRequests();
        } catch (IOException e) {
            closeEveryThing();
        }
    }
    private void AcceptRequests() {
        new Thread(() -> {
            try {
                String playerRequest;
                    while (!socket.isClosed() &&(playerRequest = bufferedReader.readLine()) != null) {
                        sendResponse(RequestHandler.handleRequest(playerRequest, PlayerHandler.this));
                    }
            } catch (Exception e) {
                System.out.println("exception in accept response");
                closeEveryThing();
            }
        }).start();
    }
    

    public void sendResponse(String response)  {
        if (response == null) {
            return;
        }
        try {
            if (!socket.isClosed()) {
                bufferedWriter.write(response);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void broadcastResponse(String response) {
        for (PlayerHandler playerHandler : playerHandlers) {
            playerHandler.sendResponse(response);
        }
    }
    private void removePlayer() {
        if (player != null) {
            System.out.println(this.player.getUserName() + " left the server");
            DatabaseManager.openDataBaseConnection();
            DatabaseManager.togglePlayerStatus(this.player);
            DatabaseManager.closeDataBaseConnection();
            broadcastResponse(playerLeftTheGameResponse(this.player.getUserName()));
        }
        playerHandlers.remove(this);
    } 
    public void closeEveryThing() {
        removePlayer();
        try {
          if (this.bufferedReader != null) {
              this.bufferedReader.close();
          }
          if (this.bufferedWriter != null) {
              this.bufferedWriter.close();
          }
          if (this.socket != null) {
              this.socket.close();
          }
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}

