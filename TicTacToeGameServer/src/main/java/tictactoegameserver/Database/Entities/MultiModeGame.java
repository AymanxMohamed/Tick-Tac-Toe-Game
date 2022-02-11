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
    private final String firstPlayerName;
    private final String secondPlayerName;
    private final GAME_TYPE gameType;
    private final int numberOfRounds;
    private final int firstPlayerScore; 
    private final int secondPlayerScore;
    private final String winner;
    private final String gameRecord;
    private final LocalDateTime gameDate;
    
    public MultiModeGame(int gameID, String firstPlayerName, String secondPlayerName, String gameType, int numberOfRounds, int firstPlayerScore, int secondPlayerScore, String gameResult, String gameRecord, Timestamp gameDate) {
        this.gameID = gameID;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.gameType = MappingFunctions.mapGameType(gameType);
        this.numberOfRounds = numberOfRounds;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
        this.winner = gameResult;
        this.gameRecord = gameRecord;
        this.gameDate = gameDate.toLocalDateTime();
    }
    public int getGameNumber() { return gameID; }
    public String getFirstPlayerName() { return firstPlayerName; }
    public String getSecondPlayerName() { return secondPlayerName; }
    public GAME_TYPE getGameType() { return gameType; }
    public int getNumberOfRounds() { return numberOfRounds; }
    public int getFirstPlayerScore() { return firstPlayerScore; }
    public int getSecondPlayerScore() { return secondPlayerScore; }
    public String getWinner() { return winner; }
    public String getGameRecord() { return gameRecord; }
    public LocalDateTime getGameDate() { return gameDate; }
    
    public void printData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedDateTime = gameDate.format(formatter);
        System.out.println("############################################");
        System.out.println("gameId: " + gameID);
        System.out.println("firstPlayerName: "  + firstPlayerName);
        System.out.println("secondPlayerName: "  + secondPlayerName);
        System.out.println("gameType: "  + MappingFunctions.mapGameType(gameType));
        System.out.println("firstPlayerScore: "  + firstPlayerScore);
        System.out.println("secondPlayerScore: "  + secondPlayerScore);
        System.out.println("winner: "  + winner);
        System.out.println("gameRecord: "  + gameRecord);
        System.out.println("gameDate: "  + formatedDateTime);
        System.out.println("############################################");
    }
}
