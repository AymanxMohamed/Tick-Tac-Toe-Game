/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.Models.Enums.DIFFICULTY;
import com.main.ticktacktoegame.Models.Enums.MappingFunctions;
import static com.main.ticktacktoegame.Network.Client.player;
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
        data.put("invitationSender", player.getUserName());
        data.put("invitationReciever", invitedPlayerName);
        JSONObject request = new JSONObject();
        request.put("request", "game invitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    
    public static String acceptInvitation(String invitationSender, String invitationReciever) {
        JSONObject data = new JSONObject();
        data.put("invitationSender", invitationSender);
        data.put("invitationReciever", invitationReciever);
        JSONObject request = new JSONObject();
        request.put("request", "acceptInvitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String rejectInvitation(String invitationSender, String invitationReciever) {
        Client.opponnentName = null;
        JSONObject data = new JSONObject();
        data.put("invitationSender", invitationSender);
        data.put("invitationReciever", invitationReciever);
        data.put(data, data);
        JSONObject request = new JSONObject();
        request.put("request", "rejectInvitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String xChoosen(String invitationSender, String invitationReciever) {
        JSONObject data = new JSONObject();
        data.put("invitationSender", invitationSender);
        data.put("invitationReciever", invitationReciever);
        data.put("choise", "X");
        JSONObject request = new JSONObject();
        request.put("request", "XorOChoise");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String oChoosen(String invitationSender, String invitationReciever) {
        JSONObject data = new JSONObject();
        data.put("invitationSender", invitationSender);
        data.put("invitationReciever", invitationReciever);
        data.put("choise", "O");
        JSONObject request = new JSONObject();
        request.put("request", "XorOChoise");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
        public static String xChoosenSingle(String difficulty) {
        JSONObject data = new JSONObject();
        data.put("difficulty", difficulty);
        data.put("choise", "X");
        JSONObject request = new JSONObject();
        request.put("request", "XorOChoiseSingle");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String oChoosenSingle(String difficulty) {
        JSONObject data = new JSONObject();
        data.put("difficulty", difficulty);
        data.put("choise", "O");
        JSONObject request = new JSONObject();
        request.put("request", "XorOChoiseSingle");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String multiMove(int index, String gameId) {
        JSONObject data = new JSONObject();
        data.put("gameId", gameId);
        data.put("index", index);
        JSONObject request = new JSONObject();
        request.put("request", "multiMove");
        request.put("data", data);
        return JSONValue.toJSONString(request);   
    }
    public static String playSingleModeGame(String difficulty, String choice) {
        JSONObject data = new JSONObject();
        data.put("difficulty", difficulty);
        data.put("choice", choice);
        JSONObject request = new JSONObject();
        request.put("request", "play single mode game");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String singleMove(int index, String gameId) {
        JSONObject data = new JSONObject();
        data.put("gameId", gameId);
        data.put("index", index);
        JSONObject request = new JSONObject();
        request.put("request", "singleMove");
        request.put("data", data);
        return JSONValue.toJSONString(request);   
    }
}
