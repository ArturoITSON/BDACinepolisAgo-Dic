/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.SalaDTO;
import DTOs.SalaFiltroTablaDTO;
import DTOs.SalaGuardarDTO;
import DTOs.SalaModificarDTO;
import DTOs.SalaTablaDTO;
import Entidades.SalaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public class SalaDAO implements ISalaDAO {
    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    
    public SalaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    @Override
    public List<String> obtenerSalas() throws PersistenciaException {
    List<String> salas = new ArrayList<>();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT nombre FROM Sala";
        comandoSQL = conexion.createStatement();
        resultado = comandoSQL.executeQuery(codigoSQL);

        while (resultado.next()) {
            salas.add(resultado.getString("nombre"));
        }
        return salas;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de salas, inténtelo de nuevo.");
    } finally {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (comandoSQL != null) {
                comandoSQL.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
        }
     }
    }
    
    
    
    @Override
    public List<String> obtenerSalasPorSucursal(int idSucursal) throws PersistenciaException {
    List<String> salas = new ArrayList<>();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT nombre FROM Sala WHERE sucursal_id = ?";
        
        PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
        preparedStatement.setInt(1, idSucursal);
        
        resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                salas.add(resultado.getString("nombre"));
            }

        return salas;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de salas, inténtelo de nuevo.");
    } finally {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (comandoSQL != null) {
                comandoSQL.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
        }
     }
    }
    
    
    @Override
    public List<SalaDTO> obtenerIdSalasPorSucursal(int idSucursal) throws PersistenciaException {
    List<SalaDTO> salas = new ArrayList<>();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT id, nombre, asientos_disponibles, precio, minutosLimpieza, sucursal_id FROM Sala WHERE sucursal_id = ?";
        
        PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
        preparedStatement.setInt(1, idSucursal);
        
        resultado = preparedStatement.executeQuery();
        
            while (resultado.next()) {
                if (salas == null) {
                    salas = new ArrayList<>();
                }
                salas.add(this.SalaDTO(resultado));
            }

        return salas;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de salas, inténtelo de nuevo.");
    } finally {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (comandoSQL != null) {
                comandoSQL.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
        }
     }
    }
    
    @Override
    public List<SalaTablaDTO> buscarSalasTabla(SalaFiltroTablaDTO filtro) throws PersistenciaException {
                try {
            List<SalaTablaDTO> salaLista = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                                SELECT
                                     id,
                                     nombre,
                                     asientos_disponibles,
                                     precio,
                                     minutosLimpieza,
                                     sucursal_id
                                FROM Sala
                                WHERE CONCAT(nombre, ' ', precio, ' ', sucursal_id) LIKE ?
                                LIMIT ? 
                                OFFSET ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, "%" + filtro.getFiltro() + "%");
            preparedStatement.setInt(2, filtro.getLimit());
            preparedStatement.setInt(3, filtro.getOffset());

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                if (salaLista == null) {
                    salaLista = new ArrayList<>();
                }
                salaLista.add(this.salaTablaDTO(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return salaLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    @Override
    public SalaEntidad guardar(SalaGuardarDTO sala) throws PersistenciaException {

        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            String insertCliente = """
                                    INSERT INTO Sala (nombre,
                                                          asientos_disponibles,
                                                          precio,
                                                          minutosLimpieza,
                                                          sucursal_id
                                   ) 
                                                 VALUES (?, ?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexionGeneral.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sala.getNombre());
            preparedStatement.setInt(2, sala.getAsientos_disponibles());
            preparedStatement.setFloat(3, sala.getPrecio());
            preparedStatement.setInt(4, sala.getMinutosLimpiza());
            preparedStatement.setInt(5, sala.getSucursa_id());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción de la sala falló, no se pudo insertar el registro.");
            }

            int idCliente = 0;
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                idCliente = (resultado.getInt(1));
            }
            
            // Confirmar la transacción
            conexionGeneral.commit();

            resultado.close();
            preparedStatement.close();
            conexionGeneral.close();

            return this.buscarPorId(idCliente);

        } catch (SQLException | PersistenciaException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al registrar la sala, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }

    }
    
    @Override
    public SalaEntidad modificarSala(SalaModificarDTO sala) throws PersistenciaException {
        
        try{
        this.conexionGeneral = this.conexionBD.crearConexion();
        this.conexionGeneral.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        
        SalaEntidad salaEditada = new SalaEntidad();
        salaEditada.setId(sala.getId());
        salaEditada.setNombre(sala.getNombre());
        salaEditada.setAsientos_disponibles(sala.getAsientos_disponibles());
        salaEditada.setPrecio(sala.getPrecio());
        salaEditada.setMinutosLimpiza(sala.getMinutosLimpiza());
        salaEditada.setSucursa_id(sala.getSucursa_id());
        
            String sentenciaSql = "UPDATE Sala SET nombre = ?, asientos_disponibles = ?, precio = ?, minutosLimpieza= ?, sucursal_Id = ? WHERE id = ?";
            preparedStatement = conexionGeneral.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, salaEditada.getNombre());
            preparedStatement.setInt(2, salaEditada.getAsientos_disponibles());
            preparedStatement.setFloat(3, salaEditada.getPrecio());
            preparedStatement.setInt(4, salaEditada.getMinutosLimpiza());
            preparedStatement.setInt(5, salaEditada.getSucursa_id());
            preparedStatement.setInt(6, salaEditada.getId());

            
            
            preparedStatement.executeUpdate();
            // Confirmar la transacción
            conexionGeneral.commit();
            
            System.out.println(salaEditada.toString() + "dao");
            return salaEditada;
            
        }
             catch (SQLException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al eliminar el cliente, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }
    }
    
    
    @Override
    public SalaEntidad eliminarSala(int idSala) throws PersistenciaException {
        
        try{
        this.conexionGeneral = this.conexionBD.crearConexion();
        this.conexionGeneral.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        SalaEntidad salaEliminada = this.buscarPorId(idSala);
        
            conexionGeneral = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Sala WHERE id = ?";
            preparedStatement = conexionGeneral.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idSala);

            preparedStatement.executeUpdate();
            
            // Confirmar la transacción
            conexionGeneral.commit();
            
            return salaEliminada;
            
        } catch (SQLException | PersistenciaException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al eliminar la sala, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }
    }
    
    
    
        private SalaDTO SalaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        int asientos_disponibles = resultado.getInt("asientos_disponibles");
        float precio = resultado.getFloat("precio");
        int minutosLimpieza = resultado.getInt("minutosLimpieza");
        int sucursal_id = resultado.getInt("sucursal_id");
        return new SalaDTO(id, nombre, asientos_disponibles, precio, minutosLimpieza, sucursal_id);
    }
    
    private SalaTablaDTO salaTablaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombres = resultado.getString("nombre");
        int asientos_disponibles = resultado.getInt("asientos_disponibles");
        float precio = resultado.getInt("precio");
        int minutosLimpieza = resultado.getInt("minutosLimpieza");
        int sucursal_id = resultado.getInt("sucursal_id");
        
        return new SalaTablaDTO(id, nombres, asientos_disponibles, precio, minutosLimpieza, sucursal_id);
    }

    private SalaEntidad buscarPorId(int idSala) throws PersistenciaException{
        try {
            SalaEntidad sala = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombre,
                                    asientos_disponibles,
                                    precio,
                                    minutosLimpieza,
                                    sucursal_id
                               FROM Sala
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idSala);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                sala = this.salaEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return sala;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    @Override
    public SalaEntidad buscarSalasPorNombre(String nombreSala) throws PersistenciaException{
        try {
            SalaEntidad sala = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombre,
                                    asientos_disponibles,
                                    precio,
                                    minutosLimpieza,
                                    sucursal_id
                               FROM Sala
                               WHERE nombre = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, nombreSala);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                sala = this.salaEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return sala;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }    
    
    private SalaEntidad salaEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        int asientos_disponibles = resultado.getInt("asientos_disponibles");
        float precio = resultado.getFloat("precio");
        int minutosLimpieza = resultado.getInt("minutosLimpieza");
        int sucursal_id = resultado.getInt("sucursal_id");
        return new SalaEntidad(id, nombre, asientos_disponibles, precio, minutosLimpieza, sucursal_id);
    }
    
    
    
}
