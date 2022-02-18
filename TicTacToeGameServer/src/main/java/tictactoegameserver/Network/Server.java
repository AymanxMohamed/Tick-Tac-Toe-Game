/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tictactoegameserver.Network.PlayerHandler.addPlayerHandler;

/**
 *
 * @author ayman
 */
public class Server {
    private static ServerSocket serverSocket;
    private static final int SERVER_PORT = 9000;

    public static void startServer() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            new Thread(() -> {
                try {
                    while (!serverSocket.isClosed()) {
                        Socket socket = serverSocket.accept();
                        addPlayerHandler(socket);
                        System.out.println("Cann't launch the server on This port");
                        System.out.println("May be this port is used by another program");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeServer() {
        System.out.println("server is closed");
        try {
            if (!serverSocket.isClosed())
                PlayerHandler.playerHandlers.forEach(handler -> handler.closeEveryThing());
                serverSocket.close();
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
