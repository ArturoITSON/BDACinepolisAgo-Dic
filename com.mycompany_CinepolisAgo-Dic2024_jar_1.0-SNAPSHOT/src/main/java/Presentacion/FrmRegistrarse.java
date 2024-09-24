/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.ClienteGuardarDTO;
import Negocio.ClienteNegocio;
import Negocio.IClienteNegocio;
import Negocio.NegocioException;
import Persistencia.PersistenciaException;
import java.awt.Image;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo ITSON
 */
public class FrmRegistrarse extends javax.swing.JFrame {

    
    private String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";
    FrmInicio inicio;
    IClienteNegocio clienteNegocio;
    
    
    /**
     * Creates new form frmRegistrarse
     */
    public FrmRegistrarse(FrmInicio inicio, ClienteNegocio clienteNegocio){
        initComponents();
        
        
        setImagenLabel(jblCinepolisLogo, rutaCinepolisLogo);

        
        this.inicio = inicio;
        this.clienteNegocio = clienteNegocio;
        
        
        try {
            cargarMetodosIniciales();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    private void cargarMetodosIniciales() throws NegocioException{
        cargarCiudades();
    
    }
    
     /**
     * Metodo que coloca una imagen de el logo de cinepolis en la interfaz
     * @param nombreJlb el jlabel que sera reemplazado por una imagen
     * @param ruta la direccion donde se encuentra la imagen
     */
    private void setImagenLabel(JLabel nombreJlb, String ruta){
    
        ImageIcon image = new ImageIcon(ruta);
        
        Icon icon = new ImageIcon(
       image.getImage().getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));
        
        nombreJlb.setIcon(icon);
        
        this.repaint();
   
    }
    
    
    private void cargarCiudades() throws NegocioException {
            
        try {
            List<String> ciudades = clienteNegocio.obtenerCiudades();
            for (String ciudad : ciudades) {
                cbCiudades.addItem(ciudad);
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las ciudades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jblCinepolisLogo = new javax.swing.JLabel();
        campoTextoNombre = new javax.swing.JTextField();
        campoTextoApellidoPaterno = new javax.swing.JTextField();
        cbCiudades = new javax.swing.JComboBox<>();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblNacimiento = new javax.swing.JLabel();
        lblApellidoPaterno2 = new javax.swing.JLabel();
        campoTextoApellidoMaterno = new javax.swing.JTextField();
        btnRegistrarse = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        campoTextoCorreo = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        datePickerNacimiento = new com.github.lgooddatepicker.components.DatePicker();
        pwdContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrarse");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        campoTextoNombre.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoApellidoPaterno.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoApellidoPaternoActionPerformed(evt);
            }
        });

        lblApellidoMaterno.setText("Apellido Materno");
        lblApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        lblNombre1.setText("Nombre (s)");
        lblNombre1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblCiudad.setText("Ciudad");
        lblCiudad.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        lblNacimiento.setText("Fecha de nacimiento");
        lblNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblApellidoPaterno2.setText("Apellido Paterno");
        lblApellidoPaterno2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        campoTextoApellidoMaterno.setBackground(new java.awt.Color(136, 201, 239));

        btnRegistrarse.setBackground(new java.awt.Color(8, 148, 249));
        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(240, 76, 76));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        campoTextoCorreo.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoCorreoActionPerformed(evt);
            }
        });

        lblContraseña.setText("Contraseña");
        lblContraseña.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        lblCorreo.setText("Correo Electronico");
        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoTextoCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                    .addComponent(datePickerNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCiudades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApellidoPaterno2)
                                    .addComponent(campoTextoApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoTextoApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(168, 168, 168))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pwdContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(209, 209, 209)
                                .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(lblNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(558, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoPaterno2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datePickerNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCiudades, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoTextoCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(pwdContrasena))
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(253, 253, 253)
                    .addComponent(lblNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(303, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        // TODO add your handling code here:
         
        ClienteGuardarDTO clienteGuardarDTO = new ClienteGuardarDTO();

        clienteGuardarDTO.setApelldioMaterno(campoTextoApellidoMaterno.getText());
        clienteGuardarDTO.setApellidoPaterno(campoTextoApellidoPaterno.getText());
        clienteGuardarDTO.setCiudad(cbCiudades.getSelectedIndex() + 1);
        clienteGuardarDTO.setContrasena(pwdContrasena.getText());
        clienteGuardarDTO.setCorreo(campoTextoCorreo.getText());
        clienteGuardarDTO.setNombres(campoTextoNombre.getText());
        clienteGuardarDTO.setNacimiento(Date.valueOf(datePickerNacimiento.getDate()));
        
        try {

            clienteNegocio.guardar(clienteGuardarDTO);
            JOptionPane.showMessageDialog(this, "El usuario con nombre: " + clienteGuardarDTO.getNombres() + ", ha sido registrado");
            this.dispose();
            inicio.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(FrmRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void campoTextoCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoCorreoActionPerformed

    private void campoTextoApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoApellidoPaternoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JTextField campoTextoApellidoMaterno;
    private javax.swing.JTextField campoTextoApellidoPaterno;
    private javax.swing.JTextField campoTextoCorreo;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JComboBox<String> cbCiudades;
    private com.github.lgooddatepicker.components.DatePicker datePickerNacimiento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jblCinepolisLogo;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno2;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNacimiento;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JPasswordField pwdContrasena;
    // End of variables declaration//GEN-END:variables
}
