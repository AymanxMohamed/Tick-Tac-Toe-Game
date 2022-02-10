package com.main.ticktacktoegame.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.main.ticktacktoegame.App;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author elsho
 */
public class ChooseModeController {

    public void switchTicTacToe() throws IOException {
        App.setRoot("TicTackToe");
    }
    
}
