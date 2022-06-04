
package ConexionSQL;

import Model.Pacientes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class PacientesBD {
    
    // Código para VER la tabla de la BD
    public ArrayList<Pacientes> ListPacientes() {
        ArrayList<Pacientes> pacientes = new ArrayList();
        Connection cnx = DataBaseConexion.getConnection();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID_PACIENTE,NOMBRE,APELLIDO,EDAD,SEXO,"
                    + "MOTIVO_CONSULTA FROM PACIENTES ORDER BY 1 ");
            while (rs.next()) {
                Pacientes paciente = new Pacientes();
                paciente.setId_paciente(rs.getInt("ID_PACIENTE"));
                paciente.setNombre(rs.getString("NOMBRE"));
                paciente.setApellido(rs.getString("APELLIDO"));
                paciente.setEdad(rs.getInt("EDAD"));
                paciente.setSexo(rs.getString("SEXO"));
                paciente.setMotivo_consulta(rs.getString("MOTIVO_CONSULTA"));
                pacientes.add(paciente);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al mostrar los datos de la tabla");
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return pacientes;
    }
    
    // Código para INSERTAR un nuevo paciente en la tabla de la BD
    public void agregarPaciente(Pacientes paciente) {
        Connection cnx = DataBaseConexion.getConnection();
        try {
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO PACIENTES(ID_PACIENTE,NOMBRE,APELLIDO,EDAD,SEXO,MOTIVO_CONSULTA)"
                    + " VALUES (?,?,?,?,?,?)");
            pst.setInt(1, paciente.getId_paciente());
            pst.setString(2, paciente.getNombre());
            pst.setString(3, paciente.getApellido());
            pst.setInt(4, paciente.getEdad());
            pst.setString(5, paciente.getSexo());
            pst.setString(6, paciente.getMotivo_consulta());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al agregar datos en la tabla");
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
    
    // Código para utilizar los Procedimientos almacenados de Oracle PL/SQL
    public void eliminarPaciente(int id_paciente) {
        Connection cnx = DataBaseConexion.getConnection();
        try {
            // Se realiza la llamada al pocedimiento de la BD
            CallableStatement cst = cnx.prepareCall("{call BAJA_PACIENTE (?)}");
            
            // Parametro para el procedimiento almacenado
            cst.setInt(1, id_paciente);
            
            // Ejecuta el procedimiento almacenado
            cst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al eliminar datos de la tabla");
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
    
    public void actualizaID(int id_ant, int id_nvo) {
        Connection cnx = DataBaseConexion.getConnection();
        try {
            // Se realiza la llamada al pocedimiento de la BD
            CallableStatement cst = cnx.prepareCall("{call CAMBIA_ID (?,?)}");
            
            // Parametro 1 para el procedimiento almacenado
            cst.setInt(1, id_ant);
            // Parametro 2 para el procedimiento almacenado
            cst.setInt(2, id_nvo);
            
            // Ejecuta el procedimiento almacenado
            cst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al actualizar datos de la tabla");
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
    
    // Código para utilizar las Funciones almacenadas de Oracle PL/SQL
    public double calculaPromedio() {
        double prom = 0;
        Connection cnx = DataBaseConexion.getConnection();
        try {
            // Se realiza la llamada a la funcion de la BD
            CallableStatement cst = cnx.prepareCall("{? = call PROM_EDAD (?)}");
            
            // Se indica que el primer interrogante es de salida
            cst.registerOutParameter(1, java.sql.Types.DECIMAL);
            
            // Se pasa un parámetro en el segundo interrogante
            cst.setInt(2, 0);
            
            // Ejecuta la función almacenada
            cst.executeUpdate();
            
            // Se recupera el resultado de la funcion pl/sql
            prom = cst.getDouble(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al calcular el promedio");
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return prom;
    }
    
    public double calculaPago(double cantidad) {
        double descuento = 0;
        Connection cnx = DataBaseConexion.getConnection();
        try {
            // Se realiza la llamada a la funcion de la BD
            CallableStatement cst = cnx.prepareCall("{? = call PAGO_DESC (?)}");
            
            // Se indica que el primer interrogante es de salida
            cst.registerOutParameter(1, java.sql.Types.DECIMAL);
            
            // Se pasa un parámetro en el segundo interrogante
            cst.setDouble(2, cantidad);
            
            // Ejecuta la función almacenada
            cst.executeUpdate();
            
            // Se recupera el resultado de la funcion pl/sql
            descuento = cst.getDouble(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al aplicar el descuento");
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return descuento;
    }
    
}
