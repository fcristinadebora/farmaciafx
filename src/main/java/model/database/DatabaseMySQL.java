package model.database;

 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.mysql.jdbc.Connection;

/**
 *
 * @author cassioseffrin
 */
public class DatabaseMySQL implements Database {

    private static Connection connection;
 
    
    public static Connection getConnection(){
        if (connection == null){
            return conectar();
        }else{
            return connection;
        }
    }
    
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/farmacia", "root","123456");
            return  connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
