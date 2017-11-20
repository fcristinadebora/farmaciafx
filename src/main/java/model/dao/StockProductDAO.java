package model.dao;

import model.classes.StockProduct;
import com.mysql.jdbc.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fcristinadebora
 */
public class StockProductDAO {

    private Connection connection; 

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(StockProduct pro) {
        String sql = "INSERT INTO StockProduct(name, price, quantity) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pro.getName());
            stmt.setInt(2, pro.getPrice());
            stmt.setInt(3, pro.getQuantity());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(StockProduct pro) {
        String sql = "UPDATE StockProduct SET name=?, price=?, quantity=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pro.getName());
            stmt.setInt(2, pro.getPrice());
            stmt.setInt(3, pro.getQuantity());
            stmt.setInt(4, pro.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(StockProduct pro) {
        String sql = "DELETE FROM StockProduct WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<StockProduct> listar() {
        String sql = "SELECT * FROM StockProduct";
        List<StockProduct> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	StockProduct pro = new StockProduct();
            	
            	pro.setId(resultado.getInt("id"));
            	pro.setName(resultado.getString("name"));
            	pro.setPrice(resultado.getInt("price"));
            	pro.setQuantity(resultado.getInt("quantity"));

                retorno.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public StockProduct buscar(StockProduct pro) {
        String sql = "SELECT * FROM StockProduct WHERE id=?";
        StockProduct retorno = new StockProduct();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pro.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
            	
            	pro.setName(resultado.getString("nome"));
            	pro.setPrice(resultado.getInt("price"));
            	pro.setQuantity(resultado.getInt("quantity"));
                retorno = pro;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
