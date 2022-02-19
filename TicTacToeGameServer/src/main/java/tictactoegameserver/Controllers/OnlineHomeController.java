/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tictactoegameserver.Controllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tictactoegameserver.App;
import tictactoegameserver.models.PlayerModel;
import tictactoegameserver.Network.Server;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class OnlineHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button serverStatusToggle;

    @FXML
    TableView<PlayerModel> onlinePlayersTable;

    @FXML
    TableColumn<PlayerModel, String> playerName;

    @FXML
    TableColumn<PlayerModel, Integer> bonusPoints;

    @FXML
    TableColumn<PlayerModel, String> playerRank;

    @FXML
    TableColumn<PlayerModel, String> isOnline;

    @FXML
    TableColumn<PlayerModel, String> InGame;

    @FXML
    TableColumn<PlayerModel, String> InChat;

    ObservableList<PlayerModel> playersList = FXCollections.observableArrayList(PlayerModel.playersList);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }

    public void refreshTable() {
        new Thread(() -> {
            while (true) {
                refresh();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(OnlineHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();
    }

    private void refresh() {
        if (!PlayerModel.playersList.isEmpty()) {
            playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
            bonusPoints.setCellValueFactory(new PropertyValueFactory<>("bonusPoints"));
            playerRank.setCellValueFactory(new PropertyValueFactory<>("playerRank"));
            InGame.setCellValueFactory(new PropertyValueFactory<>("inGameText"));
            InChat.setCellValueFactory(new PropertyValueFactory<>("inChatText"));
            isOnline.setCellValueFactory(new PropertyValueFactory<>("isOnlineText"));
            onlinePlayersTable.setItems(playersList);
        } else {
            onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
        }
    }

    @FXML
    public void toggleServerStatus() {
//        if (serverStatusToggle.getText().equals("Open Server")) {
//            System.out.println("open is called");
//            serverStatusToggle.setText("Close Server");
//            new Thread(() -> {
//                Server.startServer();
//            }).start();
//        } else {
//            System.out.println("close is called");
//            serverStatusToggle.setText("Open Server");
        Server.closeServer();
        try {
            App.setRoot("index");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        }
    }

}
