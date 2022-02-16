/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Controllers.TicTackToeController;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Models.Player;
import static com.main.ticktacktoegame.Network.RequestCreator.*;
import static com.main.ticktacktoegame.Network.Utility.getIntegerArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.json.simple.*;

/**
 *
 * @author ayman
 */
public class ResponseHandler {

    @FXML
    private Text winnerText;

    public static ArrayList<Button> singleGameModeButtons;

    public static void inializeSingleModeGameButtons() {
        Button button0 = (Button) App.scene.lookup("#button0");
        Button button1 = (Button) App.scene.lookup("#button1");
        Button button2 = (Button) App.scene.lookup("#button2");
        Button button3 = (Button) App.scene.lookup("#button3");
        Button button4 = (Button) App.scene.lookup("#button4");
        Button button5 = (Button) App.scene.lookup("#button5");
        Button button6 = (Button) App.scene.lookup("#button6");
        Button button7 = (Button) App.scene.lookup("#button7");
        Button button8 = (Button) App.scene.lookup("#button8");
        singleGameModeButtons = new ArrayList<>() {
            {
                add(button0);
                add(button1);
                add(button2);
                add(button3);
                add(button4);
                add(button5);
                add(button6);
                add(button7);
                add(button8);
            }
        };
    }

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
            case "player already online":
                handlePlayerAlreadyOnline(data);
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
            case "game invitation":
                handleGameInvitation(data);
                break;
            case "chat invitation":
                handleChatInvitation(data);
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
            case "enable single buttons":
                handleEnableSingleButtons();
                break;
            case "player left multi game":
                handlePlayerLeftMultiGame(data);
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
            case "go to welcome view":
                handleGoToWelcomeView();
                break;
            case "player left chat":
                handlePlayerLeftChatRoom(data);
                break;
            case "add new message":
                handleAddNewMessage(data);
                break;
            case "open chat room":
                handleOpenChatRoom(data);
                break;
            case "player in chat":
                handlePlayerInChat(data);
                break;
            default:
                break;
        }
    }
    /* _____ * _____ Login Responses _____ * _____ */

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

    private static void onlinePlayersList(JSONObject data) {
        // todo
        // this function will be send in the beiggining when player logged in
        JSONArray onlinePlayersDataObjects = (JSONArray) data.get("onlinePlayersDataObjects");
        for (int i = 0; i < onlinePlayersDataObjects.size(); i++) {
            JSONObject obj = (JSONObject) onlinePlayersDataObjects.get(i);
            String name = (String) obj.get("name");
            boolean inGame = (boolean) obj.get("inGame");
            boolean inChat = (boolean) obj.get("inChat");
            if (!name.equals(Client.player.getUserName())) {
                Opponent.addOpponent(name, inGame, inChat);
                updateAvilablePlayersList();
                System.out.println(name + " is online");
                System.out.println("is " + name + " in game " + inGame);
                System.out.println("is " + name + " in chat " + inChat);

            }
        }
    }

    private static void handleAddNewPlayer(JSONObject data) {
        // todo
        String playerName = (String) data.get("playerName");
        if (!playerName.equals(Client.player.getUserName())) {
            Opponent.addOpponent(playerName, false, false);
            updateAvilablePlayersList();
            System.out.println(playerName + " is online now");
        }
    }

    private static void handlePlayerAlreadyOnline(JSONObject data) {
        try {
            App.setRoot("PlayerAlreadyOnlineView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /* _____ * _____ end of Login Responses _____ * _____ */

    /* _____ * _____ Register Responses _____ * _____ */
    private static void handlePlayerExist() {
        try {
            App.setRoot("PlayerExistView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleRegisterSuccess() {
        try {
            App.setRoot("RegisterSuccessView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /* _____ * _____ end of Register Responses _____ * _____ */

    /* _____ * _____ Game invitation for sender Responses _____ * _____ */
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

    private static void handleInvitationSended(JSONObject data) {
        // todo
        // we can show a little info that tell the client that his invitation has been
        // sended
        String invitationReciever = (String) data.get("invitationReciever");
        System.out.println("The invitation to " + invitationReciever + " has been sent successfuly");
    }

    private static void handleInvitationRejected(JSONObject data) {
        // todo
        // we will display a nice message or dialog or any thing that tell the
        // client that the invitation rejected
        // for example
        String invitationReciever = (String) data.get("invitationReciever");
        System.out.println(invitationReciever + " reject your invitation");
    }

    private static void handleChooseXOrO(JSONObject data) {
        // this function will reveal a choose x or o view
        String invitationSender = (String) data.get("invitationSender");
        String invitationReciever = (String) data.get("invitationSender");
        Client.opponnentName = invitationReciever;
        // you will have to store the sender and receiver data to be able
        // to pass it
        // if the client pressed on x
        // note when you use this method XChoosen or YChoosen you are the sender
        // and the Client.opponentName is The reciever
        Client.sendRequest(xChoosen(Client.player.getUserName(), Client.opponnentName));
        // else if the client pressed on y
        Client.sendRequest(oChoosen(Client.player.getUserName(), Client.opponnentName));
    }

    /* _____ * _____ Game invitation for receiver Responses _____ * _____ */

    private static void handleGameInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        String invitationReciever = (String) data.get("invitationReciever");
        Client.opponnentName = invitationSender;
        // todo
        // this function should reveal a dialog box with a nice message
        // this message will have 2 buttons one accept and one reject
        // example for the message
        System.out.println(invitationSender + " want to play with you");
        // if he pressed on accept button you will send an accept invitation
        // request by copy and paste the below line of code

        // when you send accept or reject you are the receiver and the client is the
        // sender
        // this below code is correct

        // Client.sendRequest(acceptInvitation(Client.opponnentName,
        // Client.player.getUserName()));

        // if he pressed on reject buttoon you will send an reject invitation
        // by copy and paste the below line of code
        // Client.sendRequest(rejectInvitation(Client.opponnentName,
        // Client.player.getUserName()));
    }

    /* _____ * _____ Multi Mode Game Responses _____ * _____ */

    private static void handleStartMultiModeGame(JSONObject data) {
        String gameID = (String) data.get("gameId");
        String playerX = (String) data.get("playerX");
        String playerO = (String) data.get("playerO");

        // you will have to store the game id in the client
        Client.multiModeGameId = gameID;
        if (playerX.equals(Client.player.getUserName())) {
            // then this client is the player x
            // highlight player X label to inform the client that he play
            // with x
        } else {
            // this client is the player O
            // highlight player o label to inform the client that he play
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
        // at disaple all buttons you will loop on the array and disaple all the buttons
        // in it
    }

    private static void handleEndMultiModeGame(JSONObject data) {
        String winner = (String) data.get("winner");
        if (winner.equals(Client.player.getUserName())) {
            // display winner view that Show a nice message to
            // the player and said that he is the winner
        } else if (winner.equals("draw")) {
            // display the draw view
        } else {
            // display the loser view with a button that have play again and on press play
            // again you will send another game invitation to the current player
        }
    }

    public static void handleDrawMultiMovesHandler(JSONObject data) {
        ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
        ArrayList<Integer> gameMoves = getIntegerArray(objectArray);
        // this is an array of game moves start drawing the x and o
        // draw x then o and notice that the array have the indexes of the buttons
        // so always first index in the array represent x
    }

    private static void handleRemoveMultiButtons(JSONObject data) {
        int index = ((Long) data.get("bonusPoints")).intValue();
        // this handler will just remove the button From the buttons array
        // in the controller
    }

    private static void handleEnableMultiButtons() {
        // this hanlder will just enable all buttons in the multimode controller
        // buttons array
    }

    private static void handlePlayerLeftMultiGame(JSONObject data) {
        String playerName = (String) data.get("playerName");
        // this method will show up a message that say that
        System.out.println("unfortunatly " + playerName + " has left the game");
        // and switch the player to his home page
    }

    private static void handleGoToWelcomeView() {
        // this handler will just go to the welcome view
    }
    /* _____ * _____ end of multi Mode Game Responses _____ * _____ */

    /* _____ * _____ Single Mode Game Responses _____ * _____ */
    private static void handleStartSingleModeGame(JSONObject data) {
        String gameID = (String) data.get("gameId");
        String choice = (String) data.get("choice");
        Client.singleModeGameID = gameID;
        try {
            App.setRoot("TicTackToe");
            if (choice.equals("X")) {
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerXLabel.setText(Client.player.getUserName());
                playerOLabel.setText("Computer");
            } else {
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerXLabel.setText("Computer");
                playerOLabel.setText(Client.player.getUserName());
            }
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void handleDrawSingleMoves(JSONObject data) {
        ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
        ArrayList<Integer> gameMoves = getIntegerArray(objectArray);
        Platform.runLater(()-> TicTackToeController.drawMoves(gameMoves));
    }

    private static void handleEnableSingleButtons() {
//        Platform.runLater(()-> Client.currentGame.enableAllButtons());
        Platform.runLater(()-> TicTackToeController.enableAllButtons());
    }

    private static void handleDisapleAllButtonsSingle() {
//        Platform.runLater(()-> Client.currentGame.disapleAllButtons());
        Platform.runLater(()-> TicTackToeController.disapleAllButtons());
    }

    private static void handleEndSingleModeGame(JSONObject data) {
        String winner = (String) data.get("winner");
        if (winner.equals(Client.player.getUserName())) {
            // display winner view that Show a nice message to
            // the player and said that he is the winner
        } else if (winner.equals("draw")) {
            // display the draw view
        } else {
            // display the loser view with a button that have play again and on press play
            // again you will send another game invitation to the current player
        }
    }
    /* _____ * _____ end of Single Mode Game Responses _____ * _____ */

    /* _____ * _____ start of Chat Room Responses _____ * _____ */

    private static void handleAddNewMessage(JSONObject data) {
        String message = (String) data.get("message");
        String sender = (String) data.get("sender");
        // This method will just add this message in the text area
        String messageToAdd;
        if (sender.equals(Client.player.getUserName())) {
            messageToAdd = "me: " + message.trim();
        } else {
            messageToAdd = sender + ": " + message.trim();
        }
        // just add the message the message box area in the chat view
    }

    private static void handleOpenChatRoom(JSONObject data) {
        String chatID = (String) data.get("chatID");
        String sender = (String) data.get("sender");
        String receiver = (String) data.get("receiver");

        // you will have to store the game id in the client
        Client.chatRoomId = chatID;
        if (sender.equals(Client.player.getUserName())) {
            // then this client is sender
            // highlight player label to inform the client
        } else {
            // this client is the reciver
            // highlight recieverName
        }

        // Switch to the chat view with sender on the left with his name
        // and receiveron the right with his name also
        // you can also place any nice pic for each player on the left and
        // the right
    }

    private static void handlePlayerLeftChatRoom(JSONObject data) {
        String playerName = (String) data.get("playerName");
        // this method will show up a message that say that
        System.out.println("unfortunatly " + playerName + " has left the chat");
        // and switch the player home page
    }

    private static void handlePlayerInChat(JSONObject data) {
        // todo
        // this function will reveal an info message that tell the player
        // that the player that he invinted is currentlty in chat
        String invitedPlayer = (String) data.get("invitedPlayer");
        System.out.println(invitedPlayer + " is currently in chat");

        // todo later
        // we can send a list of current players names and update it frequently
        // with the same logic of the online players and prevent the client from
        // sending invitation request to the players that is in game

    }

    /* _____ * _____ end of Chat Room Responses _____ * _____ */
    /* _____ * _____ general Responses _____ * _____ */
    private static void handleUpdatePlayerData(JSONObject data) {
        Client.player.setBonusPoints(((Long) data.get("bonusPoints")).intValue());
        Client.player.setPlayerRank((String) data.get("playerRank"));
        // this is all what this handler do it only get the new data after the game
        // ended and set it in the player data
        // so when you switch to your home view you will be having the new rank
        // and the new bonus points
    }

    private static void handleUpdateAvilablePlayersList(JSONObject data) {
        ArrayList<String> playerNames = (ArrayList<String>) data.get("playersNames");
        String update = (String) data.get("update");
        for (var playerName : playerNames) {
            if (!playerName.equals(Client.player.getUserName())) {
                Opponent player = Opponent.getOpponent(playerName);
                if (update.equals("inGame")) {
                    player.togleInGameStatus();
                } else {
                    player.toogleInChatStatus();
                }
            }
        }
        updateAvilablePlayersList();
    }

    private static void updateAvilablePlayersList() {
        // this function will loob on the Opponent.onlinePlayers arrayList
        // every online player have 2 values name and inGame boolean
        // you have rebuild the players list with inGame mark next too each one
        // on every time this method called
        // summary: this method will just rebuild the online players list
        // with in Game <true or false between every one of them>
        // with also in Chat <true or false between every one of them>
        ArrayList<Opponent> onlinePlayers = Opponent.onlinePlayers;
    }

    private static void handlePlayerLeftTheGame(JSONObject data) {
        String playerName = (String) data.get("playerName");
        if (!playerName.equals(Client.player.getUserName())) {
            Opponent.removeOpponent(playerName);
            updateAvilablePlayersList();
            System.out.println(playerName + " left the game");
        }
    }
    /* _____ * _____ end of general Responses _____ * _____ */

    private static void handleChatInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        String invitationReciever = (String) data.get("invitationReciever");
        Client.opponnentName = invitationSender;
        // todo
        // this function should reveal a dialog box with a nice message
        // this message will have 2 buttons one accept and one reject
        // example for the message
        System.out.println(invitationSender + " want to have a private chat with you");
        // if he pressed on accept button you will send an accept invitation
        // request by copy and paste the below line of code

        // when you send accept or reject you are the receiver and the client is the
        // sender
        // this below code is correct

        // Client.sendRequest(acceptChatInvitation(Client.opponnentName,
        // Client.player.getUserName()));

        // if he pressed on reject buttoon you will send an reject invitation
        // by copy and paste the below line of code
        // Client.sendRequest(rejectChatInvitation(Client.opponnentName,
        // Client.player.getUserName()));
    }

}