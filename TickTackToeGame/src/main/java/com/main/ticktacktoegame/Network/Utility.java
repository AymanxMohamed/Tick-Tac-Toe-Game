/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Network;

import java.util.ArrayList;

/**
 *
 * @author ayman
 */
public class Utility {
    public static ArrayList<Integer> getIntegerArray(ArrayList<Object> objArray) {
        ArrayList<Integer> newGameMoves = new ArrayList<>();
        for (var obj : objArray) {
            newGameMoves.add(((Long) obj).intValue());
        }
        return newGameMoves;
    }
}
