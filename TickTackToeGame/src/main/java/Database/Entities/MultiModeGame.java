/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;

import java.io.File;
import java.time.LocalDateTime;

/**
 *
 * @author ayman
 */
public class MultiModeGame {
    private final int gameNumber;
    private final String firstPlayerName;
    private final String secondPlayerName;
    private final String gameType;
    private final int numberOfRounds;
    private final int firstPlayerScore; 
    private final int secondPlayerScore;
    private final String gameResult;
    private final File gameRecord;
    private final LocalDateTime gameDate;
    
    public MultiModeGame(int gameNumber, String firstPlayerName, String secondPlayerName, String gameType, int numberOfRounds, int firstPlayerScore, int secondPlayerScore, String gameResult, File gameRecord, LocalDateTime gameDate) {
        this.gameNumber = gameNumber;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.gameType = gameType;
        this.numberOfRounds = numberOfRounds;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
        this.gameResult = gameResult;
        this.gameRecord = gameRecord;
        this.gameDate = gameDate;
    }
    public int getGameNumber() { return gameNumber; }
    public String getFirstPlayerName() { return firstPlayerName; }
    public String getSecondPlayerName() { return secondPlayerName; }
    public String getGameType() { return gameType; }
    public int getNumberOfRounds() { return numberOfRounds; }
    public int getFirstPlayerScore() { return firstPlayerScore; }
    public int getSecondPlayerScore() { return secondPlayerScore; }
    public String getGameResult() { return gameResult; }
    public File getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }
}
