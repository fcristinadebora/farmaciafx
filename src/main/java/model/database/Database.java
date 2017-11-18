
package model.database;

import com.mysql.jdbc.Connection;

/**
 *
 * @author cassioseffrin
 */

public interface Database {
    
 
  //  public  Connection conectar();
    public void desconectar(Connection conn);
}
