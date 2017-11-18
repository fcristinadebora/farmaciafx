/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author cassioseffrin
 */
public class PrincipalController implements Initializable {

    
    @FXML
    private BorderPane MainContent;
    
    @FXML
    private MenuItem menuCliente;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            telaInicial();
        } catch (IOException ex) {
            
        }
    }   
    
    @FXML
    public void handlerMenu(ActionEvent e) throws IOException{
        
    }
    
    
     
    public void  telaInicial( ) throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/TelaInicial.fxml"));
        anchorPanePrincipal.getChildren().setAll(a);
    }
    
 
    
}
