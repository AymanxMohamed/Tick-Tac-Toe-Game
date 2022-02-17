/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.oChoosen;
import static com.main.ticktacktoegame.Network.RequestCreator.xChoosen;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class ChooseXorOMultiController {

   @FXML
    public void sendxChoosen(){ 
        Client.sendRequest(xChoosen());
        
    }
   @FXML
    public void sendOChoosen(){
        Client.sendRequest(oChoosen());
    }
    
}
