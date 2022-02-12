/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ayman
 */
public class RequestCreator {
    
    public static String login(String userName, String password) {
        JSONObject data = new JSONObject();
        data.put("username", userName);
        data.put("password", password);
        JSONObject request = new JSONObject();
        request.put("request", "login");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    
    public static String register(String userName, String password) {
        JSONObject data = new JSONObject();
        data.put("username", userName);
        data.put("password", password);
        JSONObject request = new JSONObject();
        request.put("request", "register");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String invitePlayer(String invitedPlayerName) {
        JSONObject data = new JSONObject();
        data.put("invitedPlayername", invitedPlayerName);
        data.put(data, data);
        JSONObject request = new JSONObject();
        request.put("request", " game invitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
}
