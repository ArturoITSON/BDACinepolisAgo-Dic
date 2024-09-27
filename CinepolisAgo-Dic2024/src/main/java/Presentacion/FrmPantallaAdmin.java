/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Negocio.ClienteNegocio;
import Negocio.IClasificacionNegocio;
import Negocio.IClienteNegocio;
import Negocio.IFuncionNegocio;
import Negocio.IGeneroNegocio;
import Negocio.IPaisNegocio;
import Negocio.IPeliculaNegocio;
import Negocio.ISalaNegocio;
import Negocio.PeliculaNegocio;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Arturo ITSON
 */
public class FrmPantallaAdmin extends javax.swing.JFrame {

    
    private String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";
    IPeliculaNegocio PeliculaNegocio;
    
    FrmIniciarSesion iniciarSesion;
    IClienteNegocio clienteNegocio;
    IPeliculaNegocio peliculaNegocio;
    IGeneroNegocio generoNegocio;
    IClasificacionNegocio clasificacionNegocio;
    IPaisNegocio paisNegocio;
    IFuncionNegocio funcionNegocio;
    ISalaNegocio salaNegocio;
    
    /**
     * Creates new form FrmPantallaAdmin
     * @param iniciarSesion
     * @param peliculaNegocio
     * @param generoNegocio
     * @param clasificacionNegocio
     */
    public FrmPantallaAdmin(FrmIniciarSesion iniciarSesion, IPeliculaNegocio peliculaNegocio, IClienteNegocio clienteNegocio, IGeneroNegocio generoNegocio, IClasificacionNegocio clasificacionNegocio, IPaisNegocio paisNegocio,
                            IFuncionNegocio funcionNegocio, ISalaNegocio salaNegocio) {
        initComponents();
        
        this.iniciarSesion = iniciarSesion;
        this.PeliculaNegocio = peliculaNegocio;
        this.generoNegocio = generoNegocio;
        this.clasificacionNegocio = clasificacionNegocio;
        this.paisNegocio=paisNegocio;
        this.funcionNegocio = funcionNegocio;
        this.salaNegocio = salaNegocio;
        
        setImagenLabel(jblCinepolisLogo, rutaCinepolisLogo);
        this.peliculaNegocio = peliculaNegocio;
        this.clienteNegocio=clienteNegocio;
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
        btnModificarPelicula = new javax.swing.JButton();
        btnModificarFuncion = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnModificarCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pantalla Administrador");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnModificarPelicula.setBackground(new java.awt.Color(8, 148, 249));
        btnModificarPelicula.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnModificarPelicula.setText("Modificar Pelicula");
        btnModificarPelicula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPeliculaActionPerformed(evt);
            }
        });

        btnModificarFuncion.setBackground(new java.awt.Color(8, 148, 249));
        btnModificarFuncion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnModificarFuncion.setText("Modificar Funcion");
        btnModificarFuncion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarFuncionActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnReportes.setBackground(new java.awt.Color(8, 148, 249));
        btnReportes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnReportes.setText("Reportes");
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnModificarCliente.setBackground(new java.awt.Color(8, 148, 249));
        btnModificarCliente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnModificarCliente.setText("Modificar Cliente");
        btnModificarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificarFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPeliculaActionPerformed
        // TODO add your handling code here:
        FrmModificarPelicula modificarPelicula = new FrmModificarPelicula(this, PeliculaNegocio, clienteNegocio, generoNegocio, clasificacionNegocio, paisNegocio);

        modificarPelicula.setVisible(true);

        this.setVisible(false);
    }//GEN-LAST:event_btnModificarPeliculaActionPerformed

    private void btnModificarFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarFuncionActionPerformed
        // TODO add your handling code here:
        FrmModificarFuncion modificarFuncion = new FrmModificarFuncion(this, funcionNegocio, peliculaNegocio, salaNegocio);

        modificarFuncion.setVisible(true);

        this.setVisible(false);

    }//GEN-LAST:event_btnModificarFuncionActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:);

        iniciarSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        // TODO add your handling code here:
        FrmEditarCliente editarCliente = new FrmEditarCliente(this, clienteNegocio);

        editarCliente.setVisible(true);

        this.setVisible(false);
    }//GEN-LAST:event_btnModificarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnModificarFuncion;
    private javax.swing.JButton btnModificarPelicula;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jblCinepolisLogo;
    // End of variables declaration//GEN-END:variables
}
