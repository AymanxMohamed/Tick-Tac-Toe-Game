/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Database;

import tictactoegameserver.Database.Entities.Enums.DIFFICULTY;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import tictactoegameserver.Database.Entities.Enums.PLAYER_RANK;
import tictactoegameserver.Database.Entities.MultiModeGame;
import tictactoegameserver.Database.Entities.Player;
import tictactoegameserver.Database.Entities.SingleModeGame;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tictactoegameserver.Security.Password.ecryptPassword;
import tictactoegameserver.models.MultiModeGameModel;
import tictactoegameserver.models.SingleModeGameModel;

/**
 *
 * @author ayman
 */
public class DatabaseManager {

    private static Connection sqlServerConnection;

    public static void openDataBaseConnection() {
        try {
            String uname = "ayman";
            String password = "@01208538504@";
            String sqlServerUrl = "jdbc:sqlserver://localhost:1433;databaseName=TicTacToe;encrypt=true;trustServerCertificate=true;";
            sqlServerConnection = DriverManager.getConnection(sqlServerUrl, uname, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeDataBaseConnection() {
        try {
            sqlServerConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* _____ *_____ Player Database Methods _____ * _____ */

    public static void addNewPlayer(String userName, String password)  {
        try {
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("INSERT INTO PLAYER (user_name, password) VALUES(?, ?); ");
            pst.setString(1, userName);
            pst.setString(2, ecryptPassword(password));
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isPlayerExist(String userName) {
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM PLAYER WHERE user_name = ?");
            pst.setString(1, userName);
            ResultSet resultSet = pst.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static boolean isValidPassword(String userName, String password) {
        return ecryptPassword(password).equals(getPlayerPassword(userName));
    }

    public static boolean isValidPlayer(String userName, String password){
        return isPlayerExist(userName) && isValidPassword(userName, password);
    }

    private static String getPlayerPassword(String userName) {
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT password FROM PLAYER WHERE user_name = ?");
            pst.setString(1, userName);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            return resultSet.getString("password");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Player getPlayer(String userName) {
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM PLAYER WHERE user_name = ?");
            pst.setString(1, userName);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            return new Player(
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getInt("bonus_points"),
                    resultSet.getString("player_rank"),
                    resultSet.getTimestamp("register_date"),
                    resultSet.getString("player_status"));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static ArrayList<Player> getAllPlayers() {
        
        ArrayList<Player> playersArray = new ArrayList<>();
        try {
            
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM player");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                playersArray.add(new Player(
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getInt("bonus_points"),
                        resultSet.getString("player_rank"),
                        resultSet.getTimestamp("register_date"),
                        resultSet.getString("player_status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playersArray;

    }
    public static ArrayList<Player> getOnlinePlayers() {
        
        ArrayList<Player> onlinePlayers = new ArrayList<>();
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM player WHERE player_status = 'Online'");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                onlinePlayers.add(new Player(
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getInt("bonus_points"),
                        resultSet.getString("player_rank"),
                        resultSet.getTimestamp("register_date"),
                        resultSet.getString("player_status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return onlinePlayers;
    }


    public static void updatePlayerData(Player player) {
        updatePlayerBonusPoints(player);
        player.setPlayerRank(getPlayerRank(player));
    }

    private static PLAYER_RANK getPlayerRank(Player player) {
        try {
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("SELECT player_rank FROM PLAYER WHERE user_name = ?");
            pst.setString(1, player.getUserName());
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            return MappingFunctions.mapPlayerRank(resultSet.getString("player_rank"));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void updatePlayerBonusPoints(Player player) {
        try {
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("UPDATE PLAYER SET bonus_points = ? WHERE user_name = ?");
            pst.setInt(1, player.getBonusPoints());
            pst.setString(2, player.getUserName());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String getPlayerStatus(String playerName) {
         try {
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT player_status FROM PLAYER WHERE user_name = ?");
            pst.setString(1, playerName);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            return resultSet.getString("player_status");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void togglePlayerStatus(Player player) {
        try {
            String playerStatus = player.getPlayerStatus().equals("Offline") ? "Online" : "Offline";
            player.setPlayerStatus(playerStatus);
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("UPDATE PLAYER SET player_status = ? WHERE user_name = ?");
            pst.setString(1, playerStatus);
            pst.setString(2, player.getUserName());
            pst.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* _____ *_____ End of Player Database Methods _____ * _____ */

    /* _____ *_____ Signle mode game Database Methods _____ * _____ */

    /**
     * @author ayman
     * @description a method that used to add a single mode game record to the
     *              database
     * @param gameID
     * @param userName
     * @param playerType
     * @param difficulty
     * @param playerCase
     * @param gameRecord
     */
    public static void addSingleModeGameRecord(String gameID, String userName, String playerType,
            DIFFICULTY difficulty, String playerCase, String gameRecord) {
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement(
                    "INSERT INTO single_mode_game(game_id, user_name, player_type, difficulty, player_case, game_record) VALUES(?, ?, ?, ?, ?, ?)");
            pst.setString(1, gameID);
            pst.setString(2, userName);
            pst.setString(3, playerType);
            pst.setString(4, MappingFunctions.mapDifficulty(difficulty));
            pst.setString(5, playerCase);
            pst.setString(6, gameRecord);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<SingleModeGame> getSingleModeGameRecords() {

        ArrayList<SingleModeGame> singleModeGameArray = new ArrayList<>();
        try {
            
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM single_mode_game");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                singleModeGameArray.add(new SingleModeGame(
                        resultSet.getString("game_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("player_type"),
                        resultSet.getString("difficulty"),
                        resultSet.getString("player_case"),
                        resultSet.getString("game_record"),
                        resultSet.getTimestamp("game_date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return singleModeGameArray;
    }

    /**
     * @description an overloaded method that give you the single mode records for a
     *              certain player
     * @param playerName
     * @return
     */
    public static ArrayList<SingleModeGame> getSingleModeGameRecords(String playerName) {

            ArrayList<SingleModeGame> singleModeGameArray = new ArrayList<>();
        try {
            
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("SELECT * FROM single_mode_game WHERE  user_name = ?");
            pst.setString(1, playerName);
            ResultSet resultSet = pst.executeQuery();
            
            while (resultSet.next()) {
                singleModeGameArray.add(new SingleModeGame(
                        resultSet.getString("game_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("player_type"),
                        resultSet.getString("difficulty"),
                        resultSet.getString("player_case"),
                        resultSet.getString("game_record"),
                        resultSet.getTimestamp("game_date")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return singleModeGameArray;
    }
    
    public static ArrayList<SingleModeGameModel> getSingleModeGameHistory(String playerName) {
        ArrayList<SingleModeGameModel> singleModeGameArray = new ArrayList<>();
        try {
            
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("SELECT game_date, player_type, difficulty, player_case, game_record FROM single_mode_game WHERE user_name = ?");
            pst.setString(1, playerName);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                singleModeGameArray.add(new SingleModeGameModel(
                        resultSet.getTimestamp("game_date"),
                        resultSet.getString("player_type"),
                        resultSet.getString("difficulty"),
                        resultSet.getString("player_case"),
                        resultSet.getString("game_record")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return singleModeGameArray;
    }
    /* _____ *_____ End of Signle mode game Database Methods _____ * _____ */

    /* _____ *_____ Multi mode game Database Methods _____ * _____ */

    public static void addMultiModeGameRecord(String gameID, String playerXUserName, String playerOUserName,
            String winner, String gameRecord){
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement(
                    "INSERT INTO multi_mode_game(game_id, player_x_user_name, player_o_user_name, winner, game_record) VALUES(?, ?, ?, ?, ?)");
            pst.setString(1, gameID);
            pst.setString(2, playerXUserName);
            pst.setString(3, playerOUserName);
            pst.setString(4, winner);
            pst.setString(5, gameRecord);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @author ayman
     * @description an overloaded method that give you the single mode records for a
     *              certain player
     * @return ArrayList<MultiModeGame>
     */
    public static ArrayList<MultiModeGame> getMultiModeGameRecords() {
       
        ArrayList<MultiModeGame> multiModeGameArray = new ArrayList<>();
        try {
            
            PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM multi_mode_game");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                multiModeGameArray.add(new MultiModeGame(
                        resultSet.getString("game_id"),
                        resultSet.getString("player_x_user_name"),
                        resultSet.getString("player_o_user_name"),
                        resultSet.getString("winner"),
                        resultSet.getString("game_record"),
                        resultSet.getTimestamp("game_date")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return multiModeGameArray;
    }

    /**
     * @author ayman
     * @description an overloaded method that give you the single mode records for a
     *              certain player
     * @param playerName
     * @return
     */
    public static ArrayList<MultiModeGame> getMultiModeGameRecords(String playerName) {
        
        ArrayList<MultiModeGame> multiModeGameArray = new ArrayList<>();
        try {
            PreparedStatement pst = sqlServerConnection.prepareStatement(
                    "SELECT * FROM multi_mode_game WHERE first_player_user_name = ? OR second_player_user_name = ?");
            pst.setString(1, playerName);
            pst.setString(2, playerName);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                multiModeGameArray.add(new MultiModeGame(
                        resultSet.getString("game_id"),
                        resultSet.getString("player_x_user_name"),
                        resultSet.getString("player_o_user_name"),
                        resultSet.getString("winner"),
                        resultSet.getString("game_record"),
                        resultSet.getTimestamp("game_date")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return multiModeGameArray;
    }
    
      public static ArrayList<MultiModeGameModel> getMultiModeGameHistory(String playerName) {
        ArrayList<MultiModeGameModel> multiModeGameArray = new ArrayList<>();
        try {
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("select game_date, player_o_user_name, winner, game_record from multi_mode_game where player_x_user_name = ?");
            pst.setString(1, playerName);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                multiModeGameArray.add(new MultiModeGameModel(
                        resultSet.getTimestamp("game_date"), "X",
                        resultSet.getString("player_o_user_name"),
                        resultSet.getString("winner"),
                        resultSet.getString("game_record")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement pst = sqlServerConnection
                    .prepareStatement("select game_date, player_x_user_name, winner, game_record from multi_mode_game where player_o_user_name = ?");
            pst.setString(1, playerName);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                multiModeGameArray.add(new MultiModeGameModel(
                        resultSet.getTimestamp("game_date"), "O",
                        resultSet.getString("player_x_user_name"),
                        resultSet.getString("winner"),
                        resultSet.getString("game_record")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return multiModeGameArray;
    }
//    public static ArrayList<MultiModeGameModel> getOMultiModeGameHistory(String playerName) {
//        ArrayList<MultiModeGameModel> multiModeGameArray = new ArrayList<>();
//        try {
//            PreparedStatement pst = sqlServerConnection
//                    .prepareStatement("select game_date, player_x_user_name, winner, game_record from multi_mode_game where player_x_user_name = ?");
//            pst.setString(1, playerName);
//            ResultSet resultSet = pst.executeQuery();
//            while (resultSet.next()) {
//                multiModeGameArray.add(new MultiModeGameModel(
//                        resultSet.getTimestamp("game_date"), "O",
//                        resultSet.getString("player_x_user_name"),
//                        resultSet.getString("winner"),
//                        resultSet.getString("game_record")));
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return multiModeGameArray;
//    }
    /* _____ *_____ End of Multi mode game Database Methods _____ * _____ */
}
