package proyectprogra2;

import Formularios.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author eltu_
 */
public class sqlMetodos {

    public boolean buscarUsuario(String usuario, String contrasenia, String cargo) {
        
        boolean hola = false;

        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.conexiondb();
            String sql = "SELECT usuario, contrasenia, CARGO FROM buscarusuariorol where usuario = '" + usuario + "' and contrasenia = '" + contrasenia + "' and CARGO = '"+cargo+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = rs.getString("usuario");
                contrasenia = rs.getString("contrasenia");
                cargo = rs.getString("CARGO");
                
                hola = true;

            } else {
                JOptionPane.showMessageDialog(null, "ERROR DE CARGO , USUARIO O CONTRASEÑA, \n PORFAVOR VUELVA A INGRESAR LOS CAMPOS.");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        
        return hola;

    }

}
