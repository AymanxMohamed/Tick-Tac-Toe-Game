/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Models;


import com.main.ticktacktoegame.Models.Enums.MappingFunctions;
import com.main.ticktacktoegame.Models.Enums.PLAYER_RANK;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayman
 */

public class Player {
    private final String userName;
    private int bonusPoints;
    private String playerRank;
    private final String registerDate;

    public Player(String userName, int bonusPoints, String playerRank, String registerDate) {
        this.userName = userName;
        this.bonusPoints = bonusPoints;
        this.playerRank = playerRank;
        this.registerDate = registerDate;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public int getBonusPoints() {
        return bonusPoints;
    }

    public void printData() {
        System.out.println("############################################");
        System.out.println("userName: " + userName);
        System.out.println("bonusPoints: "  + bonusPoints);
        System.out.println("playerRank: " + MappingFunctions.mapPlayerRank(playerRank));
        System.out.println("registerDate: " + registerDate);
        System.out.println("############################################");
    }
    
}
