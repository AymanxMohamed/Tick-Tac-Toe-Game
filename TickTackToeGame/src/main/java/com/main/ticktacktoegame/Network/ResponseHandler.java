/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Player;
import static com.main.ticktacktoegame.Network.RequestCreator.*;
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
            case "player in game":
                data = (JSONObject) requestObject.get("data");
                handlePlayerInGame(data);
                break;
            case "invitation":
                data = (JSONObject) requestObject.get("data");
                handleInvitation(data);
                break;
            case "invitationSended":
                data = (JSONObject) requestObject.get("data");
                handleInvitationSended(data);
                break;
            case "invitationRejected":
                data = (JSONObject) requestObject.get("data");
                handleInvitationRejected(data);
                break;
            default:
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

    private static void handlePlayerInGame(JSONObject data) {
        // todo
        // this function will reveal an info message that tell the player
        // that the player that he invinted is currentlty in game
        String invitedPlayer = (String) data.get("invitedPlayer");
        System.out.println(invitedPlayer + " is currently in game");
        
        // todo later 
        // we can send a list of current players names and update it frequently
        // with the same logic of the online players and prevent the client from
        // sending invitation request to the players that is in game
        
    }

    private static void handleInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");        
        String invitationReciever = (String) data.get("invitationReciever");
  
        // todo 
        // this function should reveal a dialog box with a nice message
        // this message will have 2 buttons one accept and one reject
        // example for the message 
        System.out.println(invitationSender + " want to play with you");
        // if he pressed on accept button you will send an accept invitation
        // request by copy and  paste the below line of code
        //Client.sendRequest(acceptInvitation(invitationSender, invitationReciever));
        
        // if he pressed on reject buttoon you will send an reject invitation 
        // by copy and  paste the below line of code
        // Client.sendRequest(rejectInvitation(invitationSender, invitationReciever));
    }

    private static void handleInvitationSended(JSONObject data) {
        // todo
        // we can show a little info that tell the client that his invitation has been sended
        String invitationReciever = (String) data.get("invitationReciever");
        System.out.println("The invitation to " + invitationReciever + " has been sent successfuly");
    }

    private static void handleInvitationRejected(JSONObject data) {
        // todo
        // we will display a nice message or dialog or any thing that tell the
        // client that the invitation rejected
        // for example
        String invitationReciever = (String) data.get("invitationReciever");
        System.out.println(invitationReciever + " reject your game request");
    }
    
    
    
}
