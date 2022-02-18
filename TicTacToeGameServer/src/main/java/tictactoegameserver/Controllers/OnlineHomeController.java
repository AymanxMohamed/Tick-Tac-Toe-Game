/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tictactoegameserver.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tictactoegameserver.Network.Server;
import tictactoegameserver.models.PlayerModel;

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

        if (!PlayerModel.playersList.isEmpty()) {
            playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
            bonusPoints.setCellValueFactory(new PropertyValueFactory<>("bonusPoints"));
            playerRank.setCellValueFactory(new PropertyValueFactory<>("playerRank"));
            InGame.setCellValueFactory(new PropertyValueFactory<>("inGameText"));
            InChat.setCellValueFactory(new PropertyValueFactory<>("inChatText"));
            isOnline.setCellValueFactory(new PropertyValueFactory<>("isOnlineText"));
            onlinePlayersTable.setItems(playersList);
        }
        else 
            onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
        //refreshTable();
    }
    
    
    public void refreshTable() {
        new Thread(() -> {
         while (true) {
             refresh();
             try {
                 Thread.sleep(1000);
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
        }
        else 
            onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
    }
    

    @FXML
    public void openServer() {
        Server.startServer();
    }
    
    @FXML
    public void closeServer() {
        Server.closeServer();
    }

}
