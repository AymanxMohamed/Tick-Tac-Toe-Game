/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ayman
 */
public class SingleModeGameModel {
    private final String gameDate;
    private final String playerType;
    private final String difficulty;
    private final String playerCase;
    private final String gameMovesJsonString;

    public SingleModeGameModel(Timestamp gameDate, String playerType, String difficulty, String player_case, String gameMovesJsonString) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedGameDate = gameDate.toLocalDateTime().format(formatter);
        this.gameDate = formatedGameDate;
        this.playerType = playerType;
        this.difficulty = difficulty;
        this.playerCase = player_case;
        this.gameMovesJsonString = gameMovesJsonString;
    }

    /*
        SELECT game_date, player_type, difficulty, player_case, game_record
        FROM single_mode_game
        WHERE user_name = ?;
    */

    public String getGameDate() {
        return gameDate;
    }
    public String getPlayerType() {
        return playerType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getPlayerCase() {
        return playerCase;
    }

    public String getGameMovesJsonString() {
        return gameMovesJsonString;
    }
    
    public void printData() {
        System.out.println("############################################");
        System.out.println("gameDate: "  + gameDate);
        System.out.println("playerType: "  + playerType);
        System.out.println("difficulty: "  + difficulty);
        System.out.println("playerCase: "  + playerCase);
        System.out.println("gameMovesJsonString: "  + gameMovesJsonString);
        System.out.println("############################################");
    }
}
