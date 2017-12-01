package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
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
	@FXML
	private TableColumn colOpen;
	@FXML
	private TableColumn colAlter;
	@FXML
	private TableColumn colDelete;
	@FXML
	private Tab tabListaRegistros;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOpen;
	@FXML
	private Button btnRegister;
	
	private SingleSelectionModel<Tab> selectionModel;	
    private ObservableList<StockProduct> observableListStockProduct;
   
    private StockProduct itemSelecionado; 
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.listItems();
			
			tableViewStockProduct.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selecionarItemTabela(newValue));
			
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
	
	public void selecionarItemTabela(StockProduct pro) {
		this.itemSelecionado = pro;
	}
	
	@FXML
	public void excluirItem() throws IOException {
		int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o produto " + this.itemSelecionado.getName());
		if(confirm == JOptionPane.YES_OPTION){
			StockProductDAO proDao = new StockProductDAO();
			Connection con = DatabaseMySQL.getConnection();
			proDao.setConnection((com.mysql.jdbc.Connection) con);
			proDao.remover(this.itemSelecionado);
			JOptionPane.showMessageDialog(null, "Item excluído com sucesso!");
			this.listItems();
		}
	}
	
	@FXML
    public void doRegistration(ActionEvent e) throws IOException{
		btnRegister.setDisable(true);
		btnCancel.setDisable(true);
		txtName.setEditable(false);
		txtPrice.setEditable(false);
		txtQuantity.setEditable(false);
		
		
        StockProduct pro = new StockProduct();
        pro.setName(txtName.getText());
		pro.setPrice(Integer.parseInt(txtPrice.getText()));
		pro.setQuantity(Integer.parseInt(txtQuantity.getText()));
		
		StockProductDAO proDao = new StockProductDAO();
		Connection con = DatabaseMySQL.getConnection();
		proDao.setConnection((com.mysql.jdbc.Connection) con);
		proDao.inserir(pro);
		
		JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");
		
        this.listItems();
        selectionModel.select(tabListaRegistros);

		txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        btnRegister.setDisable(false);
		btnCancel.setDisable(false);
		txtName.setEditable(true);
		txtPrice.setEditable(true);
		txtQuantity.setEditable(true);
    }
	
	@FXML
    public void cancelRegistration(ActionEvent e) throws IOException{
		btnRegister.setDisable(true);
		btnCancel.setDisable(true);
		
		txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?");
		if (confirm == JOptionPane.YES_OPTION) {
			selectionModel.select(tabListaRegistros);
		}
		
		btnRegister.setDisable(false);
		btnCancel.setDisable(false);
    }
	
	@FXML
	private void showDetails() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProductDetails.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));  
        stage.show();
    }
	
	@FXML
	public void editar(StockProduct pro) throws IOException{
		System.out.println("Foi!");
    }
}