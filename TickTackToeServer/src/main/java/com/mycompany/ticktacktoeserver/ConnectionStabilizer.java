/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticktacktoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author elsho
 */
public class ConnectionStabilizer {
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    
    private static final int SERVER_PORT = 9090;
    
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    
    public ConnectionStabilizer () throws IOException {
        serverSocket = new ServerSocket(SERVER_PORT);
        while (true) {
            System.out.println("[SERVER] Waiting for the client connection.");
            clientSocket = serverSocket.accept();
            System.out.println("[SERVER] Connected to the client successfully.");
            
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        new ConnectionStabilizer();
    }
}