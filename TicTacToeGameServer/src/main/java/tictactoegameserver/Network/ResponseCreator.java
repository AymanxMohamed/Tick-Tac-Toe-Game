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
import tictactoegameserver.Database.Entities.Player;
import static tictactoegameserver.Network.Utility.getPlayerHandler;

/**
 *
 * @author ayman
 */
public class ResponseCreator {
    
    /*_____ * _____ Login  Responses _____ * _____ */
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
    
   
    public static String onlinePlayersListResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "online players list");
        responseObject.put("data", getOnlinePlayersJsonObject());
        return JSONValue.toJSONString(responseObject);
    }

    public static String addNewPlayerResponse(String userName) {
        JSONObject dataObject = new JSONObject();
        dataObject.put("playerName", userName);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "add new player");
        responseObject.put("data", dataObject);
        return JSONValue.toJSONString(responseObject);
    }
    /*_____ * _____ end of Login  Responses _____ * _____ */

    /*_____ * _____ Register Responses _____ * _____ */
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
    /*_____ * _____ end of Register Responses _____ * _____ */
    
    /*_____ * _____ Game invitation for sender Responses _____ * _____ */
    public static String playerInGameResponse(String invitedPlayerName) {
        JSONObject dataObject = new JSONObject();
        dataObject.put("invitedPlayer", invitedPlayerName);

        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "player in game");
        responseObject.put("data", dataObject);

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
    public static String chooseXOrOResponse(JSONObject data) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "choose x or o");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    
    /*_____ * _____ Game invitation for receiver Responses _____ * _____ */
    
    public static String invitationFromPlayerRequest(JSONObject data) {

        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitation");
        responseObject.put("data", data);

        return JSONValue.toJSONString(responseObject);
    }

    public static String doNothingResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "doNothing");
        return JSONValue.toJSONString(responseObject);
    }
    
    /*_____ * _____ Multi Mode Game Responses _____ * _____ */
    
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
    
    public static String endMultiModeGameResponse(String winner) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "end multi mode game");
        JSONObject data = new JSONObject();
        data.put("winner", "winner");
        return JSONValue.toJSONString(responseObject);
    }
    public static String removeMultiButtonResponse(int index) {
        JSONObject data = new JSONObject();
        data.put("index", index);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "remove multi button");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String drawMultiMovesResponse(ArrayList<Integer> gameMoves) {
        JSONObject data = new JSONObject();
        data.put("gameMoves", gameMoves);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "draw multi moves");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    
    public static String enableMultiButtonsResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "enable multi buttons");
        return JSONValue.toJSONString(responseObject);
    }
    
    /*_____ * _____ Single Mode Game Responses _____ * _____ */
    
    public static String startSingleModeGameResponse(String gameId, String choice) {
        JSONObject data = new JSONObject();
        data.put("gameId", gameId);
        data.put("choice", choice);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "start single mode game");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String removeSingleButtonResponse(int index) {
        JSONObject data = new JSONObject();
        data.put("index", index);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "remove single button");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String disapleAllButtonsSingleResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "disaple all buttons single");
        return JSONValue.toJSONString(responseObject);
    }
    
    public static String endSingleModeGameResponse(String winner) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "end single mode game");
        JSONObject data = new JSONObject();
        data.put("winner", "winner");
        return JSONValue.toJSONString(responseObject);
    }
    public static String drawSingleMovesResponse(ArrayList<Integer> gameMoves) {
        JSONObject data = new JSONObject();
        data.put("gameMoves", gameMoves);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "draw single moves");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static String enableSingleButtonsResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "enable single buttons");
        return JSONValue.toJSONString(responseObject);
    }
    
    
    
     /*_____ * _____ general Responses _____ * _____ */
    public static String updatePlayerDataResponse(Player player) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "update player data");
        JSONObject data = new JSONObject();
        data.put("bonusPoints", player.getBonusPoints());
        data.put("playerRank", MappingFunctions.mapPlayerRank(player.getPlayerRank()));
        return JSONValue.toJSONString(responseObject);
        
    }
    public static String updateAvilablePlayersList(ArrayList<String> playersNames) {
        JSONObject data = new JSONObject();
        data.put("playersNames", playersNames);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "updateAvilablePlayesList");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    
    /*_____ * _____ Logout  Responses _____ * _____ */
    public static String playerLeftTheGameResponse(String userName){
        JSONObject dataObject = new JSONObject();
        dataObject.put("playerName", userName);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "player left the game");
        responseObject.put("data", dataObject);
        return JSONValue.toJSONString(responseObject);
    }
    public static String updateOnlinePlayersResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "update online players");
        responseObject.put("data", getOnlinePlayersJsonObject());
        return JSONValue.toJSONString(responseObject);
    }
    /*_____ * _____ Logout  Responses _____ * _____ */
    
    /*_____ * _____ general Json methods _____ * _____ */
    public static String createGameMovesJson(ArrayList<Integer> gameMoves) {
        JSONObject gameRecord = new JSONObject();
        gameRecord.put("gameMoves", gameMoves);
        return JSONValue.toJSONString(gameRecord);
    }
    public static ArrayList<Integer> getGameMovesArrayList(String gameMovesJson) {
        JSONObject gameMovesObject = (JSONObject) JSONValue.parse(gameMovesJson);
        return (ArrayList<Integer>) gameMovesObject.get("gameMoves");
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

        JSONArray onlinePlayersDataObjects = new JSONArray();
        for (var player : onlinePlayers) {
            PlayerHandler playerHandler = getPlayerHandler(player.getUserName());
            boolean inGame = playerHandler.inGame;
            JSONObject data = new JSONObject();
            data.put("name", player.getUserName());
            data.put("status", inGame);
            onlinePlayersDataObjects.add(data);
        }
        JSONObject dataObject = new JSONObject();
        dataObject.put("onlinePlayersDataObjects", onlinePlayersDataObjects);

        return dataObject;
    }
    



}

