/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Utilites;

import com.main.ticktacktoegame.App;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ayman
 */
public class AudioPlayer {
    public static Clip clip;
    public static File file;
    
    public static void play() {
        if (clip.isOpen()) {
            clip.start();
        }
    }
    
    public static void stop() {
        if (clip.isOpen()) {
            clip.stop();
        }
    }
    
    public static void toogleAudio() {
        if (clip.isOpen() && clip.isRunning()) {
            clip.stop();
        } else {
            clip.start();
        }
    }
    
    public static void changeAudio(String audioFile) {
        AudioInputStream audioStream = null;
        try {
            file = new File("C:\\02 git hub repos\\04 Java project\\TickTackToeGame\\src\\main\\resources\\com\\main\\ticktacktoegame\\sounds\\" + audioFile);
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            AudioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audioStream.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
