package Login;

import Formularios.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import proyectprogra2.Conexion;
import proyectprogra2.sqlMetodos;

public class login extends javax.swing.JFrame {

    Conexion cn = new Conexion();
    sqlMetodos mtds = new sqlMetodos();

    public login() {
        initComponents();
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        cbxRoles.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarComboBOxRoles();

        for (int i = 0; i < lista.size(); i++) {
            cbxRoles.addItem(lista.get(i));
        }
        txtUsuario.requestFocus();
        
        enter(txtUsuario, txtContrasenia);
    }

    public static ArrayList<String> llenarComboBOxRoles() {
        ArrayList<String> lista = new ArrayList<String>();
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM roles";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("denominacion"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return lista;
    }
    
    public void enter(JTextField txt1, JTextField txt2){
        txt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                txt2.requestFocus();
            }
        });
        
        txt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                notnull();
            }
        });
    }

    public static ArrayList<String> llenarComboBOxEscuela() {
        ArrayList<String> lista2 = new ArrayList<String>();
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM escuela";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista2.add(rs.getString("nombre_escuela"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return lista2;
    }

    public void limpiar() {
        txtContrasenia.setText("");
        txtUsuario.setText("");
        cbxRoles.setSelectedIndex(0);
        txtUsuario.requestFocus();
    }

    public void notnull() {
        String cargo = String.valueOf(cbxRoles.getSelectedItem());
        String user = txtUsuario.getText();
        String pass = txtContrasenia.getText();

        if (pass.equals("") || user.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingresar usuario y contraseña", "Aviso", JOptionPane.ERROR_MESSAGE);

        } else {
                //Guarda el Dato del usuario y envia al JF2
                JF2.usu=user;
                //Metodo para mostrar que formulario seria *falta
                JF2 jf= new JF2();
                jf.setVisible(true);
                //fin J
            if (cargo.equals("DOCENTE")) {
                if (mtds.buscarUsuario(user, pass, cargo)) {
                       new JF2().setVisible(true);
                       this.dispose();
                } else {
                    limpiar();
                }
            }
            if (cargo.equals("DIRECTOR DE DEPARTAMENTO")) {
                if (mtds.buscarUsuario(user, pass, cargo)) {
                    //formulario para director de escuela
                    JOptionPane.showMessageDialog(null, "INGRESASTE COMO DIRECTOR DE ESCUELA");
                    limpiar();
                } else {
                    limpiar();
                }
            }
            if (cargo.equals("COMISION-TIPIFICACIÓN")) {
                if (mtds.buscarUsuario(user, pass, cargo)) {
                    //formulario para comision-tipificacion
                    JOptionPane.showMessageDialog(null, "INGRESASTE COMO COMISION DE TIPIFICAICON");
                    limpiar();
                } else {
                    limpiar();
                }
            }
            if (cargo.equals("COMISION-RATIFICACION")) {
                if (mtds.buscarUsuario(user, pass, cargo)) {
                    //formulario para comision-ratificacion
                    JOptionPane.showMessageDialog(null, "INGRESASTE COMO COMISION DE RATIFICACION");
                    limpiar();
                } else {
                    limpiar();
                }
            }
            if (cargo.equals("DECANO")) {
                if (mtds.buscarUsuario(user, pass, cargo)) {
                    //formulario para decano
                    JOptionPane.showMessageDialog(null, "INGRESASTE COMO DECANO");
                    limpiar();
                } else {
                    limpiar();
                }
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxRoles = new javax.swing.JComboBox<>();
        txtUsuario = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtContrasenia = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jButton1.setText("REGISTRARSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addComponent(cbxRoles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContrasenia))
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(cbxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        notnull();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new JF1().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JComboBox<String> cbxRoles;
    private javax.swing.JButton jButton1;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
