package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class ProductController implements Initializable{
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtPrice;
	@FXML
	private TextField txtQuantity;
	
	@FXML
	private TableView<StockProduct> tableViewStockProduct;
	@FXML
	private TableColumn<StockProduct, Integer> colId;
	@FXML
	private TableColumn<StockProduct, String> colName;
	@FXML
	private TableColumn<StockProduct, Integer> colPrice;
	@FXML
	private TableColumn<StockProduct, Integer>colQuantity;	
	
    private ObservableList<StockProduct> observableListStockProduct;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.listItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
    public void listItems() throws IOException{
        StockProductDAO proDao = new StockProductDAO();
		Connection con = DatabaseMySQL.getConnection();
		proDao.setConnection((com.mysql.jdbc.Connection) con);
		List<StockProduct> itemList = proDao.listar();

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        observableListStockProduct = FXCollections.observableArrayList(itemList);
        tableViewStockProduct.setItems(observableListStockProduct);
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
