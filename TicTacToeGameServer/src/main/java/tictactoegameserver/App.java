package tictactoegameserver;

import tictactoegameserver.Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.Entities.Enums.DIFFICULTY;
import tictactoegameserver.Network.Server;

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
            Server.startServer();
        } catch (IOException ex) {
            Server.closeServer();
            System.exit(0);
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

//             tryDatabase();
    }

    /**
     * @author: aymman
     *          use this function if you want to try database
     */
    public static void tryDatabase() {
        DatabaseManager.openDataBaseConnection();
//        DatabaseManager.addMultiModeGameRecord(1, "ayman", "shopaky", "ayman", null);
        DatabaseManager.addSingleModeGameRecord(1, "ayman", "X", DIFFICULTY.MEDIUM, "winner", null);

        DatabaseManager.closeDataBaseConnection();
        System.exit(0);
    }

}