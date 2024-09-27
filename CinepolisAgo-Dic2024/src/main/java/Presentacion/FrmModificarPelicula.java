/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaModificarDTO;
import DTOs.PeliculaTablaDTO;
import Negocio.IClasificacionNegocio;
import Negocio.IClienteNegocio;
import Negocio.IGeneroNegocio;
import Negocio.IPaisNegocio;
import Negocio.IPeliculaNegocio;
import Negocio.NegocioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author Arturo ITSON
 */
public class FrmModificarPelicula extends javax.swing.JFrame {

    FrmPantallaAdmin admin;
    IPeliculaNegocio peliculaNegocio;
    IClienteNegocio clienteNegocio;
    IGeneroNegocio generoNegocio;
    IClasificacionNegocio clasificacionNegocio;
    IPaisNegocio paisNegocio;
    
    private int pagina = 1;
    private final int LIMITE = 2; // dafault 20
    
    /**
     * Creates new form FrmModificarPelicula
     */
    public FrmModificarPelicula(FrmPantallaAdmin admin, IPeliculaNegocio peliculaNegocio, IClienteNegocio clienteNegocio, IGeneroNegocio generoNegocio, IClasificacionNegocio clasificacionNegocio, IPaisNegocio paisNegocio) {
        initComponents();
        
        this.admin = admin;
        this.peliculaNegocio = peliculaNegocio;
        this.clienteNegocio = clienteNegocio;
        this.generoNegocio = generoNegocio;
        this.clasificacionNegocio = clasificacionNegocio;
        this.paisNegocio = paisNegocio;
        
        this.metodosIniciales();
    }

    
    
    private void metodosIniciales() {
        this.cargarConfiguracionInicialPantalla();
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarTablaPeliculas();
        
        btnPaginaAnterior.setEnabled(false);
    }
    
    
    private void cargarConfiguracionInicialPantalla() {
      //  this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    
    
   private void cargarConfiguracionInicialTablaClientes() {
        ActionListener onEditarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar
                editar();
            }
        };
        int indiceColumnaEditar = 6;
        TableColumnModel modeloColumnas = this.tblPeliculas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar", onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar
                eliminar();
            }
        };
        int indiceColumnaEliminar = 7;
        modeloColumnas = this.tblPeliculas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private int getIdSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    
    private String getTituloSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
            int indiceColumnaTitulo = 1;
            String TitloPeliculaSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaTitulo);
            return TitloPeliculaSeleccionado;
        } else {
            return null;
        }
    }
        
    private int getPaisSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
            int indiceColumnaPais = 2;
            int paisSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaPais);
            return paisSeleccionado;
        } else {
            return 0;
        }
    }
            
            
    private int getGeneroSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
            int indiceColumnaGenero = 3;
            int generoSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaGenero);
            return generoSeleccionado;
        } else {
            return 0;
        }
    }
                
                
    private int getClasificacionSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
            int indiceColumnaClasificacion = 4;
            int clasificacionSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaClasificacion);
            return clasificacionSeleccionado;
        } else {
            return 0;
        }
    }
                    
                    
    private int getDuracionSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
            int indiceColumnaDuracion = 5;
            int duracionSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaDuracion);
            return duracionSeleccionado;
        } else {
            return 0;
        }
    }
                        

    private void editar() {
        int id = this.getIdSeleccionadoTablaPeliculas();
        System.out.println("El id para editar es " + id);
        
        PeliculaModificarDTO pelicula = new PeliculaModificarDTO();
        JOptionPane.showMessageDialog(this, this.getTituloSeleccionadoTablaPeliculas());

        try{
        pelicula.setId(id);

        pelicula.setTitulo(this.getTituloSeleccionadoTablaPeliculas());
        pelicula.setClasificacion_id(this.getClasificacionSeleccionadoTablaPeliculas());
        pelicula.setDuracion(this.getDuracionSeleccionadoTablaPeliculas());
        pelicula.setGenero_id(this.getGeneroSeleccionadoTablaPeliculas());
        pelicula.setPais_id(this.getPaisSeleccionadoTablaPeliculas());

        System.out.println(pelicula.toString());    
            
        peliculaNegocio.modificar(pelicula);
        JOptionPane.showMessageDialog(this, "pelicula editada");
        }

        catch(NegocioException ex){
            JOptionPane.showMessageDialog(this, "No se pudo editar");
        }
        
        finally{
            this.cargarTablaPeliculas();
        }
    }

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaPeliculas();
        
        System.out.println("El id para eliminar es " + id);
        try{
        peliculaNegocio.eliminar(id);
        JOptionPane.showMessageDialog(this, "pelicula con id " + id + " ha sido eliminado");
        }
        
        catch(NegocioException ex){
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la pelicula");
        }
        finally{
            this.cargarTablaPeliculas();
        }
        
        
        
        this.cargarTablaPeliculas();
    }

    private void BorrarRegistrosTablaPeliculas() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--) {
                modeloTabla.removeRow(row);
            }
        }
    }

    private void AgregarRegistrosTablaCliente(List<PeliculaTablaDTO> clientesLista) {
        if (clientesLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();
        clientesLista.forEach(row -> {
            Object[] fila = new Object[6];
            fila[0] = row.getId();
            fila[1] = row.getTitulo();
            fila[2] = row.getPais();
            fila[3] = row.getGenero_id();
            fila[4] = row.getClasifiacion_id();
            fila[5] = row.getDuracion();

            modeloTabla.addRow(fila);
        });
    }

    private void cargarTablaPeliculas() {
        try {
            PeliculaFiltroTablaDTO filtro = this.obtenerFiltrosTabla();
            List<PeliculaTablaDTO> clientesLista = this.peliculaNegocio.buscarPeliculasTabla(filtro);
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

    private PeliculaFiltroTablaDTO obtenerFiltrosTabla() {
        return new PeliculaFiltroTablaDTO(this.LIMITE, this.pagina, campoTextoFiltro.getText());
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

        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnNuevaPeli = new javax.swing.JButton();
        campoTextoFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNumeroPagina = new javax.swing.JLabel();
        btnPaginaAnterior = new javax.swing.JButton();
        btnPaginaSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar Pelicula");

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Titulo", "Pais", "Genero", "Clasificacion", "Duracion", "Editar", "Eliminar"
            }
        ));
        jScrollPane1.setViewportView(tblPeliculas);

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnNuevaPeli.setBackground(new java.awt.Color(8, 148, 249));
        btnNuevaPeli.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevaPeli.setText("Nueva");
        btnNuevaPeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaPeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPeliActionPerformed(evt);
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
                .addContainerGap(36, Short.MAX_VALUE)
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
                            .addComponent(btnNuevaPeli)
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
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(btnNuevaPeli)
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
        
        this.dispose();
        admin.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevaPeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPeliActionPerformed
        // TODO add your handling code here:
        try{
        FrmNuevaPelicula nuevaPeli = new FrmNuevaPelicula(this, peliculaNegocio, clienteNegocio, generoNegocio, clasificacionNegocio, paisNegocio);
        nuevaPeli.setVisible(true);
        }
        
        catch(Exception ex){
            System.out.println("noup");
        }
        
        finally{
            this.cargarTablaPeliculas();
        }
    }//GEN-LAST:event_btnNuevaPeliActionPerformed

    private void btnPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaAnteriorActionPerformed
        this.pagina--;
        if (this.pagina == 0) {
            this.pagina = 1;
            return;
        }
        btnPaginaSiguiente.setEnabled(true);
        this.establecerTituloPaginacion();
        this.cargarTablaPeliculas();
    }//GEN-LAST:event_btnPaginaAnteriorActionPerformed

    private void btnPaginaSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaSiguienteActionPerformed
        this.pagina++;
        this.establecerTituloPaginacion();
        this.cargarTablaPeliculas();
        btnPaginaAnterior.setEnabled(true);
    }//GEN-LAST:event_btnPaginaSiguienteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.cargarTablaPeliculas();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevaPeli;
    private javax.swing.JButton btnPaginaAnterior;
    private javax.swing.JButton btnPaginaSiguiente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JTextField campoTextoFiltro;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumeroPagina;
    private javax.swing.JTable tblPeliculas;
    // End of variables declaration//GEN-END:variables
}
