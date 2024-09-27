/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.FuncionFiltroTablaDTO;
import DTOs.FuncionTablaDTO;
import Negocio.IFuncionNegocio;
import Negocio.IPeliculaNegocio;
import Negocio.ISalaNegocio;
import Negocio.NegocioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author eduar
 */
public class FrmModificarFuncion extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 5;
    
    IFuncionNegocio funcionNegocio;
    IPeliculaNegocio peliculaNegocio;
    FrmPantallaAdmin admin;
    ISalaNegocio salaNegocio;
    

    /**
     * Creates new form FrmEditarFuncion
     */
    public FrmModificarFuncion(FrmPantallaAdmin admin, IFuncionNegocio funcionNegocio, IPeliculaNegocio peliculaNegocio, ISalaNegocio salaNegocio) {
        initComponents();
        
        this.funcionNegocio = funcionNegocio;
        this.peliculaNegocio = peliculaNegocio;
        this.admin = admin;
        this.salaNegocio = salaNegocio;
        
        this.metodosIniciales();
        
        
    }


    private void metodosIniciales() {
        this.cargarConfiguracionInicialPantalla();
        this.cargarConfiguracionInicialTablaFunciones();
        this.cargarTablaFunciones();
        
        btnPaginaAnterior.setEnabled(false);
    }
    
    
    
        private void cargarConfiguracionInicialPantalla() {
      //  this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    
    
   private void cargarConfiguracionInicialTablaFunciones() {
       
       TableColumnModel modeloColumnas = this.tblFunciones.getColumnModel();
        ActionListener onEliminarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar
                eliminar();
            }
        };
        int indiceColumnaEliminar = 7;
        modeloColumnas = this.tblFunciones.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private int getIdSeleccionadoTablaFunciones() {
        int indiceFilaSeleccionada = this.tblFunciones.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblFunciones.getModel();
            int indiceColumnaId = 0;
            int idFuncionSeleccionada = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idFuncionSeleccionada;
        } else {
            return 0;
        }
    }

    

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaFunciones();
        
        System.out.println("El id para eliminar es " + id);
        try{
        funcionNegocio.eliminar(id);
        JOptionPane.showMessageDialog(this, "funcion con id " + id + " ha sido eliminado");
        }
        
        catch(NegocioException ex){
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la funcion");
        }
        finally{
            this.cargarTablaFunciones();
        }
        
        
        
        this.cargarTablaFunciones();
    }

    private void BorrarRegistrosTablaPeliculas() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblFunciones.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--) {
                modeloTabla.removeRow(row);
            }
        }
    }

    private void AgregarRegistrosTablaCliente(List<FuncionTablaDTO> clientesLista) {
        if (clientesLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblFunciones.getModel();
        clientesLista.forEach(row -> {
            Object[] fila = new Object[7];
            fila[0] = row.getId();
            fila[1] = row.getPelicula_id();
            fila[2] = row.getEmpezaFuncion().toString();
            fila[3] = row.getTerminoFuncion().toString();
            fila[4] = row.getPrecio();
            fila[5] = row.getSala_id();
            fila[6] = row.getDiaFuncion();

            modeloTabla.addRow(fila);
        });
    }

    private void cargarTablaFunciones() {
        try {
            FuncionFiltroTablaDTO filtro = this.obtenerFiltrosTabla();
            List<FuncionTablaDTO> clientesLista = this.funcionNegocio.buscarFuncionesTabla(filtro);
            this.BorrarRegistrosTablaPeliculas();
            this.AgregarRegistrosTablaCliente(clientesLista);
        } catch (NegocioException ex) {
            this.BorrarRegistrosTablaPeliculas();
            this.pagina--;
            this.establecerTituloPaginacion();
            btnPaginaSiguiente.setEnabled(false);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private FuncionFiltroTablaDTO obtenerFiltrosTabla() {
        return new FuncionFiltroTablaDTO(this.LIMITE, this.pagina, campoTextoFiltro.getText());
    }

    private void establecerTituloPaginacion() {
        lblNumeroPagina.setText("Página " + this.pagina);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFunciones = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnNuevaFuncion = new javax.swing.JButton();
        campoTextoFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNumeroPagina = new javax.swing.JLabel();
        btnPaginaAnterior = new javax.swing.JButton();
        btnPaginaSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ModificarFuncion");

        tblFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Pelicula", "Hora Inicio", "Hora Final", "Precio", "Sala", "Fecha", "Eliminar"
            }
        ));
        jScrollPane1.setViewportView(tblFunciones);

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnNuevaFuncion.setBackground(new java.awt.Color(8, 148, 249));
        btnNuevaFuncion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevaFuncion.setText("Nueva");
        btnNuevaFuncion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaFuncionActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtro de busqueda:");

        lblNumeroPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroPagina.setText("Pagina 1");

        btnPaginaAnterior.setText("Anterior");
        btnPaginaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaginaAnteriorActionPerformed(evt);
            }
        });

        btnPaginaSiguiente.setText("Siguiente");
        btnPaginaSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaginaSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblNumeroPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRegresar)
                            .addComponent(btnNuevaFuncion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addComponent(jScrollPane1))))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(btnNuevaFuncion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroPagina, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(72, 72, 72)
                .addComponent(btnRegresar)
                .addGap(17, 17, 17))
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

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        admin.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevaFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaFuncionActionPerformed

        FrmNuevaFuncion nuevaFuncion = new FrmNuevaFuncion(this, funcionNegocio, peliculaNegocio, salaNegocio);
        nuevaFuncion.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnNuevaFuncionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        this.cargarTablaFunciones();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaAnteriorActionPerformed
        this.pagina--;
        if (this.pagina == 0) {
            this.pagina = 1;
            return;
        }
        btnPaginaSiguiente.setEnabled(true);
        this.establecerTituloPaginacion();
        this.cargarTablaFunciones();
    }//GEN-LAST:event_btnPaginaAnteriorActionPerformed

    private void btnPaginaSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaSiguienteActionPerformed
        this.pagina++;
        this.establecerTituloPaginacion();
        this.cargarTablaFunciones();
        btnPaginaAnterior.setEnabled(true);
    }//GEN-LAST:event_btnPaginaSiguienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevaFuncion;
    private javax.swing.JButton btnPaginaAnterior;
    private javax.swing.JButton btnPaginaSiguiente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JTextField campoTextoFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumeroPagina;
    private javax.swing.JTable tblFunciones;
    // End of variables declaration//GEN-END:variables
}
