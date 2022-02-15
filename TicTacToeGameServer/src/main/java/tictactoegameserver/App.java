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
    public static void tryJson() {
//        ArrayList<Integer> intArray = new ArrayList<>();
//        intArray.add(0);
//        intArray.add(1);
//        intArray.add(2);
//        intArray.add(3);
//        intArray.add(4);
//        intArray.add(5);
//        intArray.add(6);
//        intArray.add(7);
//        intArray.add(8);
//        JSONObject request = new JSONObject();
//        request.put("request", "XorOChoise");
//        request.put("data", intArray);
//        ArrayList<Integer> invitationReciever = (ArrayList<Integer>) request.get("data");
//        System.out.println(invitationReciever.get(0));
//        JSONObject data = new JSONObject();
//        data.put("invitationSender", "ayman");
//        data.put("invitationReciever", "ahmed");
//        JSONObject request = new JSONObject();
//        request.put("request", "game invitation");
//        request.put("data", data);
//        System.out.println(JSONValue.toJSONString(request));
          // next test move
          ArrayList<Integer> gameMoves  = new ArrayList<>();
          addMove(2, gameMoves);
          addMove(2, gameMoves);
          addMove(1, gameMoves);
          addMove(1, gameMoves);
          System.out.println(createGameMovesJson(gameMoves));
          System.out.println(getGameMovesArrayList(createGameMovesJson(gameMoves)));
          System.exit(0);
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