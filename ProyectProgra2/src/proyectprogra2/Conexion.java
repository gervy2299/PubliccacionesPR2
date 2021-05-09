
package proyectprogra2;

import java.sql.*;
import javax.swing.JOptionPane;


public class Conexion {
    private Connection conectar = null;
    public Connection  conexiondb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC", "superuser", "12345");
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR al conectarse" + "\n" + error);
        }
        return conectar;
    }
}
