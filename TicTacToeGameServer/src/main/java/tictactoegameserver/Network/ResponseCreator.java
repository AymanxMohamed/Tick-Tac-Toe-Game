/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.Entities.Player;
import static tictactoegameserver.Network.RequestHandler.getOnlinePlayersJsonObject;
import static tictactoegameserver.Network.RequestHandler.getPlayerJsonObject;

/**
 *
 * @author ayman
 */
public class ResponseCreator {
    public static String playerNotExistResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "player not exists");
        return JSONValue.toJSONString(responseObject);
    }
    public static String wrongPasswordResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "wrong password");
        return JSONValue.toJSONString(responseObject);
    }

    static String loginSuccessResponse(String userName, Player player) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "login success");
        responseObject.put("data", getPlayerJsonObject(userName, player));
        return JSONValue.toJSONString(responseObject);
    }
    public static String updateOnlinePlayersResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "update online players");
        responseObject.put("data", getOnlinePlayersJsonObject());
        return JSONValue.toJSONString(responseObject);
    }

}

