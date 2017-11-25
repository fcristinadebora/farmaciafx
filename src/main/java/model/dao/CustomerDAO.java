package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

import model.classes.Customer;

public class CustomerDAO {
	private Connection connection; 

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Customer cus) {
        String sql = "INSERT INTO Customer(name, cpf, birthDate, address) VALUES(?,?,?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cus.getName());
            stmt.setString(2, cus.getCpf());
            stmt.setDate(3, cus.getBirthDate());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Customer cus) {
        String sql = "UPDATE Customer SET name=?, cpf=?, birthDate=?, address=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cus.getName());
            stmt.setString(2, cus.getCpf());
            stmt.setDate(3, cus.getBirthDate());
            stmt.setInt(4, cus.getId());
            stmt.setString(5, cus.getAddress());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Customer cus) {
        String sql = "DELETE FROM Customer WHERE id=?";
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

    public List<Customer> listar() {
        String sql = "SELECT * FROM Customer";
        List<Customer> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Customer cus = new Customer();
            	
            	cus.setId(resultado.getInt("id"));
            	cus.setName(resultado.getString("name"));
            	cus.setCpf(resultado.getString("cpf"));
            	//cus.setBirthDate(resultado.getDate("birthDate"));
            	cus.setAddress(resultado.getString("address"));

                retorno.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Customer buscar(Customer cus) {
        String sql = "SELECT * FROM Customer WHERE id=?";
        Customer retornoCus = new Customer();
        Customer retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cus.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
            	
            	retornoCus.setId(resultado.getInt("id"));
            	retornoCus.setName(resultado.getString("name"));
            	retornoCus.setCpf(resultado.getString("cpf"));
            	retornoCus.setAddress(resultado.getString("address"));
            	retorno = retornoCus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
