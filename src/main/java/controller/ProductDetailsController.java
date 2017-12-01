package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProductDetailsController implements Initializable{

	@FXML
	private Button btnCancel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Product details");
	}
	
	@FXML
	public void cancel() {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		
		int confirm = JOptionPane.showConfirmDialog(null, "Deseja cancelar? Todas as alterações serão perdidas!");
		
		if(confirm == JOptionPane.YES_OPTION) {
			stage.close();
		}
	}
}
