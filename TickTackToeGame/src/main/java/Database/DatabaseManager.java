/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Database.Entities.MultiModeGame;
import Database.Entities.PLAYER_RANK;
import Database.Entities.Player;
import Database.Entities.SingleModeGame;
import java.time.LocalDateTime;

/**
 *
 * @author ayman
 */
public class DatabaseManager {
    
    public void openDataBaseConnection() {
        
    }
    public void closeDataBaseConnection() {
        
    }
    public void addNewPlayer(String userName, String password) {
        
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
    
    
    public void getSingleGameModeData(SingleModeGame aThis) {
        
    }
    
    public void getMultiModeGameData(MultiModeGame aThis) {
            
    }
    
}
