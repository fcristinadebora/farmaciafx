package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.classes.Customer;
import model.classes.StockProduct;
import model.dao.CustomerDAO;
import model.dao.StockProductDAO;
import model.database.DatabaseMySQL;

public class CustomerController implements Initializable{
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtBirthDate;
	@FXML
	private TextArea txtAddress;
	@FXML
	private TableView listItemsTable;
	@FXML
	private TableColumn colId;
	@FXML
	private TableColumn colName;
	@FXML
	private TableColumn colCpf;
	@FXML
	private TableColumn colBirthDate;
	@FXML
	private Tab tabRegistros;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnRegister;
	
	private SingleSelectionModel<Tab> selectionModel;	
    private ObservableList<Customer> observableListCustomers;
	
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
		CustomerDAO cusDao = new CustomerDAO();
		Connection con = DatabaseMySQL.getConnection();
		cusDao.setConnection((com.mysql.jdbc.Connection) con);
		List<Customer> itemList = cusDao.listar();

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        //colBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        
        /*
        colOpen.setCellValueFactory(
        	    param -> new ReadOnlyObjectWrapper<>(param.getValue())
        	);
        	colOpen.setCellFactory(param -> new TableCell<StockProduct, StockProduct>() {
        	    private final Button deleteButton = new Button("Unfriend");

        	    @Override
        	    protected void updateItem(StockProduct person, boolean empty) {
        	        super.updateItem(person, empty);

        	        if (person == null) {
        	            setGraphic(null);
        	            return;
        	        }

        	        setGraphic(deleteButton);
        	        deleteButton.setOnAction(
        	            event -> getTableView().getItems().remove(person)
        	        );
        	    }
        	});
        */
        
        observableListCustomers = FXCollections.observableArrayList(itemList);
        listItemsTable.setItems(observableListCustomers);
        
    }
	
	@FXML
    public void doRegistration(ActionEvent e) throws IOException{
		btnRegister.setDisable(true);
		btnCancel.setDisable(true);
		txtName.setEditable(false);
		txtCpf.setEditable(false);
		txtBirthDate.setEditable(false);
		txtAddress.setEditable(false);
		
		
        Customer cus = new Customer();
        cus.setName(txtName.getText());
        cus.setCpf(txtCpf.getText());
        //cus.setBirthDate(txtBirthDate.getText());
        cus.setAddress(txtAddress.getText());
       
		CustomerDAO cusDAO = new CustomerDAO();
		Connection con = DatabaseMySQL.getConnection();
		cusDAO.setConnection((com.mysql.jdbc.Connection) con);
		cusDAO.inserir(cus);
		
		JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");
		
        this.listItems();
        selectionModel.select(tabRegistros);

		txtName.setText("");
        txtCpf.setText("");
        txtBirthDate.setText("");
        txtAddress.setText("");
        btnRegister.setDisable(false);
		btnCancel.setDisable(false);
		txtName.setEditable(true);
        txtCpf.setEditable(true);
        txtBirthDate.setEditable(true);
        txtAddress.setEditable(true);
    }
	
	@FXML
    public void cancelRegistration(ActionEvent e) throws IOException{
		btnRegister.setDisable(true);
		btnCancel.setDisable(true);
		
		txtName.setText("");
        txtCpf.setText("");
        txtAddress.setText("");
        txtBirthDate.setText("");
        
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?");
		if (confirm == JOptionPane.YES_OPTION) {
			selectionModel.select(tabRegistros);
		}
		
		btnRegister.setDisable(false);
		btnCancel.setDisable(false);
    }
	
	@FXML
	public void editar(StockProduct pro) throws IOException{
		System.out.println("Foi!");
    }
}