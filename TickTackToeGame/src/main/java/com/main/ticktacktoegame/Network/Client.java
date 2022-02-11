/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

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
    
        
    public static void openConnection(String userName,String password) throws IOException {
        
        socket = new Socket(serverIP, ServerPort);
        try {
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // Authontication stage
            // allowed requests login
            
            
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEveryThing(socket, bufferReader, bufferedWriter);
        }
    }
    
    public static void AcceptResponses() {
        new Thread(() -> {
            String response;
            while (socket.isConnected()) {
                try {
                    response = bufferReader.readLine();
                } catch (IOException e) {
                    closeEveryThing(socket, bufferReader, bufferedWriter);
                }
            }
        }).start();
    }
    public void sendRequest(String requestString) throws IOException {
        bufferedWriter.write(requestString);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
    private static void closeEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
