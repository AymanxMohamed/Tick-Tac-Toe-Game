/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tictactoegameserver.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ImageView switchImage;
    
    @FXML
    TableView playersTable;

//    String onImagePath = "../../resources/images/on.png";
//    String offImagePath = "../../resources/images/off.png";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playersTable.setPlaceholder(new Label("Open Server to Show Players"));
        // TODO
    }

    @FXML
    public void switchClicked() {
//        if (server on)
//         switchImage.setImage(new Image("../../resources/images/on.png"));

//        if (server off)
//         switchImage.setImage(new Image("../../resources/images/off.png"));
    }

}
