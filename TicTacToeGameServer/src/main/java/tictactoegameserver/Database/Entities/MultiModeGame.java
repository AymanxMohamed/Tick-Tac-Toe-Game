/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Database.Entities;

import tictactoegameserver.Database.Entities.Enums.GAME_TYPE;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ayman
 */
public class MultiModeGame {
    private final int gameID;
    private final String playerXUserName;
    private final String playerYUserName;
    private final String winner;
    private final String gameRecord;
    private final LocalDateTime gameDate;
    
    public MultiModeGame(int gameID, String playerXUserName, String playerYUserName, String winner, String gameRecord, Timestamp gameDate) {
        this.gameID = gameID;
        this.playerXUserName = playerXUserName;
        this.playerYUserName = playerYUserName;
        this.winner = winner;
        this.gameRecord = gameRecord;
        this.gameDate = gameDate.toLocalDateTime();
    }
    public int getGameNumber() { return gameID; }
    public String getPlayerXUserName() { return playerXUserName; }
    public String getPlayerYUserName() { return playerYUserName; }
    public String getWinner() { return winner; }
    public String getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }
    
    public void printData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedDateTime = gameDate.format(formatter);
        System.out.println("############################################");
        System.out.println("gameId: " + gameID);
        System.out.println("playerXUserName: "  + playerXUserName);
        System.out.println("playerYUserName: "  + playerYUserName);
        System.out.println("winner: "  + winner);
        System.out.println("gameRecord: "  + gameRecord);
        System.out.println("gameDate: "  + formatedDateTime);
        System.out.println("############################################");
    }
}
