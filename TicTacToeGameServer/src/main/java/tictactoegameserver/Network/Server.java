/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ayman
 */
public class Server {
    private static ServerSocket serverSocket;
    private static int serverPort = 9000;
    
    public static void startServer() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        while (!serverSocket.isClosed()) {
            try {
                    Socket socket = serverSocket.accept();
                    PlayerHandler playerHandlder = new PlayerHandler(socket);
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
    }
    public static void closeServer() {
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
