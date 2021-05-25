/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.sql.*;
import javax.swing.JOptionPane;
import proyectprogra2.Conexion;

/**
 *
 * @author HP
 */
public class VALIDAR_RATIFICACION extends javax.swing.JFrame {

    public static String usuRat1;
    Conexion co = new Conexion();
    Connection con = co.conexiondb();
    public static String tipo;
    public static String capitulos;
    public static String paginas;
    public static String titulo;
    public static String escuela;
    public static String fecha;
    public static String capitulo;
    public static String pagina;

    public VALIDAR_RATIFICACION() {
        initComponents();
        datosGen();
        txttipo.setText(tipo);
        txtfecha.setText(fecha);
        txtescuela.setText(escuela);
        txttitulo.setText(titulo);
        txtcap.setText(capitulo);
        txtpag.setText(pagina);
        txttipo.setEnabled(false);
        txtcap.setEnabled(false);
        txtescuela.setEnabled(false);
        txtfecha.setEnabled(false);
        txtpag.setEnabled(false);
        txttitulo.setEnabled(false);
        tipoUsuario();
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    public void datosGen() {
        String[] registros = new String[4];
        String docente = "";
        String sql = "CALL p_usuario('" + usuRat1 + "');";
        System.err.println("USU : " + usuRat1);
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
            jLabel3.setText(" " + docente.toUpperCase());
            jLabel4.setText(" " + registros[3]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos" + e.getMessage(), "Mensaje", 0);
        }
    }

    public void modificarEst() {
        PreparedStatement ps = null;
        try {
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            ps = conn.prepareStatement("UPDATE publicaciones SET fkestado_publicacion=3 WHERE titulo=?");
            ps.setString(1, txttitulo.getText());
            ps.execute();
            JOptionPane.showMessageDialog(rootPane, "Estado Modificado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al actualizar Estado: \n" + e);
        }
    }

    public void EstadoRechazado() {
        PreparedStatement ps = null;
        try {
            Conexion objC = new Conexion();
            Connection conn = objC.conexiondb();
            ps = conn.prepareStatement("UPDATE publicaciones SET fkestado_publicacion=6 WHERE titulo=?");
            ps.setString(1, txttitulo.getText());
            ps.execute();
            JOptionPane.showMessageDialog(rootPane, "Estado Modificado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al actualizar Estado: \n" + e);
        }
    }

    public void inhabilitar() {
        txttipo.setEnabled(false);
        txtcap.setEnabled(false);
        txtescuela.setEnabled(false);
        txtfecha.setEnabled(false);
        txtpag.setEnabled(false);
        txttitulo.setEnabled(false);
        articulo21.setEnabled(false);
        articulo22.setEnabled(false);
        articulo23.setEnabled(false);
        articulo24.setEnabled(false);
        articulo25.setEnabled(false);
        articulo26.setEnabled(false);
        articulo27.setEnabled(false);
        articulo28.setEnabled(false);
        articulo29.setEnabled(false);
        articulo30.setEnabled(false);
        articulo31.setEnabled(false);
        articulo32.setEnabled(false);
        articulo33.setEnabled(false);
        jButton3.setEnabled(false);
        btnAceptar.setEnabled(false);
    }

    public void tipoUsuario() {
        switch (tipo) {
            case "LIBRO":
                articulo21.setEnabled(true);
                articulo22.setEnabled(true);
                articulo23.setEnabled(true);
                articulo24.setEnabled(true);
                articulo25.setEnabled(false);
                articulo26.setEnabled(false);
                articulo27.setEnabled(false);
                articulo28.setEnabled(false);
                articulo29.setEnabled(false);
                articulo30.setEnabled(false);
                articulo31.setEnabled(false);
                articulo32.setEnabled(false);
                articulo33.setEnabled(false);
                break;
            case "MANUAL":
                articulo21.setEnabled(false);
                articulo22.setEnabled(false);
                articulo23.setEnabled(false);
                articulo24.setEnabled(false);
                articulo25.setEnabled(true);
                articulo26.setEnabled(true);
                articulo27.setEnabled(true);
                articulo28.setEnabled(false);
                articulo29.setEnabled(false);
                articulo30.setEnabled(false);
                articulo31.setEnabled(false);
                articulo32.setEnabled(false);
                articulo33.setEnabled(false);
                break;
            case "ENSAYO":
                articulo21.setEnabled(false);
                articulo22.setEnabled(false);
                articulo23.setEnabled(false);
                articulo24.setEnabled(false);
                articulo25.setEnabled(false);
                articulo26.setEnabled(false);
                articulo27.setEnabled(false);
                articulo28.setEnabled(true);
                articulo29.setEnabled(true);
                articulo30.setEnabled(false);
                articulo31.setEnabled(false);
                articulo32.setEnabled(false);
                articulo33.setEnabled(false);
                break;
            case "GUIA":
                articulo21.setEnabled(false);
                articulo22.setEnabled(false);
                articulo23.setEnabled(false);
                articulo24.setEnabled(false);
                articulo25.setEnabled(false);
                articulo26.setEnabled(false);
                articulo27.setEnabled(false);
                articulo28.setEnabled(false);
                articulo29.setEnabled(false);
                articulo30.setEnabled(true);
                articulo31.setEnabled(true);
                articulo32.setEnabled(false);
                articulo33.setEnabled(false);
                break;
            case "FOLLETOS":
                articulo21.setEnabled(false);
                articulo22.setEnabled(false);
                articulo23.setEnabled(false);
                articulo24.setEnabled(false);
                articulo25.setEnabled(false);
                articulo26.setEnabled(false);
                articulo27.setEnabled(false);
                articulo28.setEnabled(false);
                articulo29.setEnabled(false);
                articulo30.setEnabled(false);
                articulo31.setEnabled(false);
                articulo32.setEnabled(true);
                articulo33.setEnabled(true);
                break;

            default:
                JOptionPane.showMessageDialog(rootPane, "Error en tipo de publicacion");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txttipo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcap = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtpag = new javax.swing.JTextField();
        txttitulo = new javax.swing.JTextField();
        txtescuela = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        articulo21 = new javax.swing.JCheckBox();
        articulo23 = new javax.swing.JCheckBox();
        articulo24 = new javax.swing.JCheckBox();
        articulo25 = new javax.swing.JCheckBox();
        articulo26 = new javax.swing.JCheckBox();
        articulo28 = new javax.swing.JCheckBox();
        articulo29 = new javax.swing.JCheckBox();
        articulo30 = new javax.swing.JCheckBox();
        articulo31 = new javax.swing.JCheckBox();
        articulo32 = new javax.swing.JCheckBox();
        articulo33 = new javax.swing.JCheckBox();
        articulo27 = new javax.swing.JCheckBox();
        articulo22 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 221, 193));
        jLabel1.setText("NOMBRE:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 221, 193));
        jLabel2.setText("TIPO DE USUARIO:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("jLabel3");

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOGOUT");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 11, 1030, 70));

        jPanel2.setBackground(new java.awt.Color(244, 252, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)), "PUBLICACIÓN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(55, 221, 193))); // NOI18N

        txttipo.setBackground(new java.awt.Color(244, 252, 250));

        jLabel10.setBackground(new java.awt.Color(244, 252, 250));
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setText("TIPO:");

        jLabel6.setBackground(new java.awt.Color(244, 252, 250));
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("N° CAPITULOS:");

        txtcap.setBackground(new java.awt.Color(244, 252, 250));

        jLabel11.setBackground(new java.awt.Color(244, 252, 250));
        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setText("TITULO:");

        jLabel12.setBackground(new java.awt.Color(244, 252, 250));
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setText("ESCUELA:");

        jLabel13.setBackground(new java.awt.Color(244, 252, 250));
        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setText("N° PAGINAS:");

        jLabel14.setBackground(new java.awt.Color(244, 252, 250));
        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setText("FECHA:");

        txtpag.setBackground(new java.awt.Color(244, 252, 250));

        txttitulo.setBackground(new java.awt.Color(244, 252, 250));

        txtescuela.setBackground(new java.awt.Color(244, 252, 250));

        txtfecha.setBackground(new java.awt.Color(244, 252, 250));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtcap, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(txtpag, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtescuela, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txttipo)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtcap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(txtescuela))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtpag)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(txtfecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 93, 1040, 160));

        jPanel3.setBackground(new java.awt.Color(244, 252, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)), "VERIFICACION DE REQUERIMIENTOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(55, 221, 193))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(244, 252, 250));
        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("ARTICULO 21:");

        jLabel8.setBackground(new java.awt.Color(244, 252, 250));
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("ARTICULO 22:");

        jLabel9.setBackground(new java.awt.Color(244, 252, 250));
        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setText("ARTICULO 23:");

        jLabel15.setBackground(new java.awt.Color(244, 252, 250));
        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setText("ARTICULO 24:");

        jLabel16.setBackground(new java.awt.Color(244, 252, 250));
        jLabel16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel16.setText("ARTICULO 25:");

        jLabel17.setBackground(new java.awt.Color(244, 252, 250));
        jLabel17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel17.setText("ARTICULO 26:");

        jLabel30.setBackground(new java.awt.Color(244, 252, 250));
        jLabel30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel30.setText("ARTICULO 28:");

        jLabel31.setBackground(new java.awt.Color(244, 252, 250));
        jLabel31.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel31.setText("ARTICULO 29:");

        jLabel32.setBackground(new java.awt.Color(244, 252, 250));
        jLabel32.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel32.setText("ARTICULO 30:");

        jLabel33.setBackground(new java.awt.Color(244, 252, 250));
        jLabel33.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel33.setText("ARTICULO 31:");

        jLabel34.setBackground(new java.awt.Color(244, 252, 250));
        jLabel34.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel34.setText("ARTICULO 32:");

        jLabel35.setBackground(new java.awt.Color(244, 252, 250));
        jLabel35.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel35.setText("ARTICULO 33:");

        jLabel36.setBackground(new java.awt.Color(244, 252, 250));
        jLabel36.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel36.setText("ARTICULO 27:");

        jButton2.setBackground(new java.awt.Color(55, 221, 193));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CANCELAR");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(55, 221, 193));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("RECHAZAR");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(55, 221, 193));
        btnAceptar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(55, 221, 193));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("ARTICULOS");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        articulo21.setBackground(new java.awt.Color(244, 252, 250));

        articulo23.setBackground(new java.awt.Color(244, 252, 250));

        articulo24.setBackground(new java.awt.Color(244, 252, 250));

        articulo25.setBackground(new java.awt.Color(244, 252, 250));

        articulo26.setBackground(new java.awt.Color(244, 252, 250));

        articulo28.setBackground(new java.awt.Color(244, 252, 250));

        articulo29.setBackground(new java.awt.Color(244, 252, 250));

        articulo30.setBackground(new java.awt.Color(244, 252, 250));

        articulo31.setBackground(new java.awt.Color(244, 252, 250));

        articulo32.setBackground(new java.awt.Color(244, 252, 250));

        articulo33.setBackground(new java.awt.Color(244, 252, 250));

        articulo27.setBackground(new java.awt.Color(244, 252, 250));

        articulo22.setBackground(new java.awt.Color(244, 252, 250));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(articulo23, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(articulo21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(articulo22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(articulo25, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(articulo24, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(articulo26, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(articulo27, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                    .addComponent(articulo28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(articulo29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(87, 87, 87)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel35))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(articulo31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(articulo32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(articulo33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(articulo30, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(articulo21)
                            .addComponent(articulo24)
                            .addComponent(articulo27)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel32))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(articulo22)
                            .addComponent(articulo25)
                            .addComponent(articulo28)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel33))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(articulo23)
                                    .addComponent(articulo26)
                                    .addComponent(articulo29)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel31)
                                            .addComponent(jLabel34))))
                                .addGap(30, 30, 30)
                                .addComponent(jLabel35))
                            .addComponent(jLabel17)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(articulo33))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(articulo30)
                        .addGap(27, 27, 27)
                        .addComponent(articulo31)
                        .addGap(30, 30, 30)
                        .addComponent(articulo32)))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 1040, 310));

        jLabel18.setBackground(new java.awt.Color(244, 252, 250));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/slider.png"))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ARTICULOS.usu6 = usuRat1;
        new ARTICULOS().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (articulo21.isSelected() && articulo22.isSelected() && articulo23.isSelected() && articulo24.isSelected()) {
            JOptionPane.showMessageDialog(null, "Requerimientos cumplidos para libro!!");
            modificarEst();
            inhabilitar();
        }
        if (articulo25.isSelected() && articulo26.isSelected() && articulo27.isSelected()) {
            JOptionPane.showMessageDialog(null, "Requerimientos cumplidos para manual!!");
            modificarEst();
            inhabilitar();
        }
        if (articulo28.isSelected() && articulo29.isSelected()) {
            JOptionPane.showMessageDialog(null, "Requerimientos cumplidos para ensayo!!");
            modificarEst();
            inhabilitar();
        }
        if (articulo30.isSelected() && articulo31.isSelected()) {
            JOptionPane.showMessageDialog(null, "Requerimientos cumplidos para guias!!");
            modificarEst();
            inhabilitar();
        }
        if (articulo32.isSelected() && articulo33.isSelected()) {
            JOptionPane.showMessageDialog(null, "Requerimientos cumplidos para folletos!!");
            modificarEst();
            inhabilitar();
        }


    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        EstadoRechazado();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LISTA_RATIFICACION.usuRat = usuRat1;
        new LISTA_RATIFICACION().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Login.LOGIN().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VALIDAR_RATIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VALIDAR_RATIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VALIDAR_RATIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VALIDAR_RATIFICACION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VALIDAR_RATIFICACION().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox articulo21;
    private javax.swing.JCheckBox articulo22;
    private javax.swing.JCheckBox articulo23;
    private javax.swing.JCheckBox articulo24;
    private javax.swing.JCheckBox articulo25;
    private javax.swing.JCheckBox articulo26;
    private javax.swing.JCheckBox articulo27;
    private javax.swing.JCheckBox articulo28;
    private javax.swing.JCheckBox articulo29;
    private javax.swing.JCheckBox articulo30;
    private javax.swing.JCheckBox articulo31;
    private javax.swing.JCheckBox articulo32;
    private javax.swing.JCheckBox articulo33;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtcap;
    private javax.swing.JTextField txtescuela;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtpag;
    private javax.swing.JTextField txttipo;
    private javax.swing.JTextField txttitulo;
    // End of variables declaration//GEN-END:variables
}
