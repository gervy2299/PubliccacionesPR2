/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Login.LOGIN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import proyectprogra2.Conexion;

/**
 *
 * @author HP
 */
public class EDITAR_PERFIL extends javax.swing.JFrame {

    public static String usu3;
    Conexion co = new Conexion();
    Connection con = co.conexiondb();

    /**
     * Creates new form JF3
     */
    public EDITAR_PERFIL() {
        initComponents();
        datosGen();
        cargarDatos();
        ocultar(false);
    }

    public void ocultar(boolean estado) {
        txtjfDni.setEnabled(estado);
        txtNom.setEnabled(estado);
        txtApePat.setEnabled(estado);
        txtApeMat.setEnabled(estado);
        txtjfFecNac.setEnabled(estado);
        txtDir.setEnabled(estado);
        txtjfTel.setEnabled(estado);
        txtUsu.setEnabled(estado);
        txtCon.setEnabled(estado);
        txtCon1.setEnabled(estado);
    }

    public void datosGen() {
        String[] registros = new String[4];
        String docente = "";
        String sql = "CALL p_usuario('" + usu3 + "');";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("d.nombres");
                registros[1] = rs.getString("d.apellido_pat");
                registros[2] = rs.getString("d.apellido_mat");
                registros[3] = rs.getString("r.denominacion");
            }
            docente = registros[1] + " " + registros[2] + " " + registros[0];
            lblNom.setText(" " + docente.toUpperCase());
            lblTipUsu.setText(" " + registros[3]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos" + e.getMessage(), "Mensaje", 0);
        }
    }

    public void comboEstado() {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM roles";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cmbCar.addItem(rs.getString("denominacion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar datos: \n" + e);
        }
    }

    public void cargarDatos() {
        String[] registros = new String[10];
        String sql = "CALL p_docente('" + usu3 + "');";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("d.DNI");
                registros[1] = rs.getString("d.nombres");
                registros[2] = rs.getString("d.apellido_pat");
                registros[3] = rs.getString("d.apellido_mat");
                registros[4] = rs.getString("d.fecha_nacimiento");
                registros[5] = rs.getString("d.domicilio");
                registros[6] = rs.getString("d.telefono");
                registros[7] = rs.getString("d.usuario");
                registros[8] = rs.getString("d.contrasenia");
                registros[9] = rs.getString("d.fk_idroles");
            }
            txtjfDni.setText(registros[0]);
            txtNom.setText(registros[1]);
            txtApePat.setText(registros[2]);
            txtApeMat.setText(registros[3]);
            txtjfFecNac.setText(registros[4]);
            txtDir.setText(registros[5]);
            txtjfTel.setText(registros[6]);
            txtUsu.setText(registros[7]);
            txtCon.setText(registros[8]);
            txtCon1.setText(registros[8]);
            comboEstado();
            cmbCar.setSelectedIndex(Integer.parseInt(registros[9]) - 1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos" + e.getMessage(), "Mensaje", 0);
        }
    }

    public void modificarInfo() {
        PreparedStatement ps = null;
        try {
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            ps = conn.prepareStatement("UPDATE docentes SET DNI=?,nombres=?,apellido_pat=?,apellido_mat=?,"
                    + "fecha_nacimiento=?,domicilio=?,telefono=?,usuario=?,contrasenia=?"
                    + " WHERE usuario='" + usu3 + "';");
            ps.setString(1, txtjfDni.getText());
            ps.setString(2, txtNom.getText());
            ps.setString(3, txtApePat.getText());
            ps.setString(4, txtApeMat.getText());
            ps.setString(5, txtjfFecNac.getText());
            ps.setString(6, txtDir.getText());
            ps.setString(7, txtjfTel.getText());
            ps.setString(8, txtUsu.getText());
            ps.setString(9, txtCon.getText());
            ps.execute();
            JOptionPane.showMessageDialog(rootPane, "Información Actualizada");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al actualizar Información: \n" + e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLog = new javax.swing.JButton();
        lblTipUsu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtjfDni = new javax.swing.JFormattedTextField();
        txtNom = new javax.swing.JTextField();
        txtApePat = new javax.swing.JTextField();
        txtApeMat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtjfFecNac = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtjfTel = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtUsu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        cmbCar = new javax.swing.JComboBox<>();
        txtCon = new javax.swing.JTextField();
        txtCon1 = new javax.swing.JTextField();
        lblAdv = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("NOMBRE:");

        jLabel2.setText("TIPO DE USUARIO:");

        lblNom.setText("jLabel3");

        jLabel5.setText("DOCENTE");

        btnLog.setText("LOGOUT");
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });

        lblTipUsu.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblTipUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTipUsu)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLog)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("DNI:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 25, -1, -1));

        jLabel7.setText("NOMBRES:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 62, -1, -1));

        jLabel8.setText("APELLIDO PATERNO:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 97, -1, -1));

        jLabel9.setText("APELLIDO MATERNO:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 132, -1, -1));

        jLabel10.setText("CARGO:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 223, -1, -1));

        try {
            txtjfDni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel2.add(txtjfDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 22, 160, -1));
        jPanel2.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 59, 160, -1));
        jPanel2.add(txtApePat, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 94, 160, -1));
        jPanel2.add(txtApeMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 129, 160, -1));

        jLabel11.setText("DIRECCION:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 20, -1, -1));
        jPanel2.add(txtDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 20, 170, -1));

        jLabel13.setText("FECHA DE NACIMIENTO:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 170, -1, -1));

        txtjfFecNac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jPanel2.add(txtjfFecNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 167, 160, -1));

        jLabel14.setText("TELEFONO:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 59, -1, -1));

        try {
            txtjfTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel2.add(txtjfTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 59, 170, -1));

        jLabel15.setText("USUARIO:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 96, -1, -1));
        jPanel2.add(txtUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 96, 170, -1));

        jLabel16.setText("CONTRASEÑA:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 139, -1, -1));

        jLabel17.setText("REPETIR CONTRASEÑA:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 179, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 327, 111, 32));

        jButton3.setText("GUARDAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 327, 110, 32));

        btnMenu.setText("MENU PRINCIPAL");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        jPanel2.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 328, 142, 30));

        cmbCar.setEditable(true);
        cmbCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCarActionPerformed(evt);
            }
        });
        jPanel2.add(cmbCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 215, 280, -1));
        jPanel2.add(txtCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 136, 170, -1));
        jPanel2.add(txtCon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 176, 170, -1));

        lblAdv.setForeground(new java.awt.Color(204, 0, 0));
        lblAdv.setText("*");
        jPanel2.add(lblAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 219, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        MENU.usu = usu3;
        MENU jf2 = new MENU();
        jf2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        LOGIN lg = new LOGIN();
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogActionPerformed

    private void cmbCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCarActionPerformed
        lblAdv.setText("Al cambiar de rango, perderá sus privilegios ");
    }//GEN-LAST:event_cmbCarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modificarInfo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ocultar(true);
    }//GEN-LAST:event_btnModificarActionPerformed

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
            java.util.logging.Logger.getLogger(EDITAR_PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EDITAR_PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EDITAR_PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EDITAR_PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EDITAR_PERFIL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbCar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAdv;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblTipUsu;
    private javax.swing.JTextField txtApeMat;
    private javax.swing.JTextField txtApePat;
    private javax.swing.JTextField txtCon;
    private javax.swing.JTextField txtCon1;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtUsu;
    private javax.swing.JFormattedTextField txtjfDni;
    private javax.swing.JFormattedTextField txtjfFecNac;
    private javax.swing.JFormattedTextField txtjfTel;
    // End of variables declaration//GEN-END:variables
}
