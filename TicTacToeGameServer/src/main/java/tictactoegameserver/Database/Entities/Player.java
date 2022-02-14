/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoegameserver.Database.Entities;

import tictactoegameserver.Database.DatabaseManager;
import tictactoegameserver.Database.Entities.Enums.MappingFunctions;
import tictactoegameserver.Database.Entities.Enums.PLAYER_RANK;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ayman
 */

public class Player {
    private final String userName;
    private final String password;
    private int bonusPoints;
    private PLAYER_RANK playerRank;
    private final LocalDateTime registerDate;
    private String playerStatus;

    public Player(String userName, String password, int bonusPoints, String playerRank, Timestamp registerDate,
            String playerStatus) {
        this.userName = userName;
        this.password = password;
        this.bonusPoints = bonusPoints;
        this.playerRank = MappingFunctions.mapPlayerRank(playerRank);
        this.registerDate = registerDate.toLocalDateTime();
        this.playerStatus = playerStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswod() {
        return password;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public PLAYER_RANK getPlayerRank() {
        return playerRank;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void increaseBonusPoints() {
        switch (playerRank) {
            case BRONZE:
                bonusPoints += 5;
                break;
            case SILVER:
                bonusPoints += 5;
                break;
            case GOLD:
                bonusPoints += 4;
                break;
            case PLATINUM:
                bonusPoints += 4;
                break;
            case MASTER:
                bonusPoints += 3;
                break;
            case GRAND_MASTER:
                bonusPoints += 3;
                break;
            case CHALLENGER:
                bonusPoints += 2;
                break;
        }
        updateData();
    }

    public void decreaseBonusPoints() {
        if (bonusPoints == 0)
            return;
        switch (playerRank) {
            case BRONZE:
                bonusPoints -= 1;
                break;
            case SILVER:
                bonusPoints -= 2;
                break;
            case GOLD:
                bonusPoints -= 3;
                break;
            case PLATINUM:
                bonusPoints -= 3;
                break;
            case MASTER:
                bonusPoints -= 4;
                break;
            case GRAND_MASTER:
                bonusPoints -= 4;
                break;
            case CHALLENGER:
                bonusPoints -= 5;
                break;
        }
        updateData();
    }

    public void setPlayerRank(PLAYER_RANK value) {
        playerRank = value;
    }
    public void setPlayerStatus(String value) {
        playerStatus = value;
    }


    public void printData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedDateTime = registerDate.format(formatter);
        System.out.println("############################################");
        System.out.println("userName: " + userName);
        System.out.println("bonusPoints: "  + bonusPoints);
        System.out.println("playerRank: " + MappingFunctions.mapPlayerRank(playerRank));
        System.out.println("registerDate: " + formatedDateTime);
        System.out.println("player_status: " + playerStatus);
        System.out.println("############################################");
    }
    
    private void updateData() {
        DatabaseManager.openDataBaseConnection();
        DatabaseManager.updatePlayerData(this);
        DatabaseManager.closeDataBaseConnection();
    }
}
