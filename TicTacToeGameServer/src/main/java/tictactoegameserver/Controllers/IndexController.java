/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tictactoegameserver.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import tictactoegameserver.App;
import tictactoegameserver.Network.Server;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class IndexController {

    @FXML
    public void openServer() {
        new Thread(() -> {
            Server.startServer();
        }).start();

        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
