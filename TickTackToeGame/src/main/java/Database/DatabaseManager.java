/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.Enums.GAME_TYPE;
import Database.Entities.Enums.MappingFunctions;
import Database.Entities.MultiModeGame;
import Database.Entities.Enums.PLAYER_RANK;
import Database.Entities.Player;
import Database.Entities.SingleModeGame;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayman
 */
public class DatabaseManager {
    
    private static Connection sqlServerConnection;
    
    static  {
        try {
            openDataBaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void openDataBaseConnection() throws SQLException {
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
    
    
    /*_____ *_____ Player Database Methods _____ * _____*/
    
    public static void addNewPlayer(String userName, String password) throws SQLException {
         PreparedStatement pst = 
                 sqlServerConnection.prepareStatement("INSERT INTO PLAYER (user_name, password) VALUES(?, ?); ");
         pst.setString(1, userName);
         pst.setString(2, password);
         pst.execute();
    }
    
    public static boolean isValidPlayer(String userName, String password) {
        
        // 1- check if the userName exists 
        // bool checkUserNameExistence(String userName);
        //      if true: 
        //          return password == getPasswordFromDataBase();
        //      else
        //          return false;
        return true;
    }
//    public static Player getPlayer(String userName) {
//        // todo go to the data base and get the record for the corresponding player
//        // then return the player object
//        int bonusPoints = 10; // getBonusPoints() from database
//        
//        PLAYER_RANK playerRank = PLAYER_RANK.BRONZE; //getPlayerRank() from database
//        
//        LocalDateTime registerDate = LocalDateTime.now(); // get dateTime from database
//        
//        //Player(String userName, int bonusPoints, PLAYER_RANK playerRank, LocalDateTime registerDate)
//        //return new Player(userName, bonusPoints, playerRank, registerDate);
//    }
    
    // this function will update the player bonus in the data base and will
    // get the updated player rank from database 
    public static void updatePlayerData(Player player) {
        
        // this function will 
        // first update the player bonus in the database
        
        // update player 
        // set bonus_points = player.getBonusPoints()
        // where user_name = player.getUserName();
        
        // create a function that get the player RankFromDataBase();
        
        // second get the new player_rank fromt the database
        // select player_rank 
        // from player
        // where user_name = player.getUserName();
        
        // then make set the player_rank
        // player.setPlayerRank(playerRank);
    }
    /*_____ *_____ End of Player Database Methods _____ * _____*/
    
      /*_____ *_____ Signle mode game Database Methods _____ * _____*/
    public static void addSingleModeGameRecord(String userName, int numberOfRounds, int playerScore, DIFFICULTY difficulty, FileInputStream gameRecord) throws SQLException {
        PreparedStatement pst = 
                 sqlServerConnection.prepareStatement("INSERT INTO single_mode_game(user_name, no_of_rounds, player_score, difficulty, game_record) VALUES(?, ?, ?, ?, ?)");
         pst.setString(1, userName);
         pst.setInt(2, numberOfRounds);
         pst.setInt(3, playerScore);
         pst.setString(4, MappingFunctions.mapDifficulty(difficulty));
         pst.setBlob(5, gameRecord);
         pst.execute();
    }
    
    public static ArrayList<SingleModeGame> getSingleModeGameRecords() {
        
        ArrayList<SingleModeGame> singleModeGameArray = new ArrayList<>();
        
        // place her the logic of retreving the single mode game records
        // and store it in the arrray
        
        return singleModeGameArray;
    }
    /*_____ *_____ End of Signle mode game Database Methods _____ * _____*/
      
     /*_____ *_____ Multi mode game Database Methods _____ * _____*/
    
    public static void addMultiModeGameRecord(String firstPlayerName, String secondPlayerName, GAME_TYPE gameType, int numberOfRounds, int firstPlayerScore, FileInputStream gameRecord) throws SQLException {
        PreparedStatement pst = 
        sqlServerConnection.prepareStatement("INSERT INTO multi_mode_game(first_player_user_name, second_player_user_name, game_type, no_of_rounds, first_player_score, game_record) VALUES(?, ?, ?, ?, ?, ?)");
         pst.setString(1, firstPlayerName);
         pst.setString(2, secondPlayerName);
         pst.setString(3, MappingFunctions.mapGameType(gameType));
         pst.setInt(4, numberOfRounds);         
         pst.setInt(5, firstPlayerScore);
         pst.setBlob(6, gameRecord);
         pst.execute();
    }
    
    
    public ArrayList<MultiModeGame> getMultiModeGameRecords() {
        
        ArrayList<MultiModeGame> multiModeGameArray = new ArrayList<>();
        
        // place her the logic of retreving the multi mode game records
        // and store it in the arrray
        
        return multiModeGameArray;
    }
    
     /*_____ *_____ End of Multi mode game Database Methods _____ * _____*/
}
