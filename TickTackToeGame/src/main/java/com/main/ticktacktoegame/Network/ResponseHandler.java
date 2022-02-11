/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Controllers.WelcomeController;
import com.main.ticktacktoegame.Models.Player;
import java.io.IOException;
import org.json.simple.*;

/**
 *
 * @author ayman
 */
public class ResponseHandler {
    public static void handleResponse(String responseString) {
        
        JSONObject requestObject = (JSONObject) JSONValue.parse(responseString);
        String response = (String) requestObject.get("response");
        JSONObject data;

        switch (response) {
            case "player not exists":
                handlePlayerNotExist();
                break;
            case "wrong password":
                handleWrongPassword();
                break;
            case "login success":
                data = (JSONObject) requestObject.get("data");
                handleLoginSuccess(data);
                break;
            case "player exists":
                handlePlayerExist();
                // this means that the player tried to reqister but the user name exists
                break;
            case "reqister sucsess":
                handleRegisterSuccess();
                break;
            case "update online players":
                data = (JSONObject) requestObject.get("data");
                handleUpdateOnlinePlayers(data);
                break;
        }
    }

    private static void handlePlayerNotExist() {
        try {
            App.setRoot("PlayerNotExistView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleWrongPassword() {
        try {
            App.setRoot("WrongPasswordView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleLoginSuccess(JSONObject playerData) {
        try {
            Client.player = new Player(
                    (String) playerData.get("userName"),
                    ((Long) playerData.get("bonusPoints")).intValue(),
                    (String) playerData.get("playerRank"),
                    (String) playerData.get("registerDate"));
            System.out.println("player logged in successfuly");
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handlePlayerExist() {
        try {
            App.setRoot("PlayerExistView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("please enter new player");
    }

    private static void handleRegisterSuccess() {
        try {
            App.setRoot("RegisterSuccessView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("gratz u have registered");
    }
    
    private static void handleUpdateOnlinePlayers(JSONObject data) {
        // todo
        // this function will manage reveal the player names that came in the data
        // in the online players area
        JSONArray onlinePlayersNames = (JSONArray) data.get("onlinePlayers");
        for (var playerName : onlinePlayersNames) {
//            WelcomeController.printOnlinePlayers("TEST");
            System.out.println(playerName + " is online");
        }
    }
}
