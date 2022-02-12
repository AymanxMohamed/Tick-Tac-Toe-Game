/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Player;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        // todo
        // you have to send an error message to the player view that say that he entered
        // wrong username
        System.out.println("player not exist");
    }

    private static void handleWrongPassword() {
        // todo
        // you have to send an error message to the player login view that say that
        // entered wrong password
        System.out.println("wrong password");
    }

    private static void handleLoginSuccess(JSONObject playerData) {
        try {
            // todo
            Client.player = new Player(
                    (String) playerData.get("username"),
                    ((Long) playerData.get("bonusPoints")).intValue(),
                    (String) playerData.get("playerRank"),
                    (String) playerData.get("registerDate"));
            // switch the player to his home page with it's corresponding information from and a nice welcome back messaage
            // his object
            System.out.println("player loged in successfuly");
            App.setRoot("TicTackToe");
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void handlePlayerExist() {
        // todo
        // you have to show the register view again but with an error message that shows that the player exist before
          System.out.println("please enter new player");
    }

    private static void handleRegisterSuccess() {
        // todo
        // you have to redirect the user to the login view and show a message that say that he register successfuly
        System.out.println("gratz u have registered");
    }
    private static void handleUpdateOnlinePlayers(JSONObject data) {
        // todo
        // this function will manage reveal the player names that came in the data
        // in the online players area
        JSONArray onlinePlayersNames = (JSONArray) data.get("onlinePlayers");
        for (var player : onlinePlayersNames) {
            System.out.println(player + " is online");
        }
    }
}
