/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.DatosCarteleraDTO;
import DTOs.FuncionDTO;
import DTOs.TicketDTO;
import DTOs.TicketGuardarDTO;
import Negocio.IFuncionNegocio;
import Negocio.IGeneroNegocio;
import Negocio.ITicketNegocio;
import Negocio.NegocioException;
import Persistencia.PersistenciaException;
import static Presentacion.FrmCartelera.nombreSala;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author eduar
 */
public class frmSeleccionarPelicula extends javax.swing.JFrame {

    FrmCartelera cartelera;
    DatosCarteleraDTO datos;
    IGeneroNegocio generoNegocio;
    IFuncionNegocio funcionNegocio;
    ITicketNegocio ticketNegocio;
    
    String rutaReloj = "src/main/java/utilerias/Imagenes/reloj.png";
    String rutaPagoLinea = "src/main/java/utilerias/Imagenes/visa.png";
    String rutaPagoEfectivo = "src/main/java/utilerias/Imagenes/efectivo.png";
    String rutaQR = "src/main/java/utilerias/Imagenes/QR.png";
    
    static int cantidad = 1;
    
    
    /**
     * Creates new form frmSeleccionarPelicula
     */
    public frmSeleccionarPelicula(FrmCartelera cartelera, DatosCarteleraDTO datos, IGeneroNegocio generoNegocio, IFuncionNegocio funcionNegocio,
                                  ITicketNegocio ticketNegocio) {
        initComponents();
        
        
        this.datos = datos;
        this.cartelera = cartelera;
        this.generoNegocio = generoNegocio;
        this.funcionNegocio = funcionNegocio;
        this.ticketNegocio = ticketNegocio;
        
        
        btnMenos.setEnabled(false);
        lblCantidad.setText(String.valueOf(cantidad));
        calcularTotal(cantidad);
        
        try {
            cargarMetodosDatos();
        } catch (NegocioException ex) {
            Logger.getLogger(frmSeleccionarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(frmSeleccionarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    private void cargarMetodosDatos() throws NegocioException, MalformedURLException{
    

       setImagen(jblImagenPelicula, getImagenPelicula());
       setImagenLabel(lblReloj, rutaReloj);
       setDescripcion();
       setTituloPelicula();
       setDuracion();
       setGenero();
       setFunciones();
       setPrecio();
        
    
    }
    
    
    
    
    
            /**
        * Metodo que coloca una imagen de el logo de reloj en la interfaz
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

        private void setImagen(JLabel nombreJlb, String ruta) throws MalformedURLException{
    
        URL url;
        url = new URL(ruta);
        
            
        ImageIcon image = new ImageIcon(url);
        
        Icon icon = new ImageIcon(
        image.getImage().getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));
        
        nombreJlb.setIcon(icon);
        
        this.repaint();
   
    }
    
    
    private String getImagenPelicula() throws NegocioException{
    
        String imagenLink = null;
        
            
        
        
        return datos.getPelicula().getLinkImagen();
    }
    
    private void setTituloPelicula() throws NegocioException{
        

        
        lblTitulo.setText(datos.getPelicula().getTitulo());
    }  
    
    private void setDescripcion() throws NegocioException{
            
        
        lblDescripcion.setText(datos.getPelicula().getSinopsis());
    }  
    
    private void setDuracion() throws NegocioException{
            
        
        lblDuracion.setText("Duracion: "  + datos.getPelicula().getDuracion() + "m");
    } 
    
    private void setGenero() throws NegocioException{
            
  
        try {
            lblGenero.setText(generoNegocio.buscarGeneroPorId(datos.getPelicula().getGenero_id()).getNombre());
        } catch (PersistenciaException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void setFunciones() throws NegocioException{
            
        
        cbcHorario.removeAllItems();
        
        cbcHorario.addItem(datos.getFuncion().getEmpezaFuncion().toString());

        
    } 
    
    private void setPrecio(){
        
        lblPrecio.setText(String.valueOf(datos.getFuncion().getPrecio()));
    }
    
    
    private void calcularTotal(int cantidadBoletos){
    
        String total = String.valueOf(cantidad * datos.getFuncion().getPrecio());
        
        lblTotal.setText(total);
    }
    
    
   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolver = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        lblFuncion = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        cbcHorario = new javax.swing.JComboBox<>();
        jblImagenPelicula = new javax.swing.JLabel();
        jblHorarios = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblPago = new javax.swing.JLabel();
        lblMetodo = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblBoleto1 = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        btnMenos = new javax.swing.JButton();
        btnMas = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jblTextoTotal = new javax.swing.JLabel();
        cbcMetodoPago = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SeleccionarPelicula");
        setBackground(new java.awt.Color(255, 255, 255));

        btnVolver.setBackground(new java.awt.Color(8, 148, 249));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnComprar.setBackground(new java.awt.Color(51, 255, 0));
        btnComprar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnComprar.setText("Comprar");
        btnComprar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        lblFuncion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDescripcion.setText("Descripcion");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("Titulo");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDuracion.setText("Duracion");

        lblGenero.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblGenero.setText("Genero");
        lblGenero.setToolTipText("");

        jblHorarios.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jblHorarios.setText("Horario");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jblImagenPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblTitulo))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jblHorarios))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(cbcHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblDuracion)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblGenero)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblDescripcion)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDuracion, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblGenero))
                            .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbcHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblHorarios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(lblDescripcion))
                    .addComponent(jblImagenPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        lblPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMetodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMetodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMetodo.setText("Método de pago");
        lblMetodo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPrecio.setText("Precio");
        lblPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblPrecio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblBoleto1.setText("Boleto");
        lblBoleto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBoleto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidad.setText("Cantidad");
        lblCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblCantidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnMenos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenos.setText("-");
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed(evt);
            }
        });

        btnMas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMas.setText("+");
        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });

        lblTotal.setText("Total");
        lblTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jblTextoTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTextoTotal.setText("Total");

        cbcMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Linea", "Efectivo" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblTextoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(316, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblMetodo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBoleto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbcMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(37, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblPago, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBoleto1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jblTextoTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblPago, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnComprar))
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:

        try {
        TicketGuardarDTO ticket = new TicketGuardarDTO();
        ticket.setCliente_id(datos.getCliente().getIdCliente());
        ticket.setFuncion_id(datos.getFuncion().getId());
        
        
        ticket.setMetodoPago((String) cbcMetodoPago.getSelectedItem());
        
        ticket.setQR(rutaQR);
        ticket.setPrecio(cantidad * datos.getFuncion().getPrecio());
        
            System.out.println(ticket.toString());
            ticketNegocio.guardar(ticket);
            JOptionPane.showMessageDialog(this, "Boleto comprado");
            
        } catch (NegocioException ex) {
            Logger.getLogger(frmSeleccionarPelicula.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "no se pudo comprar el boleto");
        }
        
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        // TODO add your handling code here:
        if (cantidad < 1){
            cantidad = 1;
            btnMenos.setEnabled(false);
            lblCantidad.setText(String.valueOf(cantidad));
            calcularTotal(cantidad);
            
        }
        else{
            cantidad--;
            lblCantidad.setText(String.valueOf(cantidad));
            calcularTotal(cantidad);
        }
        
        
        
    }//GEN-LAST:event_btnMenosActionPerformed

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        // TODO add your handling code here:
        
        cantidad++;
        lblCantidad.setText(String.valueOf(cantidad));
        btnMenos.setEnabled(true);
        calcularTotal(cantidad);
    }//GEN-LAST:event_btnMasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnMas;
    private javax.swing.JButton btnMenos;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbcHorario;
    private javax.swing.JComboBox<String> cbcMetodoPago;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jblHorarios;
    private javax.swing.JLabel jblImagenPelicula;
    private javax.swing.JLabel jblTextoTotal;
    private javax.swing.JLabel lblBoleto1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblFuncion;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblMetodo;
    private javax.swing.JLabel lblPago;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private java.awt.Panel panel2;
    // End of variables declaration//GEN-END:variables
}
