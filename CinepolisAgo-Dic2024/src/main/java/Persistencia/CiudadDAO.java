/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import DTOs.CiudadDTO;
import Entidades.CiudadEntidad;
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
public class CiudadDAO implements ICiudadDAO {
    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    
    public CiudadDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    @Override
    public List<String> obtenerCiudades() throws PersistenciaException {
    List<String> ciudades = new ArrayList<>();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT nombre FROM Ciudad";
        comandoSQL = conexion.createStatement();
        resultado = comandoSQL.executeQuery(codigoSQL);

        while (resultado.next()) {
            ciudades.add(resultado.getString("nombre"));
        }
        return ciudades;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de ciudades, inténtelo de nuevo.");
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
    public List<CiudadDTO> obtenerCiudadesDTO() throws PersistenciaException {
    List<CiudadDTO> ciudades = new ArrayList<>();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT id, nombre FROM Ciudad"; // Ahora seleccionamos el ID también
        comandoSQL = conexion.createStatement();
        resultado = comandoSQL.executeQuery(codigoSQL);

        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            CiudadDTO ciudadDTO = new CiudadDTO(id, nombre); // Crear una instancia de CiudadDTO
            ciudades.add(ciudadDTO);
        }
        return ciudades;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de ciudades, inténtelo de nuevo.");
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
    public List<CiudadEntidad> obtenerTodasLasCiudades() throws PersistenciaException {
        List<CiudadEntidad> ciudades = new ArrayList<>();
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre FROM Ciudad";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                CiudadEntidad ciudad = new CiudadEntidad(
                    resultado.getInt("id"),
                    resultado.getString("nombre")
                );
                ciudades.add(ciudad);
            }
            
        return ciudades;
        
        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al leer la base de datos de peliculas, inténtelo de nuevo.");
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (comandoSQL != null) comandoSQL.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
    }
}
    
    
    @Override
    public CiudadEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            CiudadEntidad ciudad = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombre
                               FROM Ciudad
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                ciudad = this.ciudadEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return ciudad;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    
        private CiudadEntidad ciudadEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        return new CiudadEntidad(id, nombre);
    }

}
