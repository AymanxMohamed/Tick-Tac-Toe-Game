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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class AvailableUsersViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView onlinePlayersTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
    }

}
