/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.leaveChat;
import static com.main.ticktacktoegame.Network.RequestCreator.sendNewMessage;
import static com.main.ticktacktoegame.Network.RequestCreator.singleMove;
import com.main.ticktacktoegame.Utilites.AudioPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    Button soundBtn;

    private StringBuilder fieldContent = new StringBuilder("");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (quitChatController.fieldContent != null) {
            this.fieldContent = quitChatController.fieldContent;
            textArea.setText(this.fieldContent.toString());
        }
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
    public void leave() {
        quitChatController.fieldContent = this.fieldContent;
        try {
            App.setRoot("LeaveChatView");
        } catch (IOException ex) {
            System.out.println("problem in history mode controller ");
        }
    }

    @FXML
    public void toogleAudio() {
        if (soundBtn.getStyleClass().contains("muteSound")) {
            soundBtn.getStyleClass().remove("muteSound");
            soundBtn.getStyleClass().add("unmuteSound");
        } else {
            soundBtn.getStyleClass().remove("unmuteSound");
            soundBtn.getStyleClass().add("muteSound");
        }
        AudioPlayer.toogleAudio();
    }

}
