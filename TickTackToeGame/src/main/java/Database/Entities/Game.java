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
    protected int game_number;
    protected int numberOfRounds;
    protected String gameResult;
    // add the appropriate datatype to store the video 
//  private video gameRecord;
    protected LocalDateTime gameDate;
}
