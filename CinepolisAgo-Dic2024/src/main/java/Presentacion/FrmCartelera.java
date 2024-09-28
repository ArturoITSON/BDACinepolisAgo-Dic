/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.CiudadDTO;
import DTOs.ClienteBuscarDTO;
import DTOs.FuncionDTO;
import DTOs.FuncionFiltroTablaDTO;
import DTOs.FuncionTablaDTO;
import DTOs.PeliculaDTO;
import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaTablaDTO;
import DTOs.SalaDTO;
import DTOs.SucursalDTO;
import Negocio.ICiudadNegocio;
import Negocio.IFuncionNegocio;
import Negocio.IPeliculaNegocio;
import Negocio.ISalaNegocio;
import Negocio.ISucursalNegocio;
import Negocio.NegocioException;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class FrmCartelera extends javax.swing.JFrame {

    
    private int pagina = 0;
    private final int LIMITE = 5;
    
    List<PeliculaDTO> listaPelicula;
    List<FuncionTablaDTO> listaFuncion;
    ArrayList peliculasID = new ArrayList();
    
    IPeliculaNegocio peliculaNegocio;
    IFuncionNegocio funcionNegocio;
    ICiudadNegocio ciudadNegocio;
    ISucursalNegocio sucursalNegocio;
    ISalaNegocio salaNegocio;
    FrmIniciarSesion iniciarSesion;
    
    String rutaReloj = "src/main/java/utilerias/Imagenes/reloj.png";
    String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";

    
    private Map<Integer, Integer> ciudadesIds;
    private static Map<String, Integer> ciudadMap;
    private Map<Integer, Integer> sucursalesIds;
    private Map<String, Integer> sucursalMap;
    private Map<String, Integer> salaMap;
    
    ClienteBuscarDTO cliente;
    
    /**
     * Creates new form FrmCartelera
     */
    public FrmCartelera(IPeliculaNegocio peliculaNegocio, ICiudadNegocio ciudadNegocio, ClienteBuscarDTO cliente, ISucursalNegocio sucursalNegocio,
                        FrmIniciarSesion iniciarSesion, ISalaNegocio salaNegocio, IFuncionNegocio funcionNegocio) {
        initComponents();
        
        this.ciudadNegocio = ciudadNegocio;
        this.cliente = cliente;
        this.sucursalNegocio = sucursalNegocio;
        this.iniciarSesion = iniciarSesion;
        this.salaNegocio = salaNegocio;
        this.funcionNegocio = funcionNegocio;
        this.peliculaNegocio = peliculaNegocio;
        
        setCinepolisLogo(jblCinepolis, rutaCinepolisLogo);
        
        
        try {
            cargarCiudades();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PeliculaFiltroTablaDTO filtro = this.obtenerFiltrosTabla();
//        FuncionFiltroTablaDTO filtroFuncion = this.obtenerFiltrosTablaFuncion();
        
            
            //this.listaFuncion = funcionNegocio.buscarFuncionesTabla(filtroFuncion);
//            
//            for(int i = 0; i< listaFuncion.size(); i++){
//                peliculasID.add(listaFuncion.get(i).getPelicula_id());
//            }
            

        btnRetroceso.setEnabled(false);

        
        cargarFunciones();
        
        try {
            cargarMetodosDatos();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    
    private void cargarMetodosDatos() throws NegocioException, MalformedURLException{
    

       setImagen(lblImagenPelicula, getImagenPelicula());
       setImagenLabel(lblReloj, rutaReloj);
       setDescripcion();
       setTituloPelicula();
       setDuracion();
        
        
        
        
    
    }
    
    
    private void cargarCiudades() throws NegocioException {
            
        try {
            List<CiudadDTO> ciudades = ciudadNegocio.obtenerCiudadesDTO();
            
            ciudadMap = new HashMap<>();
            
            cbcCiudades.removeAllItems();
            
            for (CiudadDTO ciudad : ciudades) {
                String nombre = ciudad.getNombre();
                int ciudad_id = ciudad.getId();
                
                System.out.println( "tamaño es: " + ciudades.size());
                
                cbcCiudades.addItem(ciudad.getNombre());
                ciudadMap.put(nombre, ciudad_id);
                
            }
            
//          if (cont == 1){
         // cbcCiudades.setSelectedIndex(ciudadMap.get(ciudadNegocio.buscarPorId(cliente.getCiudad()).getNombre()) - 1);
//            cont++;
//            }
            
            
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las ciudades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void cargarSucursales() throws NegocioException {
        
        try {
            List<SucursalDTO> sucursales = sucursalNegocio.buscarSucursalPorIdCiudad(cbcCiudades.getSelectedIndex() + 1);
            
            //List<SucursalDTO> sucursales = (List<SucursalDTO>) sucursalNegocio.buscarSucursalPorIdCiudad(id);
            
            sucursalMap = new HashMap<>();
            
            cbcSucursales.removeAllItems();
            
            for (SucursalDTO sucursal : sucursales) {
                String nombre = sucursal.getNombre();
                int idSucursal = sucursal.getId();
                int ciudad_id = sucursal.getCiudad_id();
                
                
                
                cbcSucursales.addItem(sucursal.getNombre());
                sucursalMap.put(nombre,ciudad_id);
                
            }
           

            
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las sucursales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    private void cargarSalas() throws NegocioException {
        
        try {
            List<SalaDTO> salas = salaNegocio.obtenerIdSalasPorSucursal(cbcSucursales.getSelectedIndex() + 1);
            
            //List<SucursalDTO> sucursales = (List<SucursalDTO>) sucursalNegocio.buscarSucursalPorIdCiudad(id);
            
            salaMap = new HashMap<>();
            
            cbcSalas.removeAllItems();
            
            for (SalaDTO sala : salas) {
                String nombre = sala.getNombre();
                int idSala = sala.getId();
                
                
                
                cbcSalas.addItem(sala.getNombre());
                salaMap.put(nombre,idSala);
                
            }
           

            
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las sucursales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void cargarFunciones(){
    
        try{
            
            List<FuncionDTO> funciones = funcionNegocio.buscarFuncionesPorIdSala(cbcSalas.getSelectedIndex() + 2);
            
            
            for(int i = 0; i< funciones.size(); i++){
                peliculasID.add(funciones.get(i).getPelicula_id());
            }
            
            this.listaPelicula = peliculaNegocio.obtenerPeliculasDTO();
            this.listaPelicula.clear();
            
            for(int i = 0; i< funciones.size(); i++){
                this.listaPelicula.add(peliculaNegocio.obtenerPeliculasPorId((int) peliculasID.get(i)));
            }
            
            try {
                cargarMetodosDatos();
            } catch (MalformedURLException ex) {
                Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listaPelicula.get(0).getTitulo());
        }
        catch(NegocioException ex){
            JOptionPane.showMessageDialog(this, "Error al cargar las funciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        
        
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
        
            
        imagenLink = this.listaPelicula.get(0).getLinkImagen();
        
        return imagenLink;
    }
    
    private void setTituloPelicula() throws NegocioException{
        
        String titulo = this.listaPelicula.get(pagina).getTitulo();
        
        lblTitulo.setText(titulo);
    }  
    
    private void setDescripcion() throws NegocioException{
            
        String descripcion = this.listaPelicula.get(pagina).getSinopsis();
        
        lblDescripcion.setText(descripcion);
    }  
    
    private void setDuracion() throws NegocioException{
            
        String inicio = String.valueOf(this.listaPelicula.get(pagina).getDuracion());
        
        lblHorario.setText("Duracion: "  + inicio + "m");
    } 
    
    
    private PeliculaFiltroTablaDTO obtenerFiltrosTabla() {
        return new PeliculaFiltroTablaDTO(this.LIMITE, this.pagina, "");
    }
    
//    private FuncionFiltroTablaDTO obtenerFiltrosTablaFuncion() {
//        return new FuncionFiltroTablaDTO(this.LIMITE, this.pagina, "");
//    }
    
    
    
    private void setCinepolisLogo(JLabel nombreJlb, String ruta){
    
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

        jblFunciones = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        cbcSucursales = new javax.swing.JComboBox<>();
        btnTickets = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        lblReloj = new javax.swing.JLabel();
        lblFuncion = new javax.swing.JLabel();
        btnRetroceso = new javax.swing.JButton();
        btnAvanzar = new javax.swing.JButton();
        cbcFunciones1 = new javax.swing.JComboBox<>();
        lblFunciones = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        lblImagenPelicula = new javax.swing.JLabel();
        cbcCiudades = new javax.swing.JComboBox<>();
        jlbCiudad = new javax.swing.JLabel();
        jlbSucursal = new javax.swing.JLabel();
        cbcSalas = new javax.swing.JComboBox<>();
        jlbSalas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jblCinepolis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cartelera");

        jblFunciones.setBackground(new java.awt.Color(255, 255, 255));

        btnVolver.setBackground(new java.awt.Color(8, 148, 249));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        cbcSucursales.setBackground(new java.awt.Color(136, 201, 239));
        cbcSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcSucursalesActionPerformed(evt);
            }
        });

        btnTickets.setBackground(new java.awt.Color(8, 148, 249));
        btnTickets.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTickets.setText("Mis tickets");
        btnTickets.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketsActionPerformed(evt);
            }
        });

        lblFuncion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnRetroceso.setBackground(new java.awt.Color(8, 148, 249));
        btnRetroceso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRetroceso.setText("<");
        btnRetroceso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRetroceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocesoActionPerformed(evt);
            }
        });

        btnAvanzar.setBackground(new java.awt.Color(8, 148, 249));
        btnAvanzar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAvanzar.setText(">");
        btnAvanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarActionPerformed(evt);
            }
        });

        cbcFunciones1.setBackground(new java.awt.Color(136, 201, 239));

        lblFunciones.setText("Funciones");

        lblDescripcion.setText("Descripcion");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Titulo");

        lblHorario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHorario.setText("Horario");

        lblGenero.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblGenero.setText("Genero");
        lblGenero.setToolTipText("");

        btnComprar.setBackground(new java.awt.Color(51, 255, 0));
        btnComprar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnComprar.setText("Comprar");
        btnComprar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(lblGenero)
                        .addGap(45, 45, 45)
                        .addComponent(btnRetroceso)
                        .addGap(18, 18, 18)
                        .addComponent(btnAvanzar)
                        .addGap(18, 18, 18))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(cbcFunciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(lblFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))))
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblReloj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGenero, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(lblHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbcFunciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFunciones)
                            .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAvanzar)
                        .addComponent(btnRetroceso)))
                .addGap(18, 18, 18)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        lblImagenPelicula.setText("jLabel1");

        cbcCiudades.setBackground(new java.awt.Color(136, 201, 239));
        cbcCiudades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbcCiudadesItemStateChanged(evt);
            }
        });
        cbcCiudades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbcCiudadesMouseClicked(evt);
            }
        });
        cbcCiudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcCiudadesActionPerformed(evt);
            }
        });

        jlbCiudad.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbCiudad.setText("Ciudades");

        jlbSucursal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbSucursal.setText("Sucursal");

        cbcSalas.setBackground(new java.awt.Color(136, 201, 239));
        cbcSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcSalasActionPerformed(evt);
            }
        });

        jlbSalas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbSalas.setText("Sala");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jblCinepolis, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jblCinepolis, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jblFuncionesLayout = new javax.swing.GroupLayout(jblFunciones);
        jblFunciones.setLayout(jblFuncionesLayout);
        jblFuncionesLayout.setHorizontalGroup(
            jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jblFuncionesLayout.createSequentialGroup()
                .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jblFuncionesLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblImagenPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jblFuncionesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jblFuncionesLayout.createSequentialGroup()
                                .addComponent(btnVolver)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                                .addComponent(btnTickets))
                            .addComponent(cbcCiudades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jblFuncionesLayout.createSequentialGroup()
                                .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbcSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbSucursal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbSalas)
                                    .addComponent(cbcSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(jblFuncionesLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jlbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jblFuncionesLayout.createSequentialGroup()
                    .addContainerGap(36, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
        );
        jblFuncionesLayout.setVerticalGroup(
            jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jblFuncionesLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbCiudad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbcCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbSucursal)
                    .addComponent(jlbSalas))
                .addGap(2, 2, 2)
                .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbcSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(lblImagenPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnTickets))
                .addGap(24, 24, 24))
            .addGroup(jblFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jblFuncionesLayout.createSequentialGroup()
                    .addContainerGap(278, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(91, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jblFunciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jblFunciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbcCiudadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcCiudadesActionPerformed

        try {
            cargarSucursales();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbcCiudadesActionPerformed

    private void cbcCiudadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcCiudadesMouseClicked

    }//GEN-LAST:event_cbcCiudadesMouseClicked

    private void cbcCiudadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbcCiudadesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcCiudadesItemStateChanged

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        // TODO add your handling code here:
        this.pagina++;
        btnRetroceso.setEnabled(true);
        try {
            this.cargarMetodosDatos();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);

        } catch (MalformedURLException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(this, "Ya no hay mas peliculas");
            btnAvanzar.setEnabled(false);
            this.pagina--;
        }

        btnRetroceso.setEnabled(true);
    }//GEN-LAST:event_btnAvanzarActionPerformed

    private void btnRetrocesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocesoActionPerformed
        // TODO add your handling code here:

        if (this.pagina == 0) {
            this.pagina = 1;
            btnRetroceso.setEnabled(false);
            return;
        }
        btnAvanzar.setEnabled(true);
        try {
            this.pagina--;
            this.cargarMetodosDatos();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRetrocesoActionPerformed

    private void btnTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTicketsActionPerformed

    private void cbcSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcSucursalesActionPerformed
        // TODO add your handling code here:

        try {
            cargarSalas();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbcSucursalesActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        iniciarSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cbcSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcSalasActionPerformed
        // TODO add your handling code here:
        cargarFunciones();
        
    }//GEN-LAST:event_cbcSalasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnRetroceso;
    private javax.swing.JButton btnTickets;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbcCiudades;
    private javax.swing.JComboBox<String> cbcFunciones1;
    private javax.swing.JComboBox<String> cbcSalas;
    private javax.swing.JComboBox<String> cbcSucursales;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jblCinepolis;
    private javax.swing.JPanel jblFunciones;
    private javax.swing.JLabel jlbCiudad;
    private javax.swing.JLabel jlbSalas;
    private javax.swing.JLabel jlbSucursal;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFuncion;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblImagenPelicula;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblTitulo;
    private java.awt.Panel panel2;
    // End of variables declaration//GEN-END:variables
}
