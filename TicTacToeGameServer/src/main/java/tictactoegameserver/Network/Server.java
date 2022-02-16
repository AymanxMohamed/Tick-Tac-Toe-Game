/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import static tictactoegameserver.Network.PlayerHandler.addPlayerHandler;

/**
 *
 * @author ayman
 */
public class Server {
    private static ServerSocket serverSocket;
    private static final int SERVER_PORT = 9000;

    public static void startServer() throws IOException {
        serverSocket = new ServerSocket(SERVER_PORT);
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                addPlayerHandler(socket);
            } catch (IOException e) {
                System.out.println("Cann't launch the server on This port");
                System.out.println("May be this port is used by another program");
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
