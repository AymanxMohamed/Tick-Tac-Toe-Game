/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.Enums.GAME_TYPE;
import Database.Entities.Enums.MappingFunctions;
import Database.Entities.Enums.PLAYER_RANK;
import Database.Entities.MultiModeGame;
import Database.Entities.Player;
import Database.Entities.SingleModeGame;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Security.Password.ecryptPassword;

/**
 *
 * @author ayman
 */
public class DatabaseManager {

    private static Connection sqlServerConnection;

    public static void openDataBaseConnection() throws SQLException {
        String uname = "ayman";
        String password = "@01208538504@";
        String sqlServerUrl = "jdbc:sqlserver://localhost:1433;databaseName=TicTacToe;encrypt=true;trustServerCertificate=true;";
        sqlServerConnection = DriverManager.getConnection(sqlServerUrl, uname, password);
    }

    public static void closeDataBaseConnection() {
        try {
            sqlServerConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* _____ *_____ Player Database Methods _____ * _____ */

    public static void addNewPlayer(String userName, String password) throws SQLException, NullPointerException {
        PreparedStatement pst = sqlServerConnection
                .prepareStatement("INSERT INTO PLAYER (user_name, password) VALUES(?, ?); ");
        pst.setString(1, userName);
        pst.setString(2, ecryptPassword(password));
        pst.execute();
    }

    public static boolean isPlayerExist(String userName) throws SQLException {
        PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM PLAYER WHERE user_name = ?");
        pst.setString(1, userName);
        ResultSet resultSet = pst.executeQuery();
        return resultSet.next();
    }
    
    private static boolean isValidPassword(String userName, String password) throws SQLException {
        return ecryptPassword(password).equals(getPlayerPassword(userName));
    }
    
    public static boolean isValidPlayer(String userName, String password) throws SQLException {
        return isPlayerExist(userName) && isValidPassword(userName, password);
    }

    private static String getPlayerPassword(String userName) throws SQLException {
        PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT password FROM PLAYER WHERE user_name = ?");
        pst.setString(1, userName);
        ResultSet resultSet = pst.executeQuery();
        resultSet.next();
        return resultSet.getString("password");
    }

    public static Player getPlayer(String userName) throws SQLException {
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
    }


    public static void updatePlayerData(Player player) throws SQLException {
        updatePlayerBonusPoints(player);
        player.setPlayerRank(getPlayerRank(player));
    }
    private static PLAYER_RANK getPlayerRank(Player player) throws SQLException {
        PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT player_rank FROM PLAYER WHERE user_name = ?");
        pst.setString(1, player.getUserName());
        ResultSet resultSet = pst.executeQuery();
        resultSet.next();
        return MappingFunctions.mapPlayerRank(resultSet.getString("player_rank"));        
    } 
    private  static void updatePlayerBonusPoints(Player player) throws SQLException {
        PreparedStatement pst = sqlServerConnection.prepareStatement("UPDATE PLAYER SET bonus_points = ? WHERE user_name = ?");
        pst.setInt(1, player.getBonusPoints());
        pst.setString(2, player.getUserName());
        pst.execute();
    }
    /* _____ *_____ End of Player Database Methods _____ * _____ */

    /* _____ *_____ Signle mode game Database Methods _____ * _____ */
    
    /**
     * @author ayman
     * @description a method that used to add a single mode game record to the
     * database
     * @param userName
     * @param numberOfRounds
     * @param playerScore
     * @param difficulty
     * @param gameRecord
     * @throws SQLException 
     */
    public static void addSingleModeGameRecord(String userName, int numberOfRounds, int playerScore,
            DIFFICULTY difficulty, String gameRecord) throws SQLException {
        PreparedStatement pst = sqlServerConnection.prepareStatement(
                "INSERT INTO single_mode_game(user_name, no_of_rounds, player_score, difficulty, game_record) VALUES(?, ?, ?, ?, ?)");
        pst.setString(1, userName);
        pst.setInt(2, numberOfRounds);
        pst.setInt(3, playerScore);
        pst.setString(4, MappingFunctions.mapDifficulty(difficulty));
        pst.setString(5, gameRecord);
        pst.execute();
    }

    public static ArrayList<SingleModeGame> getSingleModeGameRecords() throws SQLException {

        ArrayList<SingleModeGame> singleModeGameArray = new ArrayList<>();
        
        PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM single_mode_game");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            singleModeGameArray.add(new SingleModeGame(
                    resultSet.getInt("game_id"), 
                    resultSet.getString("user_name"), 
                    resultSet.getInt("no_of_rounds"), 
                    resultSet.getInt("player_score"), 
                    resultSet.getString("difficulty"), 
                    resultSet.getString("player_case"), 
                    resultSet.getString("game_record"), 
                    resultSet.getTimestamp("game_date"))
            );
        }
        return singleModeGameArray;
    }

    /**
     * @description an overloaded method that give you the single mode records for a certain player
     * @param playerName
     * @return
     * @throws SQLException 
     */
    public static ArrayList<SingleModeGame> getSingleModeGameRecords(String playerName) throws SQLException {

        ArrayList<SingleModeGame> singleModeGameArray = new ArrayList<>();
        
        PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM single_mode_game WHERE  user_name = ?");
        pst.setString(1, playerName);
        ResultSet resultSet = pst.executeQuery();
        
        while (resultSet.next()) {
            singleModeGameArray.add(new SingleModeGame(
                    resultSet.getInt("game_id"), 
                    resultSet.getString("user_name"), 
                    resultSet.getInt("no_of_rounds"), 
                    resultSet.getInt("player_score"), 
                    resultSet.getString("difficulty"), 
                    resultSet.getString("player_case"), 
                    resultSet.getString("game_record"), 
                    resultSet.getTimestamp("game_date"))
            );
        }
        return singleModeGameArray;
    }
    /* _____ *_____ End of Signle mode game Database Methods _____ * _____ */

    /* _____ *_____ Multi mode game Database Methods _____ * _____ */

    public static void addMultiModeGameRecord(int gameID, String firstPlayerName, String secondPlayerName,
            GAME_TYPE gameType, int numberOfRounds, int firstPlayerScore, String gameRecord) throws SQLException {
        PreparedStatement pst = sqlServerConnection.prepareStatement(
                "INSERT INTO multi_mode_game(game_id, first_player_user_name, second_player_user_name, game_type, no_of_rounds, first_player_score, game_record) VALUES(?, ?, ?, ?, ?, ?, ?)");
        pst.setInt(1, gameID);
        pst.setString(2, firstPlayerName);
        pst.setString(3, secondPlayerName);
        pst.setString(4, MappingFunctions.mapGameType(gameType));
        pst.setInt(5, numberOfRounds);
        pst.setInt(6, firstPlayerScore);
        pst.setString(7, gameRecord);
        pst.execute();
    }
    /**
     * @author ayman
     * @description an overloaded method that give you the single mode records for a certain player
     * @return
     * @throws SQLException 
     */
    public static ArrayList<MultiModeGame> getMultiModeGameRecords() throws SQLException {

        ArrayList<MultiModeGame> multiModeGameArray = new ArrayList<>();

        PreparedStatement pst = sqlServerConnection.prepareStatement("SELECT * FROM multi_mode_game");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            multiModeGameArray.add(new MultiModeGame(
                    resultSet.getInt("game_id"), 
                    resultSet.getString("first_player_user_name"),
                    resultSet.getString("second_player_user_name"),
                    resultSet.getString("game_type"),
                    resultSet.getInt("no_of_rounds"), 
                    resultSet.getInt("first_player_score"), 
                    resultSet.getInt("second_player_score"),
                    resultSet.getString("winner"),
                    resultSet.getString("game_record"), 
                    resultSet.getTimestamp("game_date"))
            );
        }
        return multiModeGameArray;
    }
    /**
     * @author ayman
     * @description an overloaded method that give you the single mode records for a certain player
     * @param playerName
     * @return
     * @throws SQLException 
     */
    public static ArrayList<MultiModeGame> getMultiModeGameRecords(String playerName) throws SQLException {

        ArrayList<MultiModeGame> multiModeGameArray = new ArrayList<>();
        
        PreparedStatement pst = sqlServerConnection.prepareStatement(
                        "SELECT * FROM multi_mode_game WHERE first_player_user_name = ? OR second_player_user_name = ?"
                );
        pst.setString(1, playerName);        
        pst.setString(2, playerName);
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            multiModeGameArray.add(new MultiModeGame(
                    resultSet.getInt("game_id"), 
                    resultSet.getString("first_player_user_name"),
                    resultSet.getString("second_player_user_name"),
                    resultSet.getString("game_type"),
                    resultSet.getInt("no_of_rounds"), 
                    resultSet.getInt("first_player_score"), 
                    resultSet.getInt("second_player_score"),
                    resultSet.getString("winner"),
                    resultSet.getString("game_record"), 
                    resultSet.getTimestamp("game_date"))
            );
        }
        return multiModeGameArray;
    }

    /* _____ *_____ End of Multi mode game Database Methods _____ * _____ */

}
