/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import model.clinica.Cliente;
import model.dao.ClienteDAO;
import model.database.DatabaseMySQL;

/**
 * FXML Controller class
 *
 * @author cassioseffrin
 */
public class ClientesController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtEndereco;
    @FXML
    private AnchorPane anchorCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handlerSalvar(ActionEvent e) throws IOException {
        System.out.println("salvando cliente...");

        try {
            Cliente cliente = new Cliente();
            cliente.setNome(txtNome.getText());
            cliente.setCpf(Long.parseLong(txtCPF.getText()));
            cliente.setEndereco(txtEndereco.getText());

            DatabaseMySQL db = new DatabaseMySQL();
            Connection con = db.conectar();

            ClienteDAO cliDao = new ClienteDAO();
            cliDao.setConnection((com.mysql.jdbc.Connection) con);
            cliDao.inserir(cliente);
            handlerTelaInicial(new ActionEvent());
        } catch (Exception ex) {
          
            System.out.println("mostrar anchor pane de erro");
        }

    }

    @FXML
    public void handlerTelaInicial(ActionEvent e) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/TelaInicial.fxml"));
        anchorCliente.getChildren().setAll(a);
    }

}
