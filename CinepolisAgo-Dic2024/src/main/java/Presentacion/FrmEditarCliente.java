/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.ClienteFiltroTablaDTO;
import DTOs.ClienteModificarDTO;
import DTOs.ClienteTablaDTO;
import Negocio.IClienteNegocio;
import Negocio.NegocioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
public class FrmEditarCliente extends javax.swing.JFrame {

    FrmPantallaAdmin admin;
    IClienteNegocio clienteNegocio;

    private int pagina = 1;
    private final int LIMITE = 20;

    /**
     * Creates new form FrmEditarCliente
     */
    public FrmEditarCliente(FrmPantallaAdmin admin, IClienteNegocio clienteNegocio) {
        this.admin = admin;
        this.clienteNegocio = clienteNegocio;
        initComponents();

        metodosIniciales();
    }

    private void metodosIniciales() {
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarTablaClientes();
    }

    private void cargarConfiguracionInicialTablaClientes() {
        ActionListener onEditarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar
                editar();
            }
        };
        int indiceColumnaEditar = 8;
        TableColumnModel modeloColumnas = this.tblClientes.getColumnModel();
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
        int indiceColumnaEliminar = 9;
        modeloColumnas = this.tblClientes.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private int getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private String getNombresSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 1;
            String idSocioSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private String getApellidoPaternoSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 2;
            String idSocioSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private String getApellidoMaternoSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 3;
            String idSocioSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private String getCorreoSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 4;
            String idSocioSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private Date getFechaNacimientoSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 5;
            Date idSocioSeleccionado = (Date) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private String getContrasenaSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 6;
            String idSocioSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private int getCiudadSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 7;
            Object value = modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            if (value != null) {
                return (int) value;
            }
        }
        return 0; // O el valor predeterminado que consideres apropiado
    }

    private void editar() {
        int id = this.getIdSeleccionadoTablaClientes();
        System.out.println("El id para editar es " + id);

        ClienteModificarDTO cliente = new ClienteModificarDTO();

        try {
            cliente.setId(id);

            cliente.setNombres(this.getNombresSeleccionadoTablaClientes());
            cliente.setApellidoPaterno(this.getApellidoPaternoSeleccionadoTablaClientes());
            cliente.setApelldioMaterno(this.getApellidoMaternoSeleccionadoTablaClientes());
            cliente.setCorreo(this.getCorreoSeleccionadoTablaClientes());
            cliente.setNacimiento(this.getFechaNacimientoSeleccionadoTablaClientes());
            cliente.setContrasena(this.getContrasenaSeleccionadoTablaClientes());
            cliente.setCiudad(this.getCiudadSeleccionadoTablaClientes());

            System.out.println(cliente.toString());

            clienteNegocio.modificar(cliente);
            JOptionPane.showMessageDialog(this, "cliente editado");
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo editar");
        } finally {
            this.cargarTablaClientes();
        }

    }

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaClientes();
        try {
            clienteNegocio.eliminar(id);
            JOptionPane.showMessageDialog(this, "cliente con id " + id + " ha sido eliminado");
            this.cargarTablaClientes();
        } catch (NegocioException ex) {
            System.out.println("Error al eliminar");
        }

    }

    private void BorrarRegistrosTablaClientes() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--) {
                modeloTabla.removeRow(row);
            }
        }
    }

    private void AgregarRegistrosTablaCliente(List<ClienteTablaDTO> clientesLista) {
        if (clientesLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
        clientesLista.forEach(row -> {
            Object[] fila = new Object[8];
            fila[0] = row.getIdCliente();
            fila[1] = row.getNombres();
            fila[2] = row.getApellidoPaterno();
            fila[3] = row.getApellidoMaterno();
            fila[4] = row.getNombres(); // Esto será cambiado por localización tan pronto sea implementado
            fila[5] = row.getNacimiento();
            fila[6] = row.getCorreo();
            fila[7] = row.getCiudad();

            modeloTabla.addRow(fila);
        });
    }

    private void cargarTablaClientes() {
    try {
        ClienteFiltroTablaDTO filtro = this.obtenerFiltrosTabla();
        List<ClienteTablaDTO> clientesLista = this.clienteNegocio.buscarClientesTabla(filtro);
        this.BorrarRegistrosTablaClientes();
        this.AgregarRegistrosTablaCliente(clientesLista);

        // Imprimir IDs cargados
        for (ClienteTablaDTO cliente : clientesLista) {
            System.out.println("Cliente cargado: ID = " + cliente.getIdCliente());
        }
    } catch (NegocioException ex) {
        this.BorrarRegistrosTablaClientes();
        this.pagina--;
        this.establecerTituloPaginacion();
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
    }
}


    private ClienteFiltroTablaDTO obtenerFiltrosTabla() {
        return new ClienteFiltroTablaDTO(this.LIMITE, this.pagina, campoTextoFiltro.getText());
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

        lblNumeroPagina = new javax.swing.JLabel();
        btnPaginaAnterior = new javax.swing.JButton();
        btnPaginaSiguiente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        campoTextoFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombres", "Apellido Paterno", "Apellido Materno", "Localización", "Fecha_Nacimiento", "Correo", "Ciudad", "Editar", "Eliminar"
            }
        ));
        jScrollPane1.setViewportView(tblClientes);

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtro de busqueda:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblNumeroPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRegresar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroPagina, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(53, 53, 53)
                .addComponent(btnRegresar)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaAnteriorActionPerformed
        
        this.pagina--;
        if (this.pagina == 0) {
            this.pagina = 1;
            return;
        }
        btnPaginaSiguiente.setEnabled(true);
        this.establecerTituloPaginacion();
        this.cargarTablaClientes();
    }//GEN-LAST:event_btnPaginaAnteriorActionPerformed

    private void btnPaginaSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaSiguienteActionPerformed
        
        this.pagina++;
        this.establecerTituloPaginacion();
        this.cargarTablaClientes();
        btnPaginaAnterior.setEnabled(true);
    }//GEN-LAST:event_btnPaginaSiguienteActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPaginaAnterior;
    private javax.swing.JButton btnPaginaSiguiente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JTextField campoTextoFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumeroPagina;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
