/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.SalaFiltroTablaDTO;
import DTOs.SalaModificarDTO;
import DTOs.SalaTablaDTO;
import Negocio.ICiudadNegocio;
import Negocio.ISalaNegocio;
import Negocio.ISucursalNegocio;
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
public class FrmModificarSala extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 5;
    FrmPantallaAdmin admin;
    ISalaNegocio salaNegocio;
    ISucursalNegocio sucursalNegocio;
    ICiudadNegocio ciudadNegocio;

    /**
     * Creates new form ModificarSala
     */
    public FrmModificarSala(FrmPantallaAdmin admin, ISalaNegocio salaNegocio, ISucursalNegocio sucursalNegocio, ICiudadNegocio ciudadNegocio) {
        this.admin = admin;
        this.salaNegocio = salaNegocio;
        this.sucursalNegocio = sucursalNegocio;
        this.ciudadNegocio = ciudadNegocio;

        initComponents(); // Inicializar los componentes de la interfaz

        // Configurar el modelo de la tabla con celdas no editables para ID y sucursal_id
        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Nombre", "Asientos Disponibles", "Precio", "Minutos Limpieza", "Sucursal ID", "Editar", "Eliminar"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que las columnas de ID y Sucursal ID no sean editables
                return column != 0 && column != 5; // Cambia los índices según tu tabla (0 para 'ID' y 5 para 'Sucursal ID')
            }
        };

        tblSalas.setModel(modeloTabla); // Asignar el modelo a la tabla

        metodosIniciales(); // Llamar a los métodos iniciales después de la configuración
    }

    private void metodosIniciales() {
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarTablaSalas();
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
        TableColumnModel modeloColumnas = this.tblSalas.getColumnModel();
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
        modeloColumnas = this.tblSalas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private int getIdSeleccionadoTablaSalas() {
        int indiceFilaSeleccionada = this.tblSalas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSalas.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private String getNombreSeleccionadoTablaSalas() {
        int indiceFilaSeleccionada = this.tblSalas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSalas.getModel();
            int indiceColumnaId = 1;
            String idSocioSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private int getAsientosDisponiblesSeleccionadoTablaSalas() {
        int indiceFilaSeleccionada = this.tblSalas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSalas.getModel();
            int indiceColumnaId = 2;
            Object value = modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);

            if (value instanceof String) {
                try {
                    return Integer.parseInt((String) value);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el valor a entero: " + e.getMessage());
                    return 0; // Devuelve un valor predeterminado en caso de error
                }
            } else if (value instanceof Integer) {
                return (Integer) value;
            }
        }
        return 0;
    }

    private float getPrecioSeleccionadoTablaSalas() {
        int indiceFilaSeleccionada = this.tblSalas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSalas.getModel();
            int indiceColumnaId = 3;
            Object value = modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);

            if (value instanceof String) {
                try {
                    return Float.parseFloat((String) value);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el precio a float: " + e.getMessage());
                    return 0; // Devuelve un valor por defecto o muestra un mensaje de error
                }
            } else if (value instanceof Float) {
                return (Float) value;
            }
        }
        return 0;
    }

    private int getMinutosLimpiezaSeleccionadoTablaSalas() {
        int indiceFilaSeleccionada = this.tblSalas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSalas.getModel();
            int indiceColumnaId = 4;
            Object value = modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);

            if (value instanceof String) {
                try {
                    return Integer.parseInt((String) value);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el valor a entero: " + e.getMessage());
                    return 0; // Devuelve un valor predeterminado en caso de error
                }
            } else if (value instanceof Integer) {
                return (Integer) value;
            }
        }
        return 0;
    }

    private int getSucursalSeleccionadoTablaSalas() {
        int indiceFilaSeleccionada = this.tblSalas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSalas.getModel();
            int indiceColumnaId = 5;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private void editar() {
        int id = this.getIdSeleccionadoTablaSalas();
        System.out.println("El id para editar es " + id);

        SalaModificarDTO sala = new SalaModificarDTO();

        try {
            sala.setId(id);

            sala.setNombre(this.getNombreSeleccionadoTablaSalas());
            sala.setAsientos_disponibles(this.getAsientosDisponiblesSeleccionadoTablaSalas());
            sala.setPrecio(this.getPrecioSeleccionadoTablaSalas());
            sala.setMinutosLimpiza(this.getMinutosLimpiezaSeleccionadoTablaSalas());
            sala.setSucursa_id(this.getSucursalSeleccionadoTablaSalas());

            System.out.println(sala.toString());

            salaNegocio.modificar(sala);
            JOptionPane.showMessageDialog(this, "sala editada");
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo editar");
        } finally {
            this.cargarTablaSalas();
        }

    }

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaSalas();
        try {
            salaNegocio.eliminar(id);
            JOptionPane.showMessageDialog(this, "cliente con id " + id + " ha sido eliminado");
            this.cargarTablaSalas();
        } catch (NegocioException ex) {
            System.out.println("Error al eliminar");
        }

    }

    private void BorrarRegistrosTablaSalas() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblSalas.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--) {
                modeloTabla.removeRow(row);
            }
        }
    }

    private void AgregarRegistrosTablaSala(List<SalaTablaDTO> salasLista) {
        if (salasLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblSalas.getModel();
        modeloTabla.setRowCount(0); // Limpia las filas existentes antes de agregar nuevas

        salasLista.forEach(row -> {
            Object[] fila = new Object[8];
            fila[0] = row.getId(); // ID - No editable
            fila[1] = row.getNombre();
            fila[2] = row.getAsientos_disponibles();
            fila[3] = row.getPrecio();
            fila[4] = row.getMinutosLimpiza();
            fila[5] = row.getSucursa_id(); // Sucursal ID - No editable
            fila[6] = "Editar";
            fila[7] = "Eliminar";

            modeloTabla.addRow(fila);
        });
    }

    private void cargarTablaSalas() {
        try {
            SalaFiltroTablaDTO filtro = this.obtenerFiltrosTabla();
            List<SalaTablaDTO> salasLista = this.salaNegocio.buscarSalasTabla(filtro);
            this.BorrarRegistrosTablaSalas();
            this.AgregarRegistrosTablaSala(salasLista);

            // Imprimir IDs cargados
            for (SalaTablaDTO sala : salasLista) {
                System.out.println("Sala cargada: ID = " + sala.getId());
            }
        } catch (NegocioException ex) {
            this.BorrarRegistrosTablaSalas();
            this.pagina--;
            this.establecerTituloPaginacion();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private SalaFiltroTablaDTO obtenerFiltrosTabla() {
        return new SalaFiltroTablaDTO(this.LIMITE, this.pagina, campoTextoFiltro.getText());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalas = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnNuevaSala = new javax.swing.JButton();
        campoTextoFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNumeroPagina = new javax.swing.JLabel();
        btnPaginaAnterior = new javax.swing.JButton();
        btnPaginaSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Asientos Disponibles", "Precio", "Minutos Limpieza", "Ciudad", "Editar", "Eliminar"
            }
        ));
        jScrollPane1.setViewportView(tblSalas);

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnNuevaSala.setBackground(new java.awt.Color(8, 148, 249));
        btnNuevaSala.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevaSala.setText("Nueva");
        btnNuevaSala.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaSalaActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
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
                            .addComponent(btnNuevaSala)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addComponent(jScrollPane1))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(btnNuevaSala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroPagina, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(72, 72, 72)
                .addComponent(btnRegresar)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevaSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaSalaActionPerformed

        FrmNuevaSala ns = new FrmNuevaSala(this, salaNegocio, sucursalNegocio, ciudadNegocio);
        ns.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNuevaSalaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        this.cargarTablaSalas();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaAnteriorActionPerformed
        this.pagina--;
        if (this.pagina == 0) {
            this.pagina = 1;
            return;
        }
        btnPaginaSiguiente.setEnabled(true);
        this.establecerTituloPaginacion();
        this.cargarTablaSalas();
    }//GEN-LAST:event_btnPaginaAnteriorActionPerformed

    private void btnPaginaSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaSiguienteActionPerformed
        this.pagina++;
        this.establecerTituloPaginacion();
        this.cargarTablaSalas();
        btnPaginaAnterior.setEnabled(true);
    }//GEN-LAST:event_btnPaginaSiguienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevaSala;
    private javax.swing.JButton btnPaginaAnterior;
    private javax.swing.JButton btnPaginaSiguiente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JTextField campoTextoFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumeroPagina;
    private javax.swing.JTable tblSalas;
    // End of variables declaration//GEN-END:variables

}
