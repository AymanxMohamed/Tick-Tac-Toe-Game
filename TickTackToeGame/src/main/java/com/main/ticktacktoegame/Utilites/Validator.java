/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ticktacktoegame.Utilites;

/**
 *
 * @author elsho
 */
public class Validator {
    public static boolean validatePassword(String password, String confirmedPassword) {
        return !isEmpty(password) && password.equals(confirmedPassword);
    }
    public static boolean validateUserName(String userName) {
        return !isEmpty(userName);
    }
    public static boolean isEmpty(String userName) {
        return userName.equals("");
    }
}
