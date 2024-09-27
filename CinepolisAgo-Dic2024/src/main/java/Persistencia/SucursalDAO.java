/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public class SucursalDAO implements ISucursalDAO{

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

    }
    
