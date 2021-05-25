/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Login.LOGIN;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import proyectprogra2.Conexion;

/**
 *
 * @author HP
 */
public class MENU extends javax.swing.JFrame {

    Conexion co = new Conexion();
    Connection con = co.conexiondb();
    String tipUser = "";
    //recibe el dato del Login
    public static String usu;

    /**
     * Creates new form JF2
     */
    //Eliminar Forms
    /*
            JF7
            JF10
            JF13
            JF16
     */
    public MENU() {
        initComponents();
        datosGen();
        tipoUsuario();
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    public void datosGen() {
        String[] registros = new String[4];
        String docente = "";
        String sql = "CALL p_usuario('" + usu + "');";
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
            tipUser = registros[3];
            lblNom.setText(" " + docente.toUpperCase());
            lblTipUsu.setText(" " + registros[3]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos" + e.getMessage(), "Mensaje", 0);
        }
    }

    public void tipoUsuario() {
        switch (tipUser) {
            case "DOCENTE":
                btnNewPub.setVisible(true);
                btnPub.setVisible(true);
                btnInfArt.setVisible(true);
                btnValTip.setVisible(false);
                btnValRat.setVisible(false);
                btnValDir.setVisible(false);
                btnValDec.setVisible(false);
                break;
            case "COMISION-TIPIFICACIÓN":
                btnNewPub.setVisible(false);
                btnPub.setVisible(false);
                btnInfArt.setVisible(false);
                //Activo
                btnValTip.setVisible(true);
                btnValRat.setVisible(false);
                btnValDir.setVisible(false);
                btnValDec.setVisible(false);
                break;
            case "COMISION-RATIFICACION":
                btnNewPub.setVisible(false);
                btnPub.setVisible(false);
                btnInfArt.setVisible(false);
                btnValTip.setVisible(false);
                //Activo
                btnValRat.setVisible(true);
                btnValDir.setVisible(false);
                btnValDec.setVisible(false);
                break;
            case "DIRECTOR DE DEPARTAMENTO":
                btnNewPub.setVisible(false);
                btnPub.setVisible(false);
                btnInfArt.setVisible(false);
                btnValTip.setVisible(false);
                btnValRat.setVisible(false);
                //Activo
                btnValDir.setVisible(true);
                btnValDec.setVisible(false);
                break;
            case "DECANO":
                btnNewPub.setVisible(false);
                btnPub.setVisible(false);
                btnInfArt.setVisible(false);
                btnValTip.setVisible(false);
                btnValRat.setVisible(false);
                btnValDir.setVisible(false);
                //Activo
                btnValDec.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(rootPane, "Error en tipo de usuario", "Mensaje", 0);
                System.out.println(usu);
        }
    }
    
    public void reporte(){
        try {
            Conexion conn = new Conexion();
            Connection con = conn.conexiondb();
            JasperReport reporte = null;
            String path = "src\\Reportes\\listaDocentes.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null,con);
            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
        } catch (Exception e) {
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
        btnNewPub = new javax.swing.JButton();
        btnPub = new javax.swing.JButton();
        btnInfArt = new javax.swing.JButton();
        btnPro = new javax.swing.JButton();
        btnValTip = new javax.swing.JButton();
        btnValRat = new javax.swing.JButton();
        btnValDir = new javax.swing.JButton();
        btnValDec = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 252, 250));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 221, 193));
        jLabel1.setText("NOMBRE:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 221, 193));
        jLabel2.setText("TIPO DE USUARIO:");

        lblNom.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNom.setText("jLabel3");

        btnExit.setBackground(new java.awt.Color(255, 102, 102));
        btnExit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("LOGOUT");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTipUsu.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTipUsu.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblTipUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 640, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(lblNom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipUsu)
                            .addComponent(jLabel2))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1050, 70));

        jPanel2.setBackground(new java.awt.Color(244, 252, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)), "MENU PRINCIPAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(55, 221, 193))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNewPub.setBackground(new java.awt.Color(255, 102, 102));
        btnNewPub.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnNewPub.setText("Nueva Publicación");
        btnNewPub.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPubActionPerformed(evt);
            }
        });
        jPanel2.add(btnNewPub, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 270, 80));

        btnPub.setBackground(new java.awt.Color(153, 255, 153));
        btnPub.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnPub.setText("Publicaciones");
        btnPub.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPubActionPerformed(evt);
            }
        });
        jPanel2.add(btnPub, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 270, 70));

        btnInfArt.setBackground(new java.awt.Color(102, 102, 255));
        btnInfArt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnInfArt.setText("Inf. Artículos");
        btnInfArt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInfArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfArtActionPerformed(evt);
            }
        });
        jPanel2.add(btnInfArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 280, 70));

        btnPro.setBackground(new java.awt.Color(255, 255, 102));
        btnPro.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnPro.setText("Perfil");
        btnPro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProActionPerformed(evt);
            }
        });
        jPanel2.add(btnPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 280, 80));

        btnValTip.setBackground(new java.awt.Color(204, 102, 255));
        btnValTip.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnValTip.setText("Validar");
        btnValTip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnValTip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValTipActionPerformed(evt);
            }
        });
        jPanel2.add(btnValTip, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 280, 80));

        btnValRat.setBackground(new java.awt.Color(255, 102, 102));
        btnValRat.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnValRat.setText("Validar");
        btnValRat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValRatActionPerformed(evt);
            }
        });
        jPanel2.add(btnValRat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 290, 80));

        btnValDir.setBackground(new java.awt.Color(0, 204, 255));
        btnValDir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnValDir.setText("Validar");
        btnValDir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnValDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValDirActionPerformed(evt);
            }
        });
        jPanel2.add(btnValDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 300, 80));

        btnValDec.setBackground(new java.awt.Color(255, 153, 102));
        btnValDec.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnValDec.setText("Validar");
        btnValDec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnValDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValDecActionPerformed(evt);
            }
        });
        jPanel2.add(btnValDec, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 310, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 109, 1050, 470));

        jLabel10.setBackground(new java.awt.Color(244, 252, 250));
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 252, 250), 300));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        new LOGIN().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNewPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPubActionPerformed
        NUEVA_PUBLICACION.usu4 = usu;
        NUEVA_PUBLICACION jf4 = new NUEVA_PUBLICACION();
        jf4.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnNewPubActionPerformed

    private void btnPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPubActionPerformed
        PUBLICACIONES_DOCENTE.usu5 = usu;
        PUBLICACIONES_DOCENTE jf5 = new PUBLICACIONES_DOCENTE();
        jf5.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnPubActionPerformed

    private void btnInfArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfArtActionPerformed
        ARTICULOS.usu6 = usu;
        ARTICULOS jf6 = new ARTICULOS();
        jf6.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnInfArtActionPerformed

    private void btnProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProActionPerformed
        EDITAR_PERFIL.usu3 = usu;
        EDITAR_PERFIL jf3 = new EDITAR_PERFIL();
        jf3.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnProActionPerformed

    private void btnValTipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValTipActionPerformed
        LISTA_TIPIFICACION.usuTip = usu;
        this.setVisible(false);
        new LISTA_TIPIFICACION().setVisible(true);
    }//GEN-LAST:event_btnValTipActionPerformed

    private void btnValRatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValRatActionPerformed
        LISTA_RATIFICACION.usuRat = usu;
        this.setVisible(false);
        new LISTA_RATIFICACION().setVisible(true);
    }//GEN-LAST:event_btnValRatActionPerformed

    private void btnValDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValDirActionPerformed
        LISTA_DIRECTOR.usuDir = usu;
        this.setVisible(false);
        new LISTA_DIRECTOR().setVisible(true);
    }//GEN-LAST:event_btnValDirActionPerformed

    private void btnValDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValDecActionPerformed
        LISTA_DECANO.usuDec = usu;
        this.setVisible(false);
        new LISTA_DECANO().setVisible(true);
    }//GEN-LAST:event_btnValDecActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reporte();
        
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
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new MENU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInfArt;
    private javax.swing.JButton btnNewPub;
    private javax.swing.JButton btnPro;
    private javax.swing.JButton btnPub;
    private javax.swing.JButton btnValDec;
    private javax.swing.JButton btnValDir;
    private javax.swing.JButton btnValRat;
    private javax.swing.JButton btnValTip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblTipUsu;
    // End of variables declaration//GEN-END:variables
}
