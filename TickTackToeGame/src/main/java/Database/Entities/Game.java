/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Database.Entities;

import java.time.LocalDateTime;

/**
 *
 * @author ayman
 */
public abstract class Game {
    protected int numberOfRounds = 0; // from database and final
    protected String gameResult;  // from database and final
    // add the appropriate datatype to store the video 
//  private video gameRecord;
    protected LocalDateTime gameDate;
    
    public void increaseNumberOfRounds() { numberOfRounds++; }
    
    public int getNumberOfRounds() { return numberOfRounds; }
    public String getGameResult() { return gameResult; }
}
