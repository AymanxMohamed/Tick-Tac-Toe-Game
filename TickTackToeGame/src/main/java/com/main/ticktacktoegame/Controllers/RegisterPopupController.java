/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author elsho
 */
public class RegisterPopupController {

    public void closePopup() {
        try {
            App.setRoot("RegistrationView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
