package tictactoegameserver;

import tictactoegameserver.Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tictactoegameserver.Database.Entities.Player;
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

      public static void main(String[] args) throws NoSuchAlgorithmException {
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
     * @author: Ayman
     *          Use this function if you want to try JSON
     */
    public static void tryJson() {

        // Step 1: prepare the jsonObject that you want to send
        // as a request or as a response that consists of action and data
        JSONObject data = new JSONObject();
        data.put("username", "ayman");
        data.put("password", "1234567");
        System.out.println(data);

        JSONObject request = new JSONObject();
        request.put("action", "login");
        request.put("data", data);
        System.out.println(request);

        // Step 2: Convert the requsetJson object to string
        // send it to the server
        String jsonText = JSONValue.toJSONString(request);

        // Step 3: in the server receive the jsonText and parse it to jsonObject
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonText);

        // Step 4: fetch the data from the jsonObject
        String action = (String) jsonObject.get("action");

        // Note: data will be also json object and you have to use casting
        JSONObject dataObject = (JSONObject) jsonObject.get("data");
        String userName = (String) dataObject.get("username");
        String password = (String) dataObject.get("password");
                  
        System.out.println("username: " + userName);
        System.out.println("password: " + password);
    }

    /**
     * @author: aymman
     *          use this function if you want to try database
     */
    public static void tryDatabase() {
        try {
           DatabaseManager.openDataBaseConnection();
           DatabaseManager.addNewPlayer("shopaky", "123456");
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("please Open the database connection first");
        } finally {
            DatabaseManager.closeDataBaseConnection();
        }
    }

}