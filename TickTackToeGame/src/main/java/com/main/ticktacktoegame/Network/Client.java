/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Controllers.ChatController;
import com.main.ticktacktoegame.Models.*;
import com.main.ticktacktoegame.Models.Enums.DIFFICULTY;
import java.net.Socket;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ayman
 */
public class Client {
    
    private static  Socket socket;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9000;
    private static BufferedReader bufferReader;
    private static BufferedWriter bufferedWriter;
    public static Player player;
    public static String multiModeGameId = "";
    public static String opponnentName;
    public static String singleModeGameID = "";
    public static String chatRoomId = "";
    public static DIFFICULTY difficulty;
    public static String cuurentCase = "";
    public static ChatController chatRoom;

        
    public static void openConnection() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        try {
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("connection opened");
            AcceptResponses();
        } catch (IOException e) {
            closeEveryThing();
        }
    }
    
    private static void AcceptResponses() {
        new Thread(() -> {
            try {
                String response;
                while (socket.isConnected() && (response = bufferReader.readLine()) != null) {
                    ResponseHandler.handleResponse(response);
                }
            } catch (IOException ex) {
                System.out.println("connection lost");
                closeEveryThing();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("connection lost problem in accept response");
                closeEveryThing();
            }
        }).start();
    }
    public static void sendRequest(String request) {
        if (request == null)
            return;
        try {
            bufferedWriter.write(request);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException ex) {
            closeEveryThing();
            System.out.println("connection lost because of send request");
        }
    }
    public static void closeEveryThing() {
        try {
            App.setRoot("ServerIsDownPopUp");
            if (bufferReader != null) {
                bufferReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("problem in closing socket");

        } 
    }
}
