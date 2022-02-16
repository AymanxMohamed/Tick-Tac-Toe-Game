package tictactoegameserver;

import tictactoegameserver.Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoegameserver.Database.Entities.Enums.DIFFICULTY;
import static tictactoegameserver.Network.ResponseCreator.*;
import tictactoegameserver.Network.Server;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
//        printArray(tryJson());
    }

    /**
     * @author: aymman
     *          use this function if you want to try database
     */
    public static void tryDatabase() {
        DatabaseManager.openDataBaseConnection();
        DatabaseManager.addSingleModeGameRecord("1", "ayman", "X", DIFFICULTY.MEDIUM, "winner", null);
        DatabaseManager.closeDataBaseConnection();
        System.exit(0);
    }
    public static String tryJson() {
        ArrayList<Integer> gameMoves = new ArrayList<>();
        gameMoves.add(0);
        gameMoves.add(1);
        gameMoves.add(2);
        gameMoves.add(3);
        gameMoves.add(4);
        gameMoves.add(5);
        gameMoves.add(6);
        gameMoves.add(7);
        gameMoves.add(8);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(gameMoves);
        JSONObject data = new JSONObject();
        data.put("jsonArray", jsonArray);
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "draw single moves");
        responseObject.put("data", data);
        return JSONValue.toJSONString(responseObject);
    }
    public static void printArray(String responseString) {
        JSONObject responseObject = (JSONObject) JSONValue.parse(responseString);
        String response = (String) responseObject.get("response");
        JSONObject data = (JSONObject) responseObject.get("data");
        
        System.out.println(data);
       JSONArray jsonArray = (JSONArray) data.get("jsonArray");
        System.out.println(jsonArray);
        ArrayList<Object> gameMoves = (ArrayList<Object>)jsonArray.get(0);
        //int inx = ((Long) gameMoves.get(0)).intValue();
//        ArrayList<Integer> newGameMoves = new ArrayList<>();
        ArrayList<Integer> newGameMoves = getIntegerArray(gameMoves);

//        for (var move : gameMoves) {
//            newGameMoves.add(((Long) move).intValue());
//        }
        
//        int inx = (Integer) index;
//        System.out.println(inx);
        newGameMoves.forEach(move -> System.out.println(move));
    }
    public static ArrayList<Integer> getIntegerArray(ArrayList<Object> objArray) {
        ArrayList<Integer> newGameMoves = new ArrayList<>();
        for (var obj : objArray) {
            newGameMoves.add(((Long) obj).intValue());
        }
        return newGameMoves;
    }
    public static void addMove(int index, ArrayList<Integer> gameMoves) {
            if (!gameMoves.contains(index)) {
                gameMoves.add(index);
            }
        }
    public static void getMoves(ArrayList<Integer> gameMoves){
        for (var move : gameMoves){
            System.out.println(move);
        }
    }
}