/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Models;


import com.main.ticktacktoegame.Models.Enums.MappingFunctions;

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
    
    public String getPlayerRank() {
        return MappingFunctions.mapPlayerRank(playerRank).toString();
    }
    
    public String getRegisterDate() {
        return registerDate;
    }
    public void setBonusPoints(int value) {
        this.bonusPoints = value;
    }
    public void setPlayerRank(String value) {
        this.playerRank = value;
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
