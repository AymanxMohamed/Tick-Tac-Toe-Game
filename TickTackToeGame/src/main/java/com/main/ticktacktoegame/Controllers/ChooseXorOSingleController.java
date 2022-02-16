/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Enums.MappingFunctions;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.playSingleModeGame;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class ChooseXorOSingleController {

    @FXML
    public void sendSingleModeRequestWithX(){
        Client.sendRequest(playSingleModeGame(MappingFunctions.mapDifficulty(Client.difficulty),"X"));
    }
    
    @FXML
    public void sendSingleModeRequestWithO() {
        Client.sendRequest(playSingleModeGame(MappingFunctions.mapDifficulty(Client.difficulty),"O"));
    }
    
}
