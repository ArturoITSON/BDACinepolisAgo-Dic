/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.SucursalEntidad;
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
public class SucursalDAO implements ISucursalDAO {

    private IConexionBD conexionBD;
    private Connection conexionGeneral;

    public SucursalDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<String> obtenerSucursal() throws PersistenciaException {

        List<String> sucursales = new ArrayList<>();
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            // se modifica el select
            String codigoSQL = "SELECT nombre FROM Sucursal";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                sucursales.add(resultado.getString("nombre"));
            }
            return sucursales;
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
public List<String> obtenerSucursalesPorCiudad(String ciudad) throws PersistenciaException {
    List<String> sucursales = new ArrayList<>();
    Connection conexion = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT nombre FROM Sucursal WHERE ciudad = ?"; // Asegúrate de que "ciudad" sea el nombre correcto de la columna
        
        PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
        preparedStatement.setString(1, ciudad); // Filtra por la ciudad seleccionada
        
        resultado = preparedStatement.executeQuery();
        
        // Verificar si la consulta devuelve resultados
        while (resultado.next()) {
            sucursales.add(resultado.getString("nombre"));
        }
        
        // Depuración: Imprimir las sucursales encontradas
        System.out.println("Sucursales encontradas para la ciudad " + ciudad + ": " + sucursales);

        return sucursales;
    } catch (SQLException ex) {
        throw new PersistenciaException("Error al obtener las sucursales por ciudad.");
    } finally {
        try {
            if (resultado != null) {
                resultado.close();
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
    public List<SucursalEntidad> buscarSucursalesPorIdCiudad(int idCiudad) throws PersistenciaException {

        List<SucursalEntidad> sucursales = new ArrayList<>();

        try {
            SucursalEntidad sucursal = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombre,
                                    ciudad_id
                               FROM Sucursal
                               WHERE ciudad_id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idCiudad);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                sucursal = new SucursalEntidad(
                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getInt("ciudad_id")
                );
                sucursales.add(sucursal);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return sucursales;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    

}
