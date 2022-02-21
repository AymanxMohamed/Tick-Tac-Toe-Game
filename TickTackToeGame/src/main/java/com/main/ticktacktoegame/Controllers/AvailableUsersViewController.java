/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.main.ticktacktoegame.Controllers;

import com.main.ticktacktoegame.App;
import com.main.ticktacktoegame.Models.Opponent;
import com.main.ticktacktoegame.Network.Client;
import static com.main.ticktacktoegame.Network.RequestCreator.invitePlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class AvailableUsersViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView onlinePlayersTable;
    
    @FXML
    TableColumn playerTableColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Opponent> onlinePlayers  = Opponent.opponentPlayers;
        System.out.println(onlinePlayers);
        if (!onlinePlayers.isEmpty()) {
            onlinePlayers.forEach(player -> {
                if (!player.isInGame() && !player.isInChat())
                {
                    Button btn = new Button();
                    btn.setText(player.getPlayerName());
                    btn.setOnMouseClicked(mouseEvent -> {
                    Client.opponnentName = player.getPlayerName();
                    Client.sendRequest(invitePlayer(player.getPlayerName()));
                    btn.setDisable(true);
                    });
                    addButtonToTable();
                } else {
                    System.out.println(player.getPlayerName() + " is online but not avilable");
                }
            });
        } else {
            System.out.println(onlinePlayers);
            onlinePlayersTable.setPlaceholder(new Label("No Online Players Right Now"));
        }
    }
    @FXML
    public void goToOnlineView() {
        try {
            App.setRoot("onlineHome");
        } catch (IOException ex) {
            Logger.getLogger(AvailableUsersViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addButtonToTable() {
        TableColumn<Data, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
            @Override
            public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
                final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Data data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        onlinePlayersTable.getColumns().add(colBtn);

    }
}
