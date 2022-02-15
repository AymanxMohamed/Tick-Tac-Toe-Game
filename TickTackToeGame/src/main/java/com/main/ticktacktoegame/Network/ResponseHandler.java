/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Models.Player;
import static com.main.ticktacktoegame.Network.RequestCreator.*;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;

/**
 *
 * @author ayman
 */
public class ResponseHandler {
    public static void handleResponse(String responseString) {
        
        JSONObject requestObject = (JSONObject) JSONValue.parse(responseString);
        String response = (String) requestObject.get("response");
        JSONObject data = (JSONObject) requestObject.get("data");


        switch (response) {
            case "player not exists":
                handlePlayerNotExist();
                break;
            case "wrong password":
                handleWrongPassword();
                break;
            case "login success":
                handleLoginSuccess(data);
                break;
            case "player exists":
                handlePlayerExist();
                break;
            case "reqister sucsess":
                handleRegisterSuccess();
                break;
            case "online players list":
                onlinePlayersList(data);
                break;
            case "add new player":
                handleAddNewPlayer(data);
                break;
            case "player left the game":
                handlePlayerLeftTheGame(data);
                break;
            case "player in game":
                handlePlayerInGame(data);
                break;
            case "invitation":
                handleInvitation(data);
                break;
            case "invitationSended":
                handleInvitationSended(data);
                break;
            case "invitationRejected":
                handleInvitationRejected(data);
                break;
            case "choose x or o":
                handleChooseXOrO(data);
                break;
            case "start multi mode game":
                handleStartMultiModeGame(data);
                break;
            case "disaple all buttons":
                handleDisapleAllButtons();
                break;
            case "end multi mode game":
                handleEndMultiModeGame(data);
                break;
            case "update player data":
                handleUpdatePlayerData(data);
                break;
            case "updateAvilablePlayesList":
                handleUpdateAvilablePlayersList(data);
                break;
            case "remove multi button":
                handleRemoveMultiButtons(data);
                break;
            case "draw multi moves":
                handleDrawMultiMovesHandler(data);
                break;
            case "enable multi buttons":
                handleEnableMultiButtons();
                break;
            case "draw single moves":
                handleDrawSingleMoves(data);
                break;
            case "remove single moves":
                handleRemoveSingleMoves(data);
                break;
            case "enable single buttons":
                handleEnableSingleButtons();
                break;
                
            case "disaple all buttons single":
                handleDisapleAllButtonsSingle();
                break;
            case "start single mode game":
                handleStartSingleModeGame(data);
                break;
            case "end single mode game":
                handleEndSingleModeGame(data);
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
    private static void onlinePlayersList(JSONObject data) {
        // this function will be send in the beiggining when player logged in
        JSONArray onlinePlayersDataObjects = (JSONArray) data.get("onlinePlayersDataObjects");
        for (int i = 0; i < onlinePlayersDataObjects.size(); i++) {
            JSONObject obj = (JSONObject)onlinePlayersDataObjects.get(i);
            String name = (String) obj.get("name");
            boolean status = (boolean) obj.get("status");
            if (!name.equals(Client.player.getUserName())) {
                Opponent.addOpponent(name, status);
                updateAvilablePlayersList();
                System.out.println(name + " is online");
                System.out.println("is " + name + " in game " + status);
            }
        }
    }
    private static void handleAddNewPlayer(JSONObject data) {
        String playerName = (String) data.get("playerName");
        if (!playerName.equals(Client.player.getUserName())) {
            Opponent.addOpponent(playerName, false);
            updateAvilablePlayersList();
            System.out.println(playerName + " is online now");
        }
    }

    private static void handlePlayerLeftTheGame(JSONObject data) {
        String playerName = (String) data.get("playerName");
        if (!playerName.equals(Client.player.getUserName())) {
            Opponent.removeOpponent(playerName);
            updateAvilablePlayersList();
            System.out.println(playerName + " left the game");
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
        Client.opponnentName = invitationSender;
        // todo 
        // this function should reveal a dialog box with a nice message
        // this message will have 2 buttons one accept and one reject
        // example for the message 
        System.out.println(invitationSender + " want to play with you");
        // if he pressed on accept button you will send an accept invitation
        // request by copy and  paste the below line of code
        
        // when you send accept or reject you are the receiver and the client is the sender
        // this below code is correct
        
        //Client.sendRequest(acceptInvitation(Client.opponnentName, Client.player.getUserName()));
        
        // if he pressed on reject buttoon you will send an reject invitation 
        // by copy and  paste the below line of code
        //Client.sendRequest(rejectInvitation(Client.opponnentName, Client.player.getUserName()));
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

    private static void handleChooseXOrO(JSONObject data) {
        // this function  will reveal a choose x or o view
        String invitationSender = (String) data.get("invitationSender");
        String invitationReciever = (String) data.get("invitationSender");
        Client.opponnentName = invitationReciever;
        // you will have to store the sender and receiver data to be able
        // to pass it 
        // if the client pressed  on x
        // note when you use this method XChoosen or YChoosen you are the sender
        // and the Client.oppxChoosenonentName is The reciever
        Client.sendRequest(xChoosen(Client.player.getUserName(), Client.opponnentName));
        // else if the client pressed on y
        Client.sendRequest(oChoosen(Client.player.getUserName(), Client.opponnentName));
    }

    private static void handleStartMultiModeGame(JSONObject data) {
        String gameID = (String) data.get("gameId");
        String playerX = (String) data.get("playerX");
        String playerO = (String) data.get("playerO");
        
        // you will have to store the game id in the client
        Client.multiModeGameId = gameID;
        if (playerX.equals(Client.player.getUserName())) {
            // then this client is the player x
            // highlight player X  label to inform the client that  he play
            // with x
        } else {
            // this client is the player O
            // highlight player o  label to inform the client that  he play
            // with x
        }
        
        // Switch to the game view with player X on the left with his name
        // and player o on the right with his name also
        // you can also place any nice pic for each player on the left and 
        // the right
    }

    private static void handleDisapleAllButtons() {
        // note: to make handle disaple moves and enable moves so ezz
        // you will have to make an arraylist in the game controller
        // this array list will have all buttons indexed from 0 to 8
        // at disaple all buttons you will loop on the array and disaple all the buttons in it
    }

    private static void handleEndMultiModeGame(JSONObject data) {
        String winner = (String) data.get("winner");
        if (winner.equals(Client.player.getUserName())) {
            // display  winner view that Show a nice message to
            // the player and said that he is the winner
        } else if(winner.equals("draw")) {
            // display the draw view
        } else {
            // display the loser view with a button that have play again and on press play again you will send another game invitation to the current player
        }
    }

    private static void handleUpdatePlayerData(JSONObject data) {
        Client.player.setBonusPoints(((Long) data.get("bonusPoints")).intValue());
        Client.player.setPlayerRank((String) data.get("playerRank"));
        // this is all what this handler do it only get the new data after the game
        // ended and set it in the player data
        // so when you switch to your home view you will be having the new rank
        // and the new bonus points
    }
    private static void  handleUpdateAvilablePlayersList(JSONObject data) {
       ArrayList<String> playerNames = (ArrayList<String>) data.get("playersNames");
        for (var playerName : playerNames) {
            if (!playerName.equals(Client.player.getUserName())) {
                 Opponent player = Opponent.getOpponent(playerName);
                 player.togleInGameStatus();
                 updateAvilablePlayersList();
            }
        }
    }

    private static void updateAvilablePlayersList() {
        // this function will loob on the Opponent.onlinePlayers arrayList
        // every online player have 2 values name and inGame boolean
        // you have rebuild the players list with inGame mark next too each one
        // on every time this method called
        // summary: this method will just rebuild the online players list 
        // with in Game <true or false between every one of them>
        ArrayList<Opponent> onlinePlayers = Opponent.onlinePlayers;
    }

    private static void handleRemoveMultiButtons(JSONObject data) {
        int index = ((Long) data.get("bonusPoints")).intValue();
        // this handler will just remove the button From the buttons array
        // in the controller
    }


    public static void handleDrawMultiMovesHandler(JSONObject data) {
        ArrayList<Integer> gameMoves = (ArrayList<Integer>) data.get("gameMoves");
        // this is an array of game moves start drawing the x and o
        // draw x then o and notice that the array have the indexes of the buttons
        // so always first index in the array represent x 
    }

    private static void handleEnableMultiButtons() {
        // this hanlder will just enable all buttons in the multimode controller 
        // buttons array
    }

    private static void handleDrawSingleMoves(JSONObject data) {
        ArrayList<Integer> gameMoves = (ArrayList<Integer>) data.get("gameMoves");
        // this is an array of game moves start drawing the x and o
        // draw x then o and notice that the array have the indexes of the buttons
        // so always first index in the array represent x 
    }

    private static void handleRemoveSingleMoves(JSONObject data) {
        int index = ((Long) data.get("bonusPoints")).intValue();
        // this handler will just remove the button From the buttons array
        // in the controller
    }
    private static void handleEnableSingleButtons() {
        // this hanlder will just enable all buttons in the singleMode controller 
        // buttons array
        
    }

    private static void handleDisapleAllButtonsSingle() {
        // note: to make handle disaple moves and enable moves so ezz
        // you will have to make an arraylist in the game controller
        // this array list will have all buttons indexed from 0 to 8
        // at disaple all buttons you will loop on the array and disaple all the buttons in it 
    }

    private static void handleStartSingleModeGame(JSONObject data) {
        String gameID = (String) data.get("gameId");
        String choice = (String) data.get("choice");
        
        // you will have to store the game id in the client
        Client.singleModeGameID = gameID;
        if (choice.equals("x")) {
            // then this client is the player x
            // highlight player X  label to inform the client that  he play
            // with x
        } else {
            // this client is the player O
            // highlight player y  label to inform the client that  he play
            // with x
        }
        
        // Switch to the game view with player X on the left with his name
        // and the computer on the right
        // you will deside the computer side according to the choice
        // you can also place any nice pic for each player on the left and 
        // the right
    }

    private static void handleEndSingleModeGame(JSONObject data) {
        String winner = (String) data.get("winner");
        if (winner.equals(Client.player.getUserName())) {
            // display  winner view that Show a nice message to
            // the player and said that he is the winner
        } else if(winner.equals("draw")) {
            // display the draw view
        } else {
            // display the loser view with a button that have play again and on press play again you will send another game invitation to the current player
        }
    }

 
}