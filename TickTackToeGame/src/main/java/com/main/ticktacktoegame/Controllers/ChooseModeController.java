package com.main.ticktacktoegame.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.main.ticktacktoegame.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author elsho
 */
public class ChooseModeController {

    @FXML
    public void switchToSingleMode() {
        switchToChooseDifficultyView();
    }
    
    @FXML
    public void switchToMultiMode() {
        switchToAvilablePlayersView();
    }
    
    public void switchToChooseDifficultyView() {
        try {
            App.setRoot("chooseLevelView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void switchToAvilablePlayersView() {
        try {
            App.setRoot("AvilablePlayersView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void sendMultiModeGameInvitation() {

    }
    
    
}
