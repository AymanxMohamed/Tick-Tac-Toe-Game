/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Network;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.DatabaseManager;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import tictactoegameserver.Database.Entities.Player;

/**
 *
 * @author ayman, shopaky
 */
public class RequestHandler {

    public static String handleRequest(String reqeustString) {
        // this function will take a request and return a respons String
        String response = null;

        JSONObject requestObject = (JSONObject) JSONValue.parse(reqeustString);
        String request = (String) requestObject.get("request");
        JSONObject data = (JSONObject) requestObject.get("data");

        switch (request) {
            case "login":
                return handleLogin(data);
            case "register":
                return handleRegister(data);
        }
        return response;
    }

    private static String handleLogin(JSONObject data) {

        String userName = (String) data.get("username");
        String password = (String) data.get("password");

        JSONObject responseObject = new JSONObject();
        try {
            if (!DatabaseManager.isPlayerExist(userName)) {
                responseObject.put("response", "player not exists");
                return JSONValue.toJSONString(responseObject);
            }
            if (!DatabaseManager.isValidPlayer(userName, password)) {
                responseObject.put("response", "wrong password");
                return JSONValue.toJSONString(responseObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        responseObject.put("response", "login success");
        responseObject.put("data", getPlayerJsonObject(userName));

        return JSONValue.toJSONString(responseObject);
    }

    public static String handleRegister(JSONObject data) {

        String userName = (String) data.get("username");
        String password = (String) data.get("password");

        JSONObject responseObject = new JSONObject();
        try {
            if (DatabaseManager.isPlayerExist(userName)) {
                responseObject.put("response", "player exists");
                return JSONValue.toJSONString(responseObject);
            } else {
                DatabaseManager.addNewPlayer(userName, password);
                responseObject.put("response", "reqister sucsess");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return JSONValue.toJSONString(responseObject);
    }

    public static JSONObject getPlayerJsonObject(String userName) {

        Player player = null;
        try {
            player = DatabaseManager.getPlayer(userName);
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONObject dataObject = new JSONObject();

        if (player != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedDateTime = player.getRegisterDate().format(formatter);
            dataObject.put("userName", player.getUserName());
            dataObject.put("bonusPoints", player.getBonusPoints());
            dataObject.put("playerRank", MappingFunctions.mapPlayerRank(player.getPlayerRank()));
            dataObject.put("registerDate", formatedDateTime);
        }
        return dataObject;
    }

    public static void tryJson() {

        // Step 1: prepare the jsonObject that you want to send
        // as a request or as a response that consists of action and data
        JSONObject data = new JSONObject();
        data.put("username", "ayman");
        data.put("password", "1234567");
        System.out.println(data);

        JSONObject request = new JSONObject();
        request.put("action", "login");
        request.put("data", data);
        System.out.println(request);

        // Step 2: Convert the requsetJson object to string
        // send it to the server
        String jsonText = JSONValue.toJSONString(request);

        // Step 3: in the server receive the jsonText and parse it to jsonObject
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonText);

        // Step 4: fetch the data from the jsonObject
        String action = (String) jsonObject.get("action");

        // Note: data will be also json object and you have to use casting
        JSONObject dataObject = (JSONObject) jsonObject.get("data");
        String userName = (String) dataObject.get("username");
        String password = (String) dataObject.get("password");

        System.out.println("username: " + userName);
        System.out.println("password: " + password);
    }
}
