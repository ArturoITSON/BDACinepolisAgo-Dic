/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.GeneroEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduar
 */
public class GeneroDAO implements IGeneroDAO{
    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    
    public GeneroDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<String> obtenerGeneros() throws PersistenciaException {
        List<String> generos = new ArrayList<>();
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            // se modifica el select
            String codigoSQL = "SELECT nombre FROM Genero";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                generos.add(resultado.getString("nombre"));
            }
            return generos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos de géneros, inténtelo de nuevo.");
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
    public GeneroEntidad buscarGeneroPorId(int idGenero) throws PersistenciaException{
        try {
            GeneroEntidad genero = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombre
                               FROM Genero
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idGenero);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                genero = this.generoEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return genero;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }  
    
    
    
    
        private GeneroEntidad generoEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        return new GeneroEntidad(id, nombre);
    }
    
}
