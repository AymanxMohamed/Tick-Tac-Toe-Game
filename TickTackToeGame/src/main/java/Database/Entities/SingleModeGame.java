/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;
import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.Enums.MappingFunctions;
import Database.Entities.Enums.PLAYER_CASE;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;


/**
 *
 * @author ayman
 */
public class SingleModeGame {
    private final int gameNumber;
    private final String playerName;
    private final int numberOfRounds;
    private final int playerScore;
    private final DIFFICULTY difficulty;
    private final PLAYER_CASE playerCase;
    private final FileInputStream gameRecord;
    private final LocalDateTime gameDate;
    
    
    public SingleModeGame(int gameNumber, String playerName, int numberOfRounds, int playerScore, String difficulty, String playerCase, FileInputStream gameRecord, LocalDateTime gameDate) {
        this.gameNumber = gameNumber;
        this.playerName = playerName;
        this.numberOfRounds = numberOfRounds;
        this.playerScore = playerScore;
        this.difficulty = MappingFunctions.mapDifficulty(difficulty);
        this.playerCase = MappingFunctions.mapPlayerCaseInSingle(playerCase);
        this.gameRecord = gameRecord;
        this.gameDate = gameDate;
    }
    public int getGameNumber() { return gameNumber; }
    public String getPlayerName() { return playerName; }
    public int getNumberOfRounds() { return numberOfRounds; }
    public int getPlayerScore() { return playerScore; }
    public DIFFICULTY getDifficulty() { return difficulty; }
    public PLAYER_CASE getPlayerCase() { return playerCase; }
    public FileInputStream getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }    
}
