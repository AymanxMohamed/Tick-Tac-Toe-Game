/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.Models.Player;

import org.json.simple.*;

/**
 *
 * @author ayman
 */
public class ResponseHandler {
    public static String handleResponse(String responseString) {
        JSONObject requestObject = (JSONObject) JSONValue.parse(responseString);
        String response = (String) requestObject.get("response");

        switch (response) {
            case "player not exists":
                handlePlayerExist();
                break;
            case "wrong password":
                handleWrongPassword();
                break;
            case "login success":
                JSONObject data = (JSONObject) requestObject.get("data");
                handleLoginSuccess(data);
                break;
            case "player exists":
                handlePlayerExist();
                // this means that the player tried to reqister but the user name exists
                break;
            case "reqister sucsess":
                handleRegisterSuccess();
                break;
        }
        return null;
    }

    private static void handlePlayerNotExist() {
        // todo
        // you have to send an error message to the player view that say that he entered
        // wrong username
    }

    private static void handleWrongPassword() {
        // todo
        // you have to send an error message to the player login view that say that
        // entered wrong password
    }

    private static void handleLoginSuccess(JSONObject playerData) {
        // todo
        Client.player = new Player(
                (String) playerData.get("username"),
                (int) playerData.get("bonusPoints"),
                (String) playerData.get("playerRank"),
                (String) playerData.get("registerDate"));
        // switch the player to his home page with it's corresponding information from and a nice welcome back messaage
        // his object
    }

    private static void handlePlayerExist() {
        // todo
        // you have to show the register view again but with an error message that shows that the player exist before
    }

    private static void handleRegisterSuccess() {
        // todo
        // you have to redirect the user to the login view and show a message that say that he register successfuly
        
    }
}
