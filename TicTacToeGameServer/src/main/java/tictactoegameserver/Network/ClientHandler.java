/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author elsho
 */
class ClientHandler implements Runnable {
    private Socket clientSocket = null;
    private BufferedReader in;
    private PrintWriter out;
    private String request;
    
    ArrayList<ClientHandler> clients;
    ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.clientSocket = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                request = in.readLine();
                System.out.println("Client says: "+request);
                // TODO : work on "request" to run the game
                out.println("[Server] Acknowledgement.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
