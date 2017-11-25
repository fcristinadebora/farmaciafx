package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{
	
	@FXML
	private BorderPane contentPane;
	
	@FXML private MenuBar mainMenu;
	
	@FXML
	private Menu menuVendas;
	@FXML
	private Menu menuProdutos;
	@FXML
	private Menu menuClientes;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        try {
        	System.out.println("App iniciado");
        	telaInicial();
        } catch (IOException ex) {
            System.out.println("Erro: não foi possível inicializar a aplicação");
        }
	} 
	
	@FXML
	public void handlerMenuVendas(){
		try {
			System.out.println("Menu Vendas");
			BorderPane content;
			content = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/Product.fxml"));
			contentPane.setCenter(content);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@FXML
    public void handlerMenuClientes(ActionEvent e){
		try {
			BorderPane content;
			content = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/Customer.fxml"));
			contentPane.setCenter(content);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@FXML
    public void handlerMenuProdutos(ActionEvent e){
		try {
			System.out.println("Menu Produtos");
			BorderPane content;
			content = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/Product.fxml"));
			contentPane.setCenter(content);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void telaInicial() throws IOException {
		BorderPane content = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/Product.fxml"));
		contentPane.setCenter(content);
	}
}
