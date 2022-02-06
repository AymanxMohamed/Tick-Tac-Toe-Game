package com.main.ticktacktoegame;

import Database.DatabaseManager;
import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.Enums.GAME_TYPE;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            DatabaseManager.addMultiModeGameRecord("ahmed", "ahmed234", GAME_TYPE.LAN, 10, 6, null);
            DatabaseManager.addSingleModeGameRecord("ayman", 9, 4, DIFFICULTY.MEDIUM, null);
            DatabaseManager.closeDataBaseConnection();
            System.out.println("database connected");
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }


//      
//        launch();
    }

}