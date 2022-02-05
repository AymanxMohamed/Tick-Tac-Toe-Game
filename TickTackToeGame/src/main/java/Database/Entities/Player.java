/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;

import java.time.LocalDateTime;

/**
 *
 * @author ayman
 */
public class Player {
    private final String userName;
    private int bonusPoints; 
    private PLAYER_RANK playerRank;
    private final LocalDateTime registerDate; 
    
    public Player(String userName, int bonusPoints, PLAYER_RANK playerRank, LocalDateTime registerDate) {
        this.userName = userName;
        this.bonusPoints = bonusPoints;
        this.playerRank = playerRank;
        this.registerDate = registerDate;
    }

    public String getUserName() { return userName; }
    public int getBonusPoints() { return bonusPoints; }
    public PLAYER_RANK getPlayerRank() { return playerRank; }
    public LocalDateTime getRegisterDate() { return registerDate; }

    public void increaseBonusPoints() {
        switch(playerRank) {
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
    }
    public void decreaseBonusPoints() {
        switch(playerRank) {
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
    }
    public void setPlayerRank(PLAYER_RANK value) { playerRank = value; }
}


