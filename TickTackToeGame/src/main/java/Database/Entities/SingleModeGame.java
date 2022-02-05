/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Entities;
import Database.Entities.Enums.DIFFICULTY;

/**
 *
 * @author ayman
 */
public class SingleModeGame extends Game{
    private String playerName;
    private int playerScore;
    private DIFFICULTY difficulty;
    
    public SingleModeGame() {

    }
    
    public String getPlayerName() { return playerName; }
    public int getPlayerScore() { return playerScore; }
    public DIFFICULTY getDifficulty() { return difficulty; }
    
}
