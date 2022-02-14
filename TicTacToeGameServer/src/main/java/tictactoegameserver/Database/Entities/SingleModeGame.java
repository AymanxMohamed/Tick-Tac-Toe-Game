/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Database.Entities;
import tictactoegameserver.Database.Entities.Enums.DIFFICULTY;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ayman
 */
public class SingleModeGame {
    private final String gameID;
    private final String playerName;
    private final String playerType;
    private final DIFFICULTY difficulty;
    private final String playerCase;
    private final String gameRecord;
    private final LocalDateTime gameDate;
    
    
    public SingleModeGame(String gameID, String playerName, String playerType, String difficulty, String playerCase, String gameRecord, Timestamp gameDate) {
        this.gameID = gameID;
        this.playerName = playerName;
        this.playerType = playerType;
        this.difficulty = MappingFunctions.mapDifficulty(difficulty);
        this.playerCase = playerCase;
        this.gameRecord = gameRecord;
        this.gameDate = gameDate.toLocalDateTime();
    }
    public String getGameID() { return gameID; }
    public String getPlayerName() { return playerName; }
    public String getPlayerType() { return playerType; }
    public DIFFICULTY getDifficulty() { return difficulty; }
    public String getPlayerCase() { return playerCase; }
    public String getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }

    public void printData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedDateTime = gameDate.format(formatter);
        System.out.println("############################################");
        System.out.println("gameId: " + gameID);
        System.out.println("playerName: "  + playerName);
        System.out.println("difficulty: "  + MappingFunctions.mapDifficulty(difficulty));
        System.out.println("playerCase: "  + playerCase);
        System.out.println("gameRecord: "  + gameRecord);
        System.out.println("gameDate: "  + formatedDateTime);
        System.out.println("############################################");
    }
}
