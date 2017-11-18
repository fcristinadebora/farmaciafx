package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.classes.Customer;
import model.dao.CustomerDAO;
import model.database.DatabaseMySQL;

public class CustomerController {
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtNascimento;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TableView listItemsTable;
	@FXML
	private TableColumn colId;
	
	public CustomerController() throws IOException {
		this.listItems();
	}
	
	@FXML
    public void listItems() throws IOException{
        StockProductDAO proDao = new StockProductDAO();
		Connection con = DatabaseMySQL.getConnection();
		proDao.setConnection((com.mysql.jdbc.Connection) con);
		List<StockProduct> itemList = new ArrayList<StockProduct>();
		itemList = proDao.listar();
		
		//List <>		
		 for(StockProduct actualItem : itemList) {			 
	          System.out.println("Item atual: " + actualItem.getName());
	     }
		 
		 
		
		
    }
	
	@FXML
    public void doRegistration(ActionEvent e) throws IOException{
        Customer cus = new Customer();
        cus.setName(txtName.getText());
        cus.setCpf(txtNascimento.getText());
        cus.setBirthDate(txtNascimento.getText());
        cus.setAddress(txtNascimento.getText());
		
		CustomerDAO cusDao = new CustomerDAO();
		Connection con = DatabaseMySQL.getConnection();
		cusDao.setConnection((com.mysql.jdbc.Connection) con);
		cusDao.inserir(cus);
    }
	
	@FXML
    public void cancelRegistration(ActionEvent e) throws IOException{
        txtName.setText("");
        txtCpf.setText("");
        txtNascimento.setText("");
        txtEndereco.setText("");
    }
}
