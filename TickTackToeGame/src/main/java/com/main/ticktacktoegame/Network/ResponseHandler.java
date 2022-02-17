/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Controllers.TicTackToeController;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Models.Player;
import static com.main.ticktacktoegame.Network.Utility.getIntegerArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
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
                handleDisapleAllButtonsSingle();
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
            case "draw multi moves":
                handleDrawSingleMoves(data);
                break;
            case "enable multi buttons":
                handleEnableSingleButtons();
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
            case "ContinueGame":
                handleContinueGame(data);
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
        if (Client.player != null && !playerName.equals(Client.player.getUserName())) {
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
        System.out.println("in handle player in game");
        try {
            String invitedPlayer = (String) data.get("invitedPlayer");
            System.out.println(invitedPlayer + " is currently in game");
            App.setRoot("PlayerIsCurrentlyInGameView");
            Label receiverName = (Label)App.scene.lookup("#playerName");
            receiverName.setText(invitedPlayer);
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void handleInvitationSended(JSONObject data) {
        System.out.println("in handle invitation sended multi single mode game");
        String invitationReciever = (String) data.get("invitationReciever");
        System.out.println("The invitation to " + invitationReciever + " has been sent successfuly");
    }

    private static void handleInvitationRejected(JSONObject data) {
        System.out.println("in handle invitation rejected multi single mode game");
        try {
            String invitationReciever = (String) data.get("invitationReciever");
            System.out.println(invitationReciever + " reject your invitation");
            App.setRoot("InvitationRejectedView");
            Label receiverName = (Label)App.scene.lookup("#playerName");
            receiverName.setText(invitationReciever);
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void handleChooseXOrO(JSONObject data) {
        System.out.println("in handle chooseXorO multi single mode game");
        try {
            String invitationReciever = (String) data.get("invitationReciever");
            Client.opponnentName = invitationReciever;
            App.setRoot("chooseXorOMulti");
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* _____ * _____ Game invitation for receiver Responses _____ * _____ */

    private static void handleGameInvitation(JSONObject data) {
        System.out.println("in handle gane invitation multi single mode game");
        String invitationSender = (String) data.get("invitationSender");
        Client.opponnentName = invitationSender;
        try {
            App.setRoot("InvitationFromPlayer");
            Label senderName = (Label)App.scene.lookup("#playerName");
            senderName.setText(invitationSender);
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* _____ * _____ Multi Mode Game Responses _____ * _____ */

    private static void handleStartMultiModeGame(JSONObject data) {
        System.out.println("in handle start multi mode game");
        String gameID = (String) data.get("gameId");
        String playerX = (String) data.get("playerX");
        String playerO = (String) data.get("playerO");
        if (playerX.equals(Client.player.getUserName())) {
             Client.cuurentCase = "X";
        } else {
             Client.cuurentCase = "O";
        }
        Client.multiModeGameId = gameID;
        Client.singleModeGameID = "";
        try {
            App.setRoot("TicTackToe");
            Label playerXLabel = (Label)App.scene.lookup("#playerX");
            playerXLabel.setText(playerX);
            Label playerOLabel = (Label)App.scene.lookup("#playerO");
            playerOLabel.setText(playerO);
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void handleEndMultiModeGame(JSONObject data) {
        System.out.println("in handle end  multi mode game");
        String winner = (String) data.get("winner");
        try {
            String playerCase = (String) data.get("playerCase");
            App.setRoot("End  Game View");
            Label endGameMessage = (Label)App.scene.lookup("#endGameMessage");
            if (winner.equals(Client.player.getUserName())) {
                endGameMessage.setText("Congratulation you won ");
            } else if (winner.equals("draw")) {
                endGameMessage.setText("Draw");
            } else {
                endGameMessage.setText("Unforunately you lost");
            }
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void handleDrawMultiMovesHandler(JSONObject data) {
//        System.out.println("in handle draw multi single mode game");
//        ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
//        ArrayList<Integer> gameMoves = getIntegerArray(objectArray);
//       // .runLater(()-> Client.currentGame.drawMoves(gameMoves));
//    }


    private static void handlePlayerLeftMultiGame(JSONObject data) {
        System.out.println("in handle player left multi mode game");
        String playerName = (String) data.get("playerName");
        // this method will show up a message that say that
        System.out.println("unfortunatly " + playerName + " has left the game");
        // and switch the player to his home page
    }

    private static void handleGoToWelcomeView() {
        // this handler will just go to the welcome view
        System.out.println("from go to welceme");
    }
    /* _____ * _____ end of multi Mode Game Responses _____ * _____ */

    /* _____ * _____ Single Mode Game Responses _____ * _____ */
    private static void handleStartSingleModeGame(JSONObject data) {
        System.out.println("in handle start single mode game");
        String gameID = (String) data.get("gameId");
        String choice = (String) data.get("choice");
        
        Client.singleModeGameID = gameID;
        Client.multiModeGameId  = "";
        Client.opponnentName = "Computer";
        Client.cuurentCase = choice;
        System.out.println("response accepted: , gameId = " + Client.singleModeGameID);
        try {
            App.setRoot("TicTackToe");
            if (choice.equals("X")) {
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerXLabel.setText(Client.player.getUserName());
                playerOLabel.setText(Client.opponnentName);
            } else {
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerXLabel.setText(Client.opponnentName);
                playerOLabel.setText(Client.player.getUserName());
            }
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void handleDrawSingleMoves(JSONObject data) {
        System.out.println("in handle draw single moves");
        ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
        ArrayList<Integer> gameMoves = getIntegerArray(objectArray);
        Platform.runLater(()-> TicTackToeController.drawMoves(gameMoves));
    }

    private static void handleEnableSingleButtons() {
        System.out.println("in handle enable buttons");
        Platform.runLater(()-> TicTackToeController.enableAllButtons());
    }
    private static void handleContinueGame(JSONObject data) {
        try {
            System.out.println("in handle continue Gmae");
            ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
            ArrayList<Integer> gameMoves = getIntegerArray(objectArray);
            App.setRoot("TicTackToe");
            if (Client.cuurentCase.equals("X")) {
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerXLabel.setText(Client.player.getUserName());
                playerOLabel.setText(Client.opponnentName);
            } else {
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerXLabel.setText(Client.opponnentName);
                playerOLabel.setText(Client.player.getUserName());
            }
            Platform.runLater(()-> TicTackToeController.drawMoves(gameMoves));
            Platform.runLater(()-> TicTackToeController.enableAllButtons());
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void handleDisapleAllButtonsSingle() {
        System.out.println("in handle disable all buttons");
        Platform.runLater(()-> TicTackToeController.disapleAllButtons());
    }

    private static void handleEndSingleModeGame(JSONObject data) {
        System.out.println("in handle end single mode game");
        try {
            String playerCase = (String) data.get("playerCase");
            App.setRoot("End  Game View");
            Label endGameMessage = (Label)App.scene.lookup("#endGameMessage");
            if (playerCase.equals("winner")) {
                endGameMessage.setText("Congratulation you won ");
            } else if (playerCase.equals("draw")) {
                endGameMessage.setText("Draw");
            } else {
                endGameMessage.setText("Unforunately you lost");
            }
        } catch (IOException ex) {
            Logger.getLogger(ResponseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* _____ * _____ end of Single Mode Game Responses _____ * _____ */

    /* _____ * _____ start of Chat Room Responses _____ * _____ */

    private static void handleAddNewMessage(JSONObject data) {
        System.out.println("in handle add new message");

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
        System.out.println("in handle open chat room");

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
        System.out.println("in handle player left chat");
        String playerName = (String) data.get("playerName");
        // this method will show up a message that say that
        System.out.println("unfortunatly " + playerName + " has left the chat");
        // and switch the player home page
    }

    private static void handlePlayerInChat(JSONObject data) {
        System.out.println("in handle playerinchat");
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
        System.out.println("in updatePlayerData ");
        Client.player.setBonusPoints(((Long) data.get("bonusPoints")).intValue());
        Client.player.setPlayerRank((String) data.get("playerRank"));
        // this is all what this handler do it only get the new data after the game
        // ended and set it in the player data
        // so when you switch to your home view you will be having the new rank
        // and the new bonus points
    }

    private static void handleUpdateAvilablePlayersList(JSONObject data) {
        
        System.out.println("in handle update avilable players list");
        ArrayList<String> playerNames = (ArrayList<String>) data.get("playersNames");
        String update = (String) data.get("update");
        for (var playerName : playerNames) {
            if (!playerName.equals(Client.player.getUserName())) {
                Opponent player = Opponent.getOpponent(playerName);
                if (player == null)
                    continue;
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
        
        System.out.println("in updateAvilableplayersListFunction");
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