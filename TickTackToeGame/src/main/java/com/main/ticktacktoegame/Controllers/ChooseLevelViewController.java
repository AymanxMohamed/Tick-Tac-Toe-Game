/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Enums.DIFFICULTY;
import com.main.ticktacktoegame.Network.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class ChooseLevelViewController implements Initializable {


    @FXML
    public void easyChoosen() {
        Client.difficulty = DIFFICULTY.EASY;
        switchToXorOViewSingle();
    }
    @FXML
    public void mediumChoosen() {
        Client.difficulty = DIFFICULTY.MEDIUM;
        switchToXorOViewSingle();
    }
    @FXML
    public void hardChoosen() {
        Client.difficulty = DIFFICULTY.HARD;
        switchToXorOViewSingle();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
   @FXML
    public void switchToXorOViewSingle() {
        try {
            App.setRoot("chooseXorOSingle");
        } catch (IOException ex) {
            System.out.println("problem in choose level controller");
        }
    }    
    
        @FXML
    public void SwitchToPreviousView() {
        try {
            App.setRoot("chooseHistoryMode");
        } catch (IOException ex) {
            System.out.println("problem in choose level controller");
        }
    }
}
