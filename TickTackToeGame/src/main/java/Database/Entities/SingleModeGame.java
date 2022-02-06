/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;
import Database.Entities.Enums.DIFFICULTY;
import java.io.File;
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
    private final String difficulty;
    private final String gameResult;
    private final File gameRecord;
    private final LocalDateTime gameDate;
    
    
    public SingleModeGame(int gameNumber, String playerName, int numberOfRounds, int playerScore, String difficulty, String gameResult, File gameRecord, LocalDateTime gameDate) {
        this.gameNumber = gameNumber;
        this.playerName = playerName;
        this.numberOfRounds = numberOfRounds;
        this.playerScore = playerScore;
        this.difficulty = difficulty;
        this.gameResult = gameResult;
        this.gameRecord = gameRecord;
        this.gameDate = gameDate;
    }
    public int getGameNumber() { return gameNumber; }
    public String getPlayerName() { return playerName; }
    public int getNumberOfRounds() { return numberOfRounds; }
    public int getPlayerScore() { return playerScore; }
    public String getDifficulty() { return difficulty; }
    public String getGameResult() { return gameResult; }
    public File getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }    
}
