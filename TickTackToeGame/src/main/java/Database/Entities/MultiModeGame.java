/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;

import Database.Entities.Enums.GAME_TYPE;
import Database.Entities.Enums.MappingFunctions;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

/**
 *
 * @author ayman
 */
public class MultiModeGame {
    private final int gameNumber;
    private final String firstPlayerName;
    private final String secondPlayerName;
    private final GAME_TYPE gameType;
    private final int numberOfRounds;
    private final int firstPlayerScore; 
    private final int secondPlayerScore;
    private final String winner;
    private final FileInputStream gameRecord;
    private final LocalDateTime gameDate;
    
    public MultiModeGame(int gameNumber, String firstPlayerName, String secondPlayerName, String gameType, int numberOfRounds, int firstPlayerScore, int secondPlayerScore, String gameResult, FileInputStream gameRecord, LocalDateTime gameDate) {
        this.gameNumber = gameNumber;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.gameType = MappingFunctions.mapGameType(gameType);
        this.numberOfRounds = numberOfRounds;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
        this.winner = gameResult;
        this.gameRecord = gameRecord;
        this.gameDate = gameDate;
    }
    public int getGameNumber() { return gameNumber; }
    public String getFirstPlayerName() { return firstPlayerName; }
    public String getSecondPlayerName() { return secondPlayerName; }
    public GAME_TYPE getGameType() { return gameType; }
    public int getNumberOfRounds() { return numberOfRounds; }
    public int getFirstPlayerScore() { return firstPlayerScore; }
    public int getSecondPlayerScore() { return secondPlayerScore; }
    public String getWinner() { return winner; }
    public FileInputStream getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }
}
