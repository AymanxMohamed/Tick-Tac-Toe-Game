/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;
import static com.main.ticktacktoegame.Network.Client.player;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ayman
 */
public class RequestCreator {
    
    /*_____ * _____ Login & Register Requests _____ * _____ */
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
    /*_____ * _____ end of Login & Register Requests _____ * _____ */
    
    /*_____ * _____ Multi Mode Game & Chat Requests _____ * _____ */
    
                /*_____ * _____ sender _____ * _____ */
    
    public static String invitePlayer(String invitedPlayerName) {
        JSONObject data = new JSONObject();
        data.put("invitationSender", player.getUserName());
        data.put("invitationReciever", invitedPlayerName);
        JSONObject request = new JSONObject();
        request.put("request", "game invitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String invitePlayerForChat(String invitedPlayerName) {
        JSONObject data = new JSONObject();
        data.put("invitationSender", player.getUserName());
        data.put("invitationReciever", invitedPlayerName);
        JSONObject request = new JSONObject();
        request.put("request", "chat invitation");
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
                /*_____ * _____ receiver _____ * _____ */
    
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
    public static String acceptChatInvitation(String invitationSender, String invitationReciever) {
        JSONObject data = new JSONObject();
        data.put("invitationSender", invitationSender);
        data.put("invitationReciever", invitationReciever);
        JSONObject request = new JSONObject();
        request.put("request", "acceptChatInvitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
    public static String rejectChatInvitation(String invitationSender, String invitationReciever) {
        Client.opponnentName = null;
        JSONObject data = new JSONObject();
        data.put("invitationSender", invitationSender);
        data.put("invitationReciever", invitationReciever);
        data.put(data, data);
        JSONObject request = new JSONObject();
        request.put("request", "rejectChatInvitation");
        request.put("data", data);
        return JSONValue.toJSONString(request);
    }
             /*_____ * _____ game process _____ * _____ */
    
    public static String multiMove(int index) {
        JSONObject data = new JSONObject();
        data.put("gameId", Client.multiModeGameId);
        data.put("index", index);
        JSONObject request = new JSONObject();
        request.put("request", "multiMove");
        request.put("data", data);
        return JSONValue.toJSONString(request);   
    }
    public static String forceEndMultiGaame() {
        JSONObject data = new JSONObject();
        data.put("gameId", Client.multiModeGameId);
        JSONObject request = new JSONObject();
        request.put("request", "force end multi mode game");
        request.put("data", data);
        return JSONValue.toJSONString(request); 
    }
    
    public static String leaveChat() {
        JSONObject data = new JSONObject();
        data.put("chatId", Client.chatRoomId);
        JSONObject request = new JSONObject();
        request.put("request", "leave chat");
        request.put("data", data);
        return JSONValue.toJSONString(request); 
    }
    /*_____ * _____ end of  Multi Mode Game Requests _____ * _____ */
    
    /*_____ * _____  Single Mode Game Requests _____ * _____ */
    
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
    public static String forceEndSingleGaame() {
        JSONObject data = new JSONObject();
        data.put("gameId", Client.multiModeGameId);
        JSONObject request = new JSONObject();
        request.put("request", "force end single mode game");
        request.put("data", data);
        return JSONValue.toJSONString(request); 
    }
    /*_____ * _____  end of Single Mode Game Requests _____ * _____ */
    /*_____ * _____  Logout Requests _____ * _____ */
    public static String logout() {
        JSONObject data = new JSONObject();
        data.put("playerName", Client.player.getUserName());
        data.put("singleGameId", Client.singleModeGameID);
        data.put("multiGameId", Client.multiModeGameId);
        data.put("chatRoomId", Client.chatRoomId);
        JSONObject request = new JSONObject();
        request.put("request", "logout");
        request.put("data", data);
        return JSONValue.toJSONString(request);  
    }
    /*_____ * _____  end of Logout Requests _____ * _____ */


}
