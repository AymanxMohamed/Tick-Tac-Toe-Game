/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.DatabaseManager;
import static tictactoegameserver.Network.PlayerHandler.*;
import static tictactoegameserver.Network.ResponseCreator.*;

/**
 *
 * @author ayman, shopaky
 */
public class RequestHandler {

    public static String handleRequest(String reqeustString, PlayerHandler playerHandler) {
        // this function will take a request and return a respons String
        String response = null;

        JSONObject requestObject = (JSONObject) JSONValue.parse(reqeustString);
        String request = (String) requestObject.get("request");
        JSONObject data = (JSONObject) requestObject.get("data");

        switch (request) {
            case "login":
                return handleLogin(data, playerHandler);
            case "register":
                return handleRegister(data);
        }
        return response;
    }

    private static String handleLogin(JSONObject data, PlayerHandler playerHandler) {

        String userName = (String) data.get("username");
        String password = (String) data.get("password");

        DatabaseManager.openDataBaseConnection();
        if (!DatabaseManager.isPlayerExist(userName)) {
            return playerNotExistResponse();
        }
        if (!DatabaseManager.isValidPlayer(userName, password)) {
            return wrongPasswordResponse();
        }
        playerHandler.player = DatabaseManager.getPlayer(userName);
        DatabaseManager.togglePlayerStatus(playerHandler.player);
        DatabaseManager.closeDataBaseConnection();
        sendUpdateOnlinePlayersResponse();
        return loginSuccessResponse(userName, playerHandler.player);
    }
    
    public static String handleRegister(JSONObject data) {

        String userName = (String) data.get("username");
        String password = (String) data.get("password");
        DatabaseManager.openDataBaseConnection();
        if (DatabaseManager.isPlayerExist(userName)) {
            return playerExistResponse();
        
        } else {
            DatabaseManager.addNewPlayer(userName, password);
        }
        DatabaseManager.closeDataBaseConnection();
        return registerSuccessResponse();
    }
}
