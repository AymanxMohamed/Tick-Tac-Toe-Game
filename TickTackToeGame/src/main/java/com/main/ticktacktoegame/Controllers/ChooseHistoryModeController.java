/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
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
public class ChooseHistoryModeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void switchToSingleModeHistoryView() {
        try {
            App.setRoot("singleModeHistoryView");
        } catch (IOException ex) {
            System.out.println("problem in history mode controller ");
        }
    }

    @FXML
    public void switchToMultiModeHistoryView() {
        try {
            App.setRoot("multiModeHistoryView");
        } catch (IOException ex) {
            System.out.println("problem in history mode controller ");
        }
    }

    @FXML
    private void SwitchToPreviousView() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            System.out.println("problem in history mode controller ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
