/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.DatabaseManager;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import tictactoegameserver.Database.Entities.Enums.PLAYER_RANK;
import tictactoegameserver.Database.Entities.Player;

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
        public static JSONObject getPlayerJsonObject(String userName, Player player) {

        JSONObject dataObject = new JSONObject();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedDateTime = player.getRegisterDate().format(formatter);
        dataObject.put("userName", player.getUserName());
        dataObject.put("bonusPoints", player.getBonusPoints());
        dataObject.put("playerRank", MappingFunctions.mapPlayerRank(player.getPlayerRank()));
        dataObject.put("registerDate", formatedDateTime);

        return dataObject;
    }
    public static JSONObject getOnlinePlayersJsonObject() {
        ArrayList<Player> onlinePlayers = null;
        DatabaseManager.openDataBaseConnection();
        onlinePlayers = DatabaseManager.getOnlinePlayers();
        DatabaseManager.closeDataBaseConnection();

        JSONArray onlinePlayersNames = new JSONArray();
        for (var player : onlinePlayers) {
            onlinePlayersNames.add(player.getUserName());
        }
        JSONObject dataObject = new JSONObject();
        dataObject.put("onlinePlayers", onlinePlayersNames);

        return dataObject;
    }
    public static String updateOnlinePlayersResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "update online players");
        responseObject.put("data", getOnlinePlayersJsonObject());
        return JSONValue.toJSONString(responseObject);
    }

    public static String playerExistResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "player exists");
        return JSONValue.toJSONString(responseObject);
    }
    public static String registerSuccessResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "reqister sucsess");
        return JSONValue.toJSONString(responseObject);
    }
    public static String playerInGameResponse(String invitedPlayerName) {
        JSONObject dataObject = new JSONObject();
        dataObject.put("invitedPlayer", invitedPlayerName);

        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "player in game");
        responseObject.put("data", dataObject);

        return JSONValue.toJSONString(responseObject);
    }
    public static String invitationFromPlayerRequest(JSONObject data) {

        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitation");
        responseObject.put("data", data);

        return JSONValue.toJSONString(responseObject);
    }
    public static String invitationSendedResponse(JSONObject data) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationSended");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String invitationRejectedResponse(JSONObject data) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationRejected");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String doNothingResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "doNothing");
        return JSONValue.toJSONString(responseObject);
    }
    public static String chooseXOrOResponse(JSONObject data) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "choose x or o");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String startMultiModeGameResponse(String gameId, String playerX, String playerO) {
        JSONObject data = new JSONObject();
        data.put("gameId", gameId);
        data.put("playerX", playerX);
        data.put("playerO", playerO);

        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "start multi mode game");
        responseObject.put("data", data);

        return JSONValue.toJSONString(responseObject);
    }
    public static String disapleAllButtonsResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "disaple all buttons");
        return JSONValue.toJSONString(responseObject);
    }
    public static String createGameMovesJson(ArrayList<Integer> gameMoves) {
        JSONObject gameRecord = new JSONObject();
        gameRecord.put("gameMoves", gameMoves);
        return JSONValue.toJSONString(gameRecord);
    }
    public static ArrayList<Integer> getGameMovesArrayList(String gameMovesJson) {
        JSONObject gameMovesObject = (JSONObject) JSONValue.parse(gameMovesJson);
        return (ArrayList<Integer>) gameMovesObject.get("gameMoves");
    }
    public static String endMultiModeGameResponse(String winner) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "end multi mode game");
        JSONObject data = new JSONObject();
        data.put("winner", "winner");
        return JSONValue.toJSONString(responseObject);
    }
    public static String updatePlayerDataResponse(Player player) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "update player data");
        JSONObject data = new JSONObject();
        data.put("bonusPoints", player.getBonusPoints());
        data.put("playerRank", MappingFunctions.mapPlayerRank(player.getPlayerRank()));
        return JSONValue.toJSONString(responseObject);
        
    }
    
    public static String updateAvilablePlayersList(ArrayList<String> playersNames, String command) {
        JSONObject data = new JSONObject();
        data.put("playersNames", playersNames);
        data.put("command", command);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "updateAvilablePlayesList");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }

}

