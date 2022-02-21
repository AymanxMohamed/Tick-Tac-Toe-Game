/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import java.io.IOException;

/**
 *
 * @author elsho
 */
public class ServerIsDownPopUpController {
    public void closePopup(){
        try {
            App.setRoot("index");
        } catch (IOException ex1) {
            System.out.println("problem in serverPopUpController");
        }
    }
}
