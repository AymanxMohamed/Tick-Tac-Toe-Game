/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.*;
import java.net.Socket;
import tictactoegameserver.Database.Entities.Player;

/**
 *
 * @author ayman
 */
public class PlayerHandler {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Player player;
    private boolean inGame;
    
    public PlayerHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // authonticate user
           
        } catch (IOException e) {
            closeEveryThing(socket, bufferedReader, bufferedWriter);
        }
    }
    public void AcceptRequests() {
        new Thread(() -> {
            String playerRequest;
            while (socket.isConnected()) {
                try {
                    playerRequest = bufferedReader.readLine();
                    sendResponse(RequestHandler.handleRequest(playerRequest));
                } catch (IOException e) {
                    closeEveryThing(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }
    public void sendResponse(String response) throws IOException {
        bufferedWriter.write(response);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
    private void closeEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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

/*
        Request --> login , reqister
        RequestHandler(JsonString){
            switch (
        }
        


*/
