/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class IndexController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public void switchToLoginView() {
        try {
            // If the server is online -> open connection and switch to login view
            Client.openConnection();
            App.setRoot("LoginView");
        } catch (IOException ex) {
            // If the server is down -> close everything and pop-up a failure message
            Client.closeEveryThing();
            try {
                App.setRoot("ServerIsDownPopUp");
            } catch (IOException ex1) {
            System.out.println("problem in index controller");
            }
        }
    }
    
}
