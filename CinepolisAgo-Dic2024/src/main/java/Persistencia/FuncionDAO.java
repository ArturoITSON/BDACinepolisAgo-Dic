/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.FuncionFiltroTablaDTO;
import DTOs.FuncionGuardarDTO;
import DTOs.FuncionTablaDTO;
import Entidades.FuncionEntidad;
import Entidades.PeliculaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public class FuncionDAO implements IFuncionDAO{

    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    
    public FuncionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    
    @Override
    public List<FuncionTablaDTO> buscarFuncionesTabla(FuncionFiltroTablaDTO filtro) throws PersistenciaException {
        try {
            List<FuncionTablaDTO> funcionLista = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                                SELECT
                                     id,
                                     precio,
                                     dia_funcion,
                                     empezarFuncion,
                                     terminarFuncion,
                                     pelicula_id,
                                     sala_id
                                FROM Funcion
                                WHERE CONCAT(pelicula_id, ' ', sala_id, ' ', dia_funcion) LIKE ?
                                LIMIT ? 
                                OFFSET ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, "%" + filtro.getFiltro() + "%");
            preparedStatement.setInt(2, filtro.getLimit());
            preparedStatement.setInt(3, filtro.getOffset());

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                if (funcionLista == null) {
                    funcionLista = new ArrayList<>();
                }
                funcionLista.add(this.funcionTablaDTO(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return funcionLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }    

    @Override
    public FuncionEntidad guardar(FuncionGuardarDTO funcion) throws PersistenciaException {
  
        try {
            
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            String insertCliente = """
                                    INSERT INTO Funcion (precio,
                                                          dia_funcion,
                                                          empezarFuncion,
                                                          terminarFuncion,
                                                          pelicula_id,
                                                          sala_id
                                   ) 
                                                 VALUES (?, ?, ?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexionGeneral.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, funcion.getPrecio());
            preparedStatement.setString(2, funcion.getDiaFuncion());
            preparedStatement.setTime(3, funcion.getEmpezaFuncion());
            preparedStatement.setTime(4, funcion.getTerminoFuncion());
            preparedStatement.setInt(5, funcion.getPelicula_id());
            preparedStatement.setInt(6, funcion.getSala_id());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción de la funcion falló, no se pudo insertar el registro.");
            }

            int idFuncion = 0;
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                idFuncion = (resultado.getInt(1));
            }

                        
            // Confirmar la transacción
            conexionGeneral.commit();
            
            resultado.close();
            preparedStatement.close();
            conexionGeneral.close();

            return this.buscarPorId(idFuncion);

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
    public FuncionEntidad buscarPorId(int id) throws PersistenciaException {
        
        try {
            FuncionEntidad funcion = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    precio,
                                    empezarFuncion,
                                    terminarFuncion,
                                    dia_funcion,
                                    pelicula_id,
                                    sala_id
                               FROM Funcion
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                funcion = this.funcionEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return funcion;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    @Override
    public FuncionEntidad eliminarFuncion(int idFuncion) throws PersistenciaException {
        
        try{
        this.conexionGeneral = this.conexionBD.crearConexion();
        this.conexionGeneral.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        FuncionEntidad funcionEliminada = this.buscarPorId(idFuncion);
        
            String sentenciaSql = "DELETE FROM Funcion WHERE id = ?";
            preparedStatement = conexionGeneral.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, funcionEliminada.getId());

            preparedStatement.executeUpdate();
            
            // Confirmar la transacción
            conexionGeneral.commit();

            
            preparedStatement.close();
            
            return funcionEliminada;
            
        } catch (SQLException ex) {
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
    public List<FuncionEntidad> buscarFuncionesPorIdSala(int idSala) throws PersistenciaException {
        
        List<FuncionEntidad> funciones = new ArrayList<>();
        
        try {
            FuncionEntidad funcion = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    precio,
                                    empezarFuncion,
                                    terminarFuncion,
                                    dia_funcion,
                                    pelicula_id,
                                    sala_id
                               FROM Funcion
                               WHERE sala_id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idSala);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                funcion = new FuncionEntidad(
                    resultado.getInt("id"),
                    resultado.getFloat("precio"),
                    resultado.getTime("empezarFuncion"),
                    resultado.getTime("terminarFuncion"),
                    resultado.getString("dia_funcion"),
                    resultado.getInt("pelicula_id"),
                    resultado.getInt("sala_id")
                );
                funciones.add(funcion);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return funciones;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    } 
    
    @Override
    public List<FuncionEntidad> buscarFuncionesPorIdSalaYIdPelicula(int idSala, int idPelicula) throws PersistenciaException {
        
        List<FuncionEntidad> funciones = new ArrayList<>();
        
        try {
            FuncionEntidad funcion = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    precio,
                                    empezarFuncion,
                                    terminarFuncion,
                                    dia_funcion,
                                    pelicula_id,
                                    sala_id
                               FROM Funcion
                               WHERE sala_id = ? AND pelicula_id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idSala);
            preparedStatement.setInt(2, idPelicula);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                funcion = new FuncionEntidad(
                    resultado.getInt("id"),
                    resultado.getFloat("precio"),
                    resultado.getTime("empezarFuncion"),
                    resultado.getTime("terminarFuncion"),
                    resultado.getString("dia_funcion"),
                    resultado.getInt("pelicula_id"),
                    resultado.getInt("sala_id")
                );
                funciones.add(funcion);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return funciones;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }     
    
    
        
    private FuncionEntidad funcionEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        float precio = resultado.getFloat("precio");
        Time empezarFuncion = resultado.getTime("empezarFuncion");
        Time terminarFuncion = resultado.getTime("terminarFuncion");
        String dia_funcion = resultado.getString("dia_funcion");
        int pelicula_id = resultado.getInt("pelicula_id");
        int sala_id = resultado.getInt("sala_id");

        return new FuncionEntidad(id, precio, empezarFuncion, terminarFuncion, dia_funcion, pelicula_id, sala_id);
    }  

    
    private FuncionTablaDTO funcionTablaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        float precio = resultado.getFloat("precio");
        Time empezarFuncion = resultado.getTime("empezarFuncion");
        Time terminarFuncion = resultado.getTime("terminarFuncion");
        String dia_funcion = resultado.getString("dia_funcion");
        int pelicula_id = resultado.getInt("pelicula_id");
        int sala_id = resultado.getInt("sala_id");

        return new FuncionTablaDTO(id, precio, empezarFuncion, terminarFuncion, dia_funcion, pelicula_id, sala_id);
    }
    
}
