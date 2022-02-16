/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class ChooseXorOMultiController {

    public void startTheGame() {
        try {
            App.setRoot("TicTackToe");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void playerXHandler(){
        startTheGame();
    }
    public void playerOHandler() {
        startTheGame();
    }
    
}
