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
            Connection conexion = this.conexionBD.crearConexion();
            String insertCliente = """
                                    INSERT INTO Funcion (precio,
                                                          dia_funcion,
                                                          pelicula_id,
                                                          sala_id
                                   ) 
                                                 VALUES (?, ?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, funcion.getPrecio());
            preparedStatement.setString(2, funcion.getDiaFuncion());
            preparedStatement.setInt(3, funcion.getPelicula_id());
            preparedStatement.setInt(4, funcion.getSala_id());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción de la funcion falló, no se pudo insertar el registro.");
            }

            int idFuncion = 0;
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                idFuncion = (resultado.getInt(1));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return this.buscarPorId(idFuncion);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
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
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        FuncionEntidad funcionEliminada = this.buscarPorId(idFuncion);
        
            try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Funcion WHERE id = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, funcionEliminada.getId());

            preparedStatement.executeUpdate();
            
            return funcionEliminada;
            
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar la pelicula: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
    
    
    
    
        
    private FuncionEntidad funcionEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        float precio = resultado.getFloat("precio");
        String dia_funcion = resultado.getString("dia_funcion");
        int pelicula_id = resultado.getInt("pelicula_id");
        int sala_id = resultado.getInt("sala_id");

        return new FuncionEntidad(id, precio, dia_funcion, pelicula_id, sala_id);
    }  

    
    private FuncionTablaDTO funcionTablaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        float precio = resultado.getFloat("precio");
        String dia_funcion = resultado.getString("dia_funcion");
        int pelicula_id = resultado.getInt("pelicula_id");
        int sala_id = resultado.getInt("sala_id");

        return new FuncionTablaDTO(id, precio, dia_funcion, pelicula_id, sala_id);
    }
    
}
