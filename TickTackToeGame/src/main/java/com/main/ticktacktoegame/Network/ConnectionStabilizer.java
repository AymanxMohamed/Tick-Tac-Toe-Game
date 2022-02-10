/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author elsho
 */
public class ConnectionStabilizer {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;
    private Socket socket = null;
    
    // private BufferedReader in;
    private PrintWriter out;
    
    public ConnectionStabilizer() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        // ServerConnectionHandler serverConnection = new ServerConnectionHandler(socket);
        // new Thread(ServerConnectionHandler).start();
        System.out.println("Hello from the client side.");
        
        System.out.close();
    }
    
    public static void main(String[] args) throws IOException {
        new ConnectionStabilizer();
    }
}