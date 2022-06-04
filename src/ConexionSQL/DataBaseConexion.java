
package ConexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class DataBaseConexion {
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "system";
            String password = "GJreyes9805";
                    
            Connection cnx = DriverManager.getConnection(myDB, usuario, password);
            
            return cnx;
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
