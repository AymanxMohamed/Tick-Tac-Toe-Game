package com.main.ticktacktoegame;

import Database.DatabaseManager;
import static Database.DatabaseManager.getPlayer;
import Database.Entities.Enums.DIFFICULTY;
import Database.Entities.Enums.GAME_TYPE;
import Database.Entities.MultiModeGame;
import Database.Entities.Player;
import Database.Entities.SingleModeGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // uncomment this code to try the database connection
        // note all database will be inside the server application
        // and will be removed from this client application
//        try {
//            DatabaseManager.openDataBaseConnection();
//            ArrayList<SingleModeGame> multiModeGameArray = DatabaseManager.getSingleModeGameRecords("ayman");
//            for (SingleModeGame mmg : multiModeGameArray) {
//                mmg.printData();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            System.exit(0);
//        } catch (NullPointerException ex) {
//            System.out.println("please Open the database connection first");
//        } finally {
//            DatabaseManager.closeDataBaseConnection();
//            System.exit(0);
//        }

        // uncomment this code before using the client application
         launch();
    }
}