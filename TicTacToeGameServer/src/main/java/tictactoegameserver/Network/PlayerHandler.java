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

/**
 *
 * @author ayman
 */
public class PlayerHandler {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public Player player;
    private boolean inGame;
    public static ArrayList<PlayerHandler> playerHandlers = new ArrayList<>();
    
    public PlayerHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            playerHandlers.add(this);
           AcceptRequests();
        } catch (IOException e) {
            closeEveryThing(socket, bufferedReader, bufferedWriter);
        }
    }
    private void AcceptRequests() {
        new Thread(() -> {
            String playerRequest;
            while (socket.isConnected()) {
                try {
                    playerRequest = bufferedReader.readLine();
                    sendResponse(RequestHandler.handleRequest(playerRequest, this));
                } catch (IOException e) {
                    closeEveryThing(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }
    
    public void sendResponse(String response)  {
        try {
            bufferedWriter.write(response);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void broadcastResponse(String response) {
        for (var playerHandler : playerHandlers) {
            playerHandler.sendResponse(response);
        }
    }
    private void closeEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // System.out.println("why u closed me :(((");
        playerHandlers.remove(this);
        RequestHandler.sendUpdateOnlinePlayersResponse();
        DatabaseManager.openDataBaseConnection();
        DatabaseManager.togglePlayerStatus(player);
        DatabaseManager.closeDataBaseConnection();
        try {
          if (bufferedReader != null) {
              bufferedReader.close();
          }
          if (bufferedWriter != null) {
              bufferedWriter.close();
          }
          if (socket != null) {
              socket.close();
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

