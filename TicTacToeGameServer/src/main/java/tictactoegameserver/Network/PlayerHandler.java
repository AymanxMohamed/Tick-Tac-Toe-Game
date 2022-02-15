/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
            try {
                String playerRequest;
                    while (!socket.isClosed() &&(playerRequest = bufferedReader.readLine()) != null) {
                        sendResponse(RequestHandler.handleRequest(playerRequest, PlayerHandler.this));
                    }
            } catch (Exception e) {
                System.out.println("exception in accept response");
                //e.printStackTrace();
                closeEveryThing(socket, bufferedReader, bufferedWriter);
            }
        }).start();
    }
    
    public void sendResponse(String response)  {
        try {
            if (!socket.isClosed()) {
                bufferedWriter.write(response);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException ex) {
            //Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void broadcastResponse(String response) {
        for (PlayerHandler playerHandler : playerHandlers) {
            playerHandler.sendResponse(response);
        }
    }
    private void removePlayer() {
        if (player != null) {
            System.out.println(this.player.getUserName() + "left the server");
            DatabaseManager.openDataBaseConnection();
            DatabaseManager.togglePlayerStatus(this.player);
            DatabaseManager.closeDataBaseConnection();
            broadcastResponse(playerLeftTheGameResponse(this.player.getUserName()));
        }
        playerHandlers.remove(this);
    } 
    private void closeEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removePlayer();
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
        } catch (Exception e) {
            System.out.println("exception");
            //e.printStackTrace();
        }
    }
}

