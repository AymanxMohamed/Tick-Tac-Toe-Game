/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.MultiModeGame;
import Database.Entities.Enums.PLAYER_RANK;
import Database.Entities.Player;
import Database.Entities.SingleModeGame;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author ayman
 */
public class DatabaseManager {
    
    public void openDataBaseConnection() {
        
    }
    
    public void closeDataBaseConnection() {
        
    }
    
    
    /*_____ *_____ Player Database Methods _____ * _____*/
    
    public void addNewPlayer(String userName, String password) {
        // this function will be just one query that add record to player table
        // in the database 
        // insert into player(user_name, password) values (userName, password);
    }
    
    public boolean isValidPlayer(String userName, String password) {
        
        // 1- check if the userName exists 
        // bool checkUserNameExistence(String userName);
        //      if true: 
        //          return password == getPasswordFromDataBase();
        //      else
        //          return false;
        return true;
    }
    public Player getPlayer(String userName) {
        // todo go to the data base and get the record for the corresponding player
        // then return the player object
        int bonusPoints = 10; // getBonusPoints() from database
        
        PLAYER_RANK playerRank = PLAYER_RANK.BRONZE; //getPlayerRank() from database
        
        LocalDateTime registerDate = LocalDateTime.now(); // get dateTime from database
        
        //Player(String userName, int bonusPoints, PLAYER_RANK playerRank, LocalDateTime registerDate)
        return new Player(userName, bonusPoints, playerRank, registerDate);
    }
    
    // this function will update the player bonus in the data base and will
    // get the updated player rank from database 
    public void updatePlayerData(Player player) {
        
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
    public void addSingleModeGameRecord(String userName, int numberOfRounds, int playerScore, String difficulty, File gameRecord) {
        // insert into single_mode_game(user_name, no_of_rounds, player_score, difficulty, game_Record)
        // values(userName, numberOfRounds, playerScore, difficulty, gameRecord)
        
    }
    
    public ArrayList<SingleModeGame> getSingleModeGameRecords() {
        
        ArrayList<SingleModeGame> singleModeGameArray = new ArrayList<>();
        
        // place her the logic of retreving the single mode game records
        // and store it in the arrray
        
        return singleModeGameArray;
    }
    /*_____ *_____ End of Signle mode game Database Methods _____ * _____*/
      
     /*_____ *_____ Multi mode game Database Methods _____ * _____*/
    
    public void addMultiModeGameRecord(String firstPlayerName, String secondPlayerName, String gameType, int numberOfRecords, int firstPlayerScore, File gameRecord) {
        // insert into multi_mode_game(first_player_name, second_player_name, game_type, no_of_Records, first_player_score, game_record) values (firstPlayerName, secondPlayerName, gameType, numberOfRecords, firstPlayerScore, gameRecord);
    }
    
    
    public ArrayList<MultiModeGame> getMultiModeGameRecords() {
        
        ArrayList<MultiModeGame> multiModeGameArray = new ArrayList<>();
        
        // place her the logic of retreving the multi mode game records
        // and store it in the arrray
        
        return multiModeGameArray;
    }
    
     /*_____ *_____ End of Multi mode game Database Methods _____ * _____*/
}
