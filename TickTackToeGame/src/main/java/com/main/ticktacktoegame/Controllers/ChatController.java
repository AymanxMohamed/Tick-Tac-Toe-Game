/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class ChatController implements Initializable {

    @FXML
    private Label playerOneLabel;
    
    @FXML
    private Label PlayerTwoLabel;
    
    @FXML
    private TextField messageToSendField;
    
    @FXML
    private TextArea textArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void sendMessage() {
        
    }
    
    @FXML
    public void  leaveChat() {
        
    }
    
}
