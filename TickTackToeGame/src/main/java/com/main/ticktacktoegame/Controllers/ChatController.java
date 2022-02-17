/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.leaveChat;
import static com.main.ticktacktoegame.Network.RequestCreator.sendNewMessage;
import static com.main.ticktacktoegame.Network.RequestCreator.singleMove;
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
    
    private StringBuilder fieldContent = new StringBuilder("");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Client.chatRoom = this;
        textArea.setDisable(true);
    }
    
    @FXML
    public void sendMessage() {
        Client.sendRequest(sendNewMessage(messageToSendField.getText()));
        messageToSendField.setText("");
    }
    public void addMessageToTextArea(String message) {
        fieldContent.append(message).append("\n");
        textArea.setText(fieldContent.toString());
    }
    
    @FXML
    public void  leave() {
        Client.sendRequest(leaveChat());
    }
    
}
