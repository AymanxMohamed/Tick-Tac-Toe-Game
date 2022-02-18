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
public class MultiModeGameModel {
    private final String gameDate;
    private final String playerType;
    private final String opponent;
    private final String playerCase;
    private final String gameRecordJsonString;

    public MultiModeGameModel(Timestamp gameDate, String playerType,String opponent, String winner, String gameRecordJsonString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedGameDate = gameDate.toLocalDateTime().format(formatter);
        this.gameDate = formatedGameDate;
        this.playerType = playerType;
        this.opponent = opponent;
        this.gameRecordJsonString = gameRecordJsonString;
        if (winner.equals(opponent)) {
            playerCase = "loser";
        } else if (winner.equals("draw")) {
            playerCase = "draw";
        } else {
            playerCase = "winner";
        }
    }
    
    public String getGameDate() {
        return gameDate;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getPlayerCase() {
        return playerCase;
    }

    public String getGameRecordJsonString() {
        return gameRecordJsonString;
    }
    
    
    public void printData() {
        System.out.println("############################################");
        System.out.println("gameDate: "  + gameDate);
        System.out.println("playerType: "  + playerType);
        System.out.println("opponent: "  + opponent);
        System.out.println("playerCase: "  + playerCase);
        System.out.println("gameRecordJsonString: "  + gameRecordJsonString);
        System.out.println("############################################");
    }
}
