/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

/**
 *
 * @author elsho
 */
public class ConnectionStabilizer {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;
    private Socket socket = null;
    
    private BufferedReader in;
    private BufferedReader keyboard;
    private PrintWriter out;
    String string;
    public ConnectionStabilizer() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream(), true);
        ServerConnectionHandler serverConnection = new ServerConnectionHandler(socket);
        new Thread(serverConnection).start();
        while (true) {
            string = keyboard.readLine();
            if (string == "exit") break;
            out.println(string);

            string = in.readLine();
            System.out.println(string);
        }

        out.close();
    }
    /*
    public static void main(String[] args) throws IOException {
        new ConnectionStabilizer();
    }
    */
}