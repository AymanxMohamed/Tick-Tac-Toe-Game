package com.main.ticktacktoegame;

import com.main.ticktacktoegame.Network.Client;
import com.main.ticktacktoegame.Network.RequestCreator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void sendLoginRequest() {
        try {
            //Client.sendRequest(RequestCreator.createRegisterJsonString("Shopaky", "123456"));
            Client.sendRequest(RequestCreator.createLoginJsonString("shopaky", "123456"));
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("TicTackToe");
    }
}
