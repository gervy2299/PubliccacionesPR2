/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Login.LOGIN;
import java.awt.HeadlessException;
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
public class NUEVA_PUBLICACION extends javax.swing.JFrame {

    Conexion co = new Conexion();
    Connection con = co.conexiondb();
    public static String usu4;

    /**
     * Creates new form JF4
     */
    
    public NUEVA_PUBLICACION() {
        initComponents();
        datosGen();
        comboTipPub();
        comboEscuela();
        this.setLocationRelativeTo(null);
    }

    public void datosGen() {
        String[] registros = new String[4];
        String docente = "";
        String sql = "CALL p_usuario('" + usu4 + "');";
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

    public void comboTipPub() {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM tipo_publicacion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cmbTipPub.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar datos: \n" + e);
        }
    }

    public void comboEscuela() {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "SELECT * FROM escuela";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cmbEsc.addItem(rs.getString("nombre_escuela"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar datos: \n" + e);
        }
    }

    public void camposActivos() {
        String tipPub = cmbTipPub.getSelectedItem().toString();
        switch (tipPub) {
            case "LIBRO":
                txtCantCap.setEnabled(true);
                txtCantPag.setEnabled(true);
                txtCantCap.setText("");
                txtCantPag.setText("");
                break;
            case "MANUAL":
                txtCantCap.setEnabled(false);
                txtCantCap.setText("0");
                txtCantPag.setEnabled(true);
                txtCantPag.setText("");
                break;
            case "ENSAYO":
                txtCantCap.setEnabled(false);
                txtCantCap.setText("0");
                txtCantPag.setText("");
                txtCantPag.setEnabled(true);
                break;
            case "GUIAS":
                txtCantCap.setEnabled(false);
                txtCantCap.setText("0");
                txtCantPag.setText("");
                txtCantPag.setEnabled(true);
                break;
            case "FOLLETOS":
                txtCantCap.setEnabled(false);
                txtCantCap.setText("0");
                txtCantPag.setEnabled(false);
                txtCantPag.setText("2");
                break;
            default:
                JOptionPane.showMessageDialog(rootPane, "Tipo de Usuario no existe");
                break;
        }
    }

    public String idAutor() {
        String id = "";
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            String sql = "CALL p_idautor('" + usu4 + "');";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("idautores");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al cargar idAutor: \n" + e);
        }
        return id;
    }

    public void NoModDat() {
        txtNomPub.setEnabled(false);
        txtFec.setEnabled(false);
        cmbEsc.setEnabled(false);
        cmbTipPub.setEnabled(false);
        txtCantCap.setEnabled(false);
        txtCantPag.setEnabled(false);
    }

    public void insertarPublicacion() {
        PreparedStatement ps = null;
        try {
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            ps = conn.prepareStatement("INSERT INTO publicaciones (titulo,cant_paginas,fecha_publicacion,numero_capitulos,fk_idautores,fk_idescuela,fkidtipo_publicacion,fkestado_publicacion) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, txtNomPub.getText());
            ps.setString(2, txtCantPag.getText());
            ps.setString(3, txtFec.getText());
            ps.setString(4, txtCantCap.getText());
            //Llamar el metodo id del autor
            ps.setString(5, idAutor());
            ps.setString(6, String.valueOf(cmbEsc.getSelectedIndex() + 1));
            ps.setString(7, String.valueOf(cmbTipPub.getSelectedIndex() + 1));
            ps.setString(8, "1");
            ps.execute();
            NoModDat();
            JOptionPane.showMessageDialog(rootPane, "Publicacion Enviado con éxito", "Informacion", 1);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al guardar Autor: \n" + e);
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
        btnExit = new javax.swing.JButton();
        lblTipUsu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbTipPub = new javax.swing.JComboBox<>();
        txtNomPub = new javax.swing.JTextField();
        txtCantPag = new javax.swing.JTextField();
        txtCantCap = new javax.swing.JTextField();
        txtFec = new javax.swing.JFormattedTextField();
        cmbEsc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 0));
        jLabel1.setText("NOMBRE:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 0));
        jLabel2.setText("TIPO DE USUARIO:");

        lblNom.setText("jLabel3");

        btnExit.setBackground(new java.awt.Color(255, 117, 109));
        btnExit.setText("LOGOUT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTipUsu.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblTipUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 460, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipUsu)
                    .addComponent(jLabel2))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 11, 850, 70));

        jPanel2.setBackground(new java.awt.Color(254, 188, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVA PUBLICACIÖN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("TIPO DE PUBLICACION:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jLabel7.setText("NOMBRE DE LA PUBLICACION:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        jLabel8.setText("CANTIDAD DE PAGINAS:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jLabel9.setText("FECHA DE PUBLICACION:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jLabel10.setText("ESCUELA PROFESIONAL:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 130, -1));

        jLabel11.setText("CANTIDAD DE CAPITULOS:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        cmbTipPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipPubActionPerformed(evt);
            }
        });
        jPanel2.add(cmbTipPub, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 240, 30));
        jPanel2.add(txtNomPub, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 430, 30));
        jPanel2.add(txtCantPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 130, 30));
        jPanel2.add(txtCantCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 130, 30));

        txtFec.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jPanel2.add(txtFec, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 130, 30));

        jPanel2.add(cmbEsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 240, 30));

        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("yyyy-MM-dd");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 850, 330));

        btnMenu.setText("MENU PRINCIPAL");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        getContentPane().add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 190, 40));

        btnEnviar.setText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 190, 40));

        jLabel12.setBackground(new java.awt.Color(184, 236, 166));
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 188, 200), 300));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        MENU.usu = usu4;
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void cmbTipPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipPubActionPerformed
        camposActivos();
    }//GEN-LAST:event_cmbTipPubActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        if (!txtNomPub.getText().equals("")) {
            if (!txtCantPag.getText().equals("")) {
                if (!txtCantCap.getText().equals("")) {
                    if (!txtFec.getText().equals("")) {
                        NoModDat();
                        insertarPublicacion();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Faltan Fecha", "Mensajo de advertencia", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Faltan Cantidad de Capitulos", "Mensajo de advertencia", 0);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Falta Cantidad de Paginas", "Mensajo de advertencia", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Faltan Nombre de la publicacion", "Mensajo de advertencia", 0);
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        LOGIN lg=new LOGIN();
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(NUEVA_PUBLICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NUEVA_PUBLICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NUEVA_PUBLICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NUEVA_PUBLICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new NUEVA_PUBLICACION().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMenu;
    private javax.swing.JComboBox<String> cmbEsc;
    private javax.swing.JComboBox<String> cmbTipPub;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblTipUsu;
    private javax.swing.JTextField txtCantCap;
    private javax.swing.JTextField txtCantPag;
    private javax.swing.JFormattedTextField txtFec;
    private javax.swing.JTextField txtNomPub;
    // End of variables declaration//GEN-END:variables
}
