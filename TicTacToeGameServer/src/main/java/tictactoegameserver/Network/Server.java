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
import static tictactoegameserver.Network.ResponseCreator.serverIsClosed;

/**
 *
 * @author ayman
 */
public class Server {
    public static ServerSocket serverSocket = null;
    public static final int SERVER_PORT = 9000;
    public static boolean isClosed = true;
    
    public static void startServer() {
        isClosed = false;
        try {
            Server.serverSocket = new ServerSocket(Server.SERVER_PORT);
            while (serverSocket != null && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                if (isClosed) {
                    socket.close();
                    break;
                }
                addPlayerHandler(socket);
            }
        } catch (IOException ex) {
            System.out.println("Cann't launch the server on This port");
            System.out.println("May be this port is used by another program");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized public static void closeServer() {
        isClosed = true;
        PlayerHandler.broadcastResponse(serverIsClosed());
        try {
            if (!serverSocket.isClosed())
            {
                new Socket(serverSocket.getInetAddress(), serverSocket.getLocalPort()).close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
