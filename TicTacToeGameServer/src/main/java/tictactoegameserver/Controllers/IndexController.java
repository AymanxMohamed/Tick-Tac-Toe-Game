/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tictactoegameserver.Controllers;




import javafx.fxml.FXML;
import tictactoegameserver.Network.Server;

/**
 * FXML Controller class
 *
 * @author Roo
 */
public class IndexController {
    
    
    @FXML
    public void openServer() {
        Server.startServer();
    }
    
    @FXML
    public void closeServer() {
        Server.closeServer();
    }
    
    
}
