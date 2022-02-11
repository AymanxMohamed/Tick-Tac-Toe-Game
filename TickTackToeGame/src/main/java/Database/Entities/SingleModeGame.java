/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;
import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.Enums.MappingFunctions;
import Database.Entities.Enums.PLAYER_CASE;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ayman
 */
public class SingleModeGame {
    private final int gameID;
    private final String playerName;
    private final int numberOfRounds;
    private final int playerScore;
    private final DIFFICULTY difficulty;
    private final PLAYER_CASE playerCase;
    private final String gameRecord;
    private final LocalDateTime gameDate;
    
    
    public SingleModeGame(int gameID, String playerName, int numberOfRounds, int playerScore, String difficulty, String playerCase, String gameRecord, Timestamp gameDate) {
        this.gameID = gameID;
        this.playerName = playerName;
        this.numberOfRounds = numberOfRounds;
        this.playerScore = playerScore;
        this.difficulty = MappingFunctions.mapDifficulty(difficulty);
        this.playerCase = MappingFunctions.mapPlayerCaseInSingle(playerCase);
        this.gameRecord = gameRecord;
        this.gameDate = gameDate.toLocalDateTime();
    }
    public int getGameNumber() { return gameID; }
    public String getPlayerName() { return playerName; }
    public int getNumberOfRounds() { return numberOfRounds; }
    public int getPlayerScore() { return playerScore; }
    public DIFFICULTY getDifficulty() { return difficulty; }
    public PLAYER_CASE getPlayerCase() { return playerCase; }
    public String getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }

    public void printData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedDateTime = gameDate.format(formatter);
        System.out.println("############################################");
        System.out.println("gameId: " + gameID);
        System.out.println("playerName: "  + playerName);
        System.out.println("numberOfRounds: "  + numberOfRounds);
        System.out.println("playerScore: "  + playerScore);
        System.out.println("difficulty: "  + MappingFunctions.mapDifficulty(difficulty));
        System.out.println("playerCase: "  + playerCase);
        System.out.println("gameRecord: "  + gameRecord);
        System.out.println("gameDate: "  + formatedDateTime);
        System.out.println("############################################");
    }
}
