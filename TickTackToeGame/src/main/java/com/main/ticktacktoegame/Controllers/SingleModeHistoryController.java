/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Models.SingleModeGameModel;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.invitePlayer;
import static com.main.ticktacktoegame.Network.RequestCreator.invitePlayerForChat;
import com.main.ticktacktoegame.Network.ResponseHandler;
import java.io.IOException;
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

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class SingleModeHistoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label usernameLabel;
    
    @FXML
    Label bonusPointsLabel;
    
    @FXML
    Label rankLabel;

    @FXML
    TableView<SingleModeGameModel> singleModeHistoryTabel;
    
    @FXML
    TableColumn<SingleModeGameModel, String> gameDate;
    
    @FXML
    TableColumn<SingleModeGameModel, String> playerType;
    
    @FXML
    TableColumn<SingleModeGameModel, String> difficulty;
    
    @FXML
    TableColumn<SingleModeGameModel, String> playerCase;
    
    
    
    ObservableList<SingleModeGameModel> singleModeHistory = FXCollections.observableArrayList(SingleModeGameModel.singleModeHistory);
    
    @FXML
    public void switchToWelcomeView() {
        try {
            App.setRoot("WelcomeView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }
    
    
    public void refreshTable() {
        new Thread(() -> {
         while (true) {
             refresh();
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException ex) {
                 Logger.getLogger(SingleModeHistoryController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
            
        }).start();
    }
    private void refresh() {
        if (!SingleModeGameModel.singleModeHistory.isEmpty()) {
            gameDate.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
            playerType.setCellValueFactory(new PropertyValueFactory<>("playerType"));
            difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
            playerCase.setCellValueFactory(new PropertyValueFactory<>("playerCase"));

            singleModeHistoryTabel.setItems(singleModeHistory);
        }
        else 
            singleModeHistoryTabel.setPlaceholder(new Label("You didn't play any single mode game"));
    }
    
    @FXML
    public void exit(){
        try {
            App.setRoot("exitView");
        } catch (IOException ex) {
            Logger.getLogger(SingleModeHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    public void playGame() {
        SingleModeGameModel selectedGame = singleModeHistoryTabel.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            selectedGame.playGame();
        }
    }
}
