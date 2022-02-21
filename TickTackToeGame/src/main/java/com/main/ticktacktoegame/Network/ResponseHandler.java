/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Controllers.OnlineHomeController;
import com.main.ticktacktoegame.Controllers.TicTackToeController;
import com.main.ticktacktoegame.Models.MultiModeGameModel;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Models.Player;
import com.main.ticktacktoegame.Models.SingleModeGameModel;
import static com.main.ticktacktoegame.Network.RequestCreator.logout;
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
    public static OnlineHomeController onlineController;
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
            case "all players list":
                handleAllPlayersList(data);
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
            case "player is offline":
                handlePlayerIsOffline(data);
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
            case "serverIsClosed":
                handleServerIsClosed();
                break;
            case "single mode game history":
                handelSingleModeGameHistory(data);
                break;
            case "multi mode game history":
                handelMultiModeGameHistory(data);
                break;
            default:
                break;
        }
    }
    /* _____ * _____ Login Responses _____ * _____ */

    private static void handlePlayerNotExist() {
        Platform.runLater(() -> {
            try {
                App.setRoot("PlayerNotExistView");
            } catch (IOException ex) {
                System.out.println("can't set player not exits view");
            }
        });
    }

    private static void handleWrongPassword() {
        Platform.runLater(() -> {
            try {
                App.setRoot("WrongPasswordView");
            } catch (IOException ex) {
                System.out.println("can't set WrongPasswordView");
            }
        });
    }

    private static void handleLoginSuccess(JSONObject playerData) {
        Platform.runLater(() -> {
            try {
                Client.player = new Player(
                        (String) playerData.get("userName"),
                        ((Long) playerData.get("bonusPoints")).intValue(),
                        (String) playerData.get("playerRank"),
                        (String) playerData.get("registerDate"));
                App.setRoot("WelcomeView");
            } catch (IOException ex) {
                System.out.println("can't set welcomeView in handle login");
            }
        });
    }
 
    private static void onlinePlayersList(JSONObject data) {
        JSONArray onlinePlayersDataObjects = (JSONArray) data.get("onlinePlayersDataObjects");
        for (int i = 0; i < onlinePlayersDataObjects.size(); i++) {
            JSONObject obj = (JSONObject) onlinePlayersDataObjects.get(i);
            String playerName = (String) obj.get("name");
            int bonusPoints = ((Long) data.get("bonusPoints")).intValue();
            String playerRank = (String) obj.get("playerRank");
            String playerStatus = (String) obj.get("playerStatus");
            boolean inGame = (boolean) obj.get("inGame");
            boolean inChat = (boolean) obj.get("inChat");
            if (!playerName.equals(Client.player.getUserName())) {
                Opponent.addOpponent(playerName, bonusPoints, playerRank, playerStatus, inGame, inChat);
            }
        }
    }
    
    private static void handleAllPlayersList(JSONObject data) {
        JSONArray allPlayersDataObjects = (JSONArray) data.get("allPlayersDataObjects");
        for (int i = 0; i < allPlayersDataObjects.size(); i++) {
            JSONObject obj = (JSONObject) allPlayersDataObjects.get(i);
            String playerName = (String) obj.get("name");
            int bonusPoints = ((Long) obj.get("bonusPoints")).intValue();
            String playerRank = (String) obj.get("playerRank");
            String playerStatus = (String) obj.get("playerStatus");
            boolean inGame = (boolean) obj.get("inGame");
            boolean inChat = (boolean) obj.get("inChat");
            if (!playerName.equals(Client.player.getUserName())) {
                Opponent.addOpponent(playerName, bonusPoints, playerRank, playerStatus, inGame, inChat);
            }
        }
    }

    private static void handleAddNewPlayer(JSONObject data) {
        String playerName = (String) data.get("userName");
        if (Client.player != null && !playerName.equals(Client.player.getUserName())) {
            String name = (String) data.get("userName");
            int bonusPoints = ((Long) data.get("bonusPoints")).intValue();
            String playerRank = (String) data.get("playerRank");
            Opponent.addOpponent(name, bonusPoints, playerRank, "Offline", false, false);
        }
    }

    private static void handlePlayerAlreadyOnline(JSONObject data) {
        Platform.runLater(() -> {
            try {
                App.setRoot("PlayerAlreadyOnlineView");
            } catch (IOException ex) {
                System.out.println("can't set PlayerAlreadyOnlineView");
            }
        });
    }
    /* _____ * _____ end of Login Responses _____ * _____ */

    /* _____ * _____ Register Responses _____ * _____ */
    private static void handlePlayerExist() {
        Platform.runLater(() -> {
            try {
                App.setRoot("PlayerExistView");
            } catch (IOException ex) {
                System.out.println("can't set PlayerExistView");
            }
        });
    }
    private static void handleRegisterSuccess() {
        Platform.runLater(() -> {
            try {
                App.setRoot("RegisterSuccessView");
            } catch (IOException ex) {
                System.out.println("can't set RegisterSuccessView");
            }
        });
    }
    /* _____ * _____ end of Register Responses _____ * _____ */

    /* _____ * _____ Game invitation for sender Responses _____ * _____ */
    private static void handlePlayerInGame(JSONObject data) {
        Platform.runLater(() -> {
            try {
                String invitedPlayer = (String) data.get("invitedPlayer");
                App.setRoot("PlayerIsCurrentlyInGameView");
                Label receiverName = (Label)App.scene.lookup("#playerName");
                receiverName.setText(invitedPlayer);
            } catch (IOException ex) {
                System.out.println("can't set PlayerIsCurrentlyInGameView");
            }
        });
    }
    private static void handlePlayerIsOffline(JSONObject data) {
        Platform.runLater(() -> {
            try {
                String invitedPlayer = (String) data.get("invitedPlayer");
                App.setRoot("PlayerIsOfflineView");
                Label receiverName = (Label)App.scene.lookup("#playerName");
                receiverName.setText(invitedPlayer);
            } catch (IOException ex) {
                System.out.println("can't set PlayerIsOfflineView");
            }
        });
    }

    private static void handleInvitationSended(JSONObject data) {
//        String invitationReciever = (String) data.get("invitationReciever");
    }

    private static void handleInvitationRejected(JSONObject data) {
        Platform.runLater(() -> {
            try {
                String invitationReciever = (String) data.get("invitationReciever");
                App.setRoot("InvitationRejectedView");
                Label receiverName = (Label)App.scene.lookup("#playerName");
                receiverName.setText(invitationReciever);
            } catch (IOException ex) {
                 System.out.println("can't set InvitationRejectedView");
            }
        });
    }

    private static void handleChooseXOrO(JSONObject data) {
        Platform.runLater(() -> {
            try {
                String invitationReciever = (String) data.get("invitationReciever");
                Client.opponnentName = invitationReciever;
                App.setRoot("chooseXorOMulti");
            } catch (IOException ex) {
                System.out.println("can't set chooseXorOMulti");
            }
        });
    }

    /* _____ * _____ Game invitation for receiver Responses _____ * _____ */

    private static void handleGameInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        Client.opponnentName = invitationSender;
        Platform.runLater(() -> {
            try {
                App.setRoot("InvitationFromPlayer");
                Label senderName = (Label)App.scene.lookup("#playerName");
                senderName.setText(invitationSender);
            } catch (IOException ex) {
                System.out.println("can't set InvitationFromPlayer");
            }
        });
    }

    /* _____ * _____ Multi Mode Game Responses _____ * _____ */

    private static void handleStartMultiModeGame(JSONObject data) {
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
        Platform.runLater(() -> {
            try {
                App.setRoot("TicTackToe");
                Label playerXLabel = (Label)App.scene.lookup("#playerX");
                playerXLabel.setText(playerX);
                Label playerOLabel = (Label)App.scene.lookup("#playerO");
                playerOLabel.setText(playerO);
            } catch (IOException ex) {
                 System.out.println("can't set TicTackToe");
            }
        });
       
    }

    private static void handleEndMultiModeGame(JSONObject data) {
        String winner = (String) data.get("winner");
        Platform.runLater(() -> {
            try {
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
                System.out.println("can't set End  Game View");
            }
        });
    }

    private static void handlePlayerLeftMultiGame(JSONObject data) {
        Platform.runLater(() -> {
            try {
                Client.opponnentName = "";
                String playerName = (String) data.get("playerName");
                App.setRoot("PlayerLeftGameView");
                Label playerNameLabel = (Label)App.scene.lookup("#playerName");
                playerNameLabel.setText(playerName);
            } catch (IOException ex) {
                System.out.println("can't set PlayerLeftGameView");
            }
        });
    }

    /* _____ * _____ end of multi Mode Game Responses _____ * _____ */

    /* _____ * _____ Single Mode Game Responses _____ * _____ */
    private static void handleStartSingleModeGame(JSONObject data) {
        String gameID = (String) data.get("gameId");
        String choice = (String) data.get("choice");
        Client.singleModeGameID = gameID;
        Client.multiModeGameId  = "";
        Client.opponnentName = "Computer";
        Client.cuurentCase = choice;
        Platform.runLater(()->{
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
                System.out.println("can't set TicTackToe");
            }
        });
    }

    private static void handleDrawSingleMoves(JSONObject data) {
        ArrayList<Object> objectArray = (ArrayList<Object>) data.get("gameMoves");
        ArrayList<Integer> gameMoves = getIntegerArray(objectArray);
        Platform.runLater(()-> TicTackToeController.drawMoves(gameMoves));
    }

    private static void handleEnableSingleButtons() {
        Platform.runLater(()-> TicTackToeController.enableAllButtons());
    }
    private static void handleContinueGame(JSONObject data) {
        Platform.runLater(() -> {
            try {
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
                System.out.println("can't set TicTackToe");
            }
        });
    }
    private static void handleDisapleAllButtonsSingle() {
        Platform.runLater(()-> TicTackToeController.disapleAllButtons());
    }

    private static void handleEndSingleModeGame(JSONObject data) {
        Platform.runLater(() -> {
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
                 System.out.println("can't set End  Game View");
            }
        });
    }
    /* _____ * _____ end of Single Mode Game Responses _____ * _____ */

    /* _____ * _____ start of Chat Room Responses _____ * _____ */

    
    
    private static void handleChatInvitation(JSONObject data) {
        String invitationSender = (String) data.get("invitationSender");
        Client.opponnentName = invitationSender;
        Platform.runLater(() -> {
             try {
                App.setRoot("InvitationFromPlayerToChat");
                Label senderName = (Label)App.scene.lookup("#playerName");
                senderName.setText(invitationSender);
            } catch (IOException ex) {
                System.out.println("can't set InvitationFromPlayerToChat");
            }
        });
       
    }

    private static void handleAddNewMessage(JSONObject data) {
        String message = (String) data.get("message");
        String sender = (String) data.get("sender");
        String messageToAdd;
        if (sender.equals(Client.player.getUserName())) {
            messageToAdd = "me: " + message.trim();
        } else {
            messageToAdd = sender + ": " + message.trim();
        }
        Platform.runLater(()-> Client.chatRoom.addMessageToTextArea(messageToAdd));
    }

    private static void handleOpenChatRoom(JSONObject data) {
        String chatID = (String) data.get("chatID");
        String sender = (String) data.get("sender");
        String receiver = (String) data.get("receiver");
        Client.opponnentName = Client.player.getUserName().equals(sender) ? receiver : sender;
        Client.chatRoomId = chatID;
        Client.multiModeGameId = "";
        Client.singleModeGameID = "";
        
        Platform.runLater(() -> {
            try {
                App.setRoot("chat");
                Label playerOneLabel = (Label)App.scene.lookup("#playerOneLabel");
                playerOneLabel.setText(Client.player.getUserName());
                Label PlayerTwoLabel = (Label)App.scene.lookup("#PlayerTwoLabel");
                PlayerTwoLabel.setText(Client.opponnentName);
            }catch (IOException ex) {
                System.out.println("can't set chat");
            }
        });
            
    }

    private static void handlePlayerLeftChatRoom(JSONObject data) {
        String playerName = (String) data.get("playerName");
        Platform.runLater(() -> {
            try {
                Client.opponnentName = "";
                App.setRoot("PlayerLeftChatView");
                Label playerNameLabel = (Label)App.scene.lookup("#playerName");
                playerNameLabel.setText(playerName);
            } catch (IOException ex) {
                System.out.println("can't set PlayerLeftChatView");
            }
        });
    }

    private static void handlePlayerInChat(JSONObject data) {
        String invitedPlayer = (String) data.get("invitedPlayer");
        Platform.runLater(() -> {
            try {
                App.setRoot("PlayerIsCurrentlyInChatView");
                Label playerName = (Label)App.scene.lookup("#playerName");
                playerName.setText(invitedPlayer);
            } catch (IOException ex) {
                System.out.println("can't set PlayerIsCurrentlyInChatView");
            }
        });
    }

    /* _____ * _____ end of Chat Room Responses _____ * _____ */
    
    /* _____ * _____ general Responses _____ * _____ */
    
    
    private static void handleUpdatePlayerData(JSONObject data) {
        String playerName = (String) data.get("playerName");
        String playerRank = (String) data.get("playerRank");
        int bonusPoints = ((Long) data.get("bonusPoints")).intValue();
        if (Client.player != null && Client.player.getUserName().equals(playerName)) {
            Client.player.setPlayerRank(playerRank);
            Client.player.setBonusPoints(bonusPoints);
        } else {
            Opponent player = Opponent.getOpponent(playerName);
            if (player != null) {
                player.setBonusPoints(bonusPoints);
                player.setPlayerRank(playerRank);
            }
        }
    }
    private static void handleUpdateAvilablePlayersList(JSONObject data) {
        ArrayList<String> playerNames = (ArrayList<String>) data.get("playersNames");
        String update = (String) data.get("update");
        for (var playerName : playerNames) {
            if (Client.player != null && !playerName.equals(Client.player.getUserName())) {
                Opponent player = Opponent.getOpponent(playerName);
                
                if (player == null)
                    continue;
                switch(update) {
                    case "inGame":
                        player.togleInGameStatus();
                        break;
                    case "playerStatus":
                        player.tooglePlayerStatus();
                        break;
                    case "inChat":
                        player.toogleInChatStatus();
                        break;
                }
            }
        }
    }

    private static void handlePlayerLeftTheGame(JSONObject data) {
    }
    private static void handleServerIsClosed() {
        if (Client.player != null) {
            Client.sendRequest(logout());
        }
        Client.closeEveryThing();
    }
    /* _____ * _____ end of general Responses _____ * _____ */

     /* _____ * _____ Game History  _____ * _____ */
    private static void handelSingleModeGameHistory(JSONObject data) {
        JSONArray gameHistoryObjects = (JSONArray) data.get("gameHistoryObjects");
        SingleModeGameModel.singleModeHistory = new ArrayList<>();
        for (int i = 0; i < gameHistoryObjects.size(); i++) {
            JSONObject obj = (JSONObject) gameHistoryObjects.get(i);
            String gameDate = (String) obj.get("gameDate");
            String playerType = (String) obj.get("playerType");
            String difficulty = (String) obj.get("difficulty");
            String playerCase = (String) obj.get("playerCase");
            String gameMoves  = (String) obj.get("gameMoves");
            SingleModeGameModel.addSingleModeGame(gameDate, playerType, difficulty, playerCase, gameMoves);
        }
    }

    private static void handelMultiModeGameHistory(JSONObject data) {
        JSONArray gameHistoryObjects = (JSONArray) data.get("gameHistoryObjects");
        MultiModeGameModel.multiModeHistory = new ArrayList<>();
        for (int i = 0; i < gameHistoryObjects.size(); i++) {
            JSONObject obj = (JSONObject) gameHistoryObjects.get(i);
            String gameDate = (String) obj.get("gameDate");
            String playerType = (String) obj.get("playerType");
            String opponent = (String) obj.get("opponent");
            String playerCase = (String) obj.get("playerCase");
            String gameMoves  = (String) obj.get("gameMoves");
            MultiModeGameModel.addMultiModeGame(gameDate, playerType, opponent, playerCase, gameMoves);
        }
    }
    /* _____ * _____ End of Game History  _____ * _____ */


}