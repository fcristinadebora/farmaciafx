package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.classes.StockProduct;
import model.dao.StockProductDAO;
import model.database.DatabaseMySQL;

public class ProductController {
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtPrice;
	@FXML
	private TextField txtQuantity;
	@FXML
	private TableView<Object> listItemsTable;
	@FXML
	private TableColumn colId;
	@FXML
	private TableColumn colName;
	@FXML
	private TableColumn colPrice;
	@FXML
	private TableColumn colQuantity;
	
	public ProductController() throws IOException {
		this.listItems();
	}
	
	@FXML
    public void listItems() throws IOException{
        StockProductDAO proDao = new StockProductDAO();
		Connection con = DatabaseMySQL.getConnection();
		proDao.setConnection((com.mysql.jdbc.Connection) con);
		List<StockProduct> itemList = new ArrayList<StockProduct>();
		itemList = proDao.listar();
		
		System.out.println(itemList.size());
		
		listItemsTable = new TableView<>();
		
		colId = new TableColumn<>("ID");
		colName = new TableColumn<>("Nome");
		colPrice = new TableColumn<>("Valor");
		colQuantity = new TableColumn<>("Quantidade2");
		
		
		colId.setCellFactory(new PropertyValueFactory<>("id"));
		colName.setCellFactory(new PropertyValueFactory<>("name"));
		colPrice.setCellFactory(new PropertyValueFactory<>("price"));
		colQuantity.setCellFactory(new PropertyValueFactory<>("quantity"));
		
		listItemsTable.setItems(FXCollections.observableArrayList(itemList));
		listItemsTable.getColumns().addAll(colId, colName, colPrice, colQuantity);
		
        //for(StockProduct actualItem : itemList) {
			
	    //}
		 
		 
		
		
    }
	
	@FXML
    public void doRegistration(ActionEvent e) throws IOException{
        StockProduct pro = new StockProduct();
        pro.setName(txtName.getText());
		pro.setPrice(Integer.parseInt(txtPrice.getText()));
		pro.setQuantity(Integer.parseInt(txtQuantity.getText()));
		
		StockProductDAO proDao = new StockProductDAO();
		Connection con = DatabaseMySQL.getConnection();
		proDao.setConnection((com.mysql.jdbc.Connection) con);
		proDao.inserir(pro);
    }
	
	@FXML
    public void cancelRegistration(ActionEvent e) throws IOException{
        txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }
}
