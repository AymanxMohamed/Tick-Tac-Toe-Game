/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.Models.Player;
import java.net.Socket;
import java.io.*;

/**
 *
 * @author ayman
 */
public class Client {
    
    private static  Socket socket;
    private static final String serverIP = "localhost";
    private static final int ServerPort = 9000;
    private static BufferedReader bufferReader;
    private static BufferedWriter bufferedWriter;
    public static Player player;
    
        
    public static void openConnection() throws IOException {
        socket = new Socket(serverIP, ServerPort);
        try {
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("connection opend");
            AcceptResponses();
        } catch (IOException e) {
            closeEveryThing();
        }
    }
    
    private static void AcceptResponses() {
        new Thread(() -> {
            String response;
            while (socket.isConnected()) {
                try {
                    response = bufferReader.readLine();
                    ResponseHandler.handleResponse(response);
                } catch (IOException e) {
                    closeEveryThing();
                }
            }
        }).start();
    }
    public static void sendRequest(String request) throws IOException {
        bufferedWriter.write(request);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
    public static void closeEveryThing() {
        try {
            if (bufferReader != null) {
                bufferReader.close();
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