/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.ClienteGuardarDTO;
import Entidades.ClienteEntidad;
import java.sql.Connection;
import java.sql.Date;
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
public class ClienteDAO implements IClienteDAO {

    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    
    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
   @Override
    public ClienteEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            ClienteEntidad cliente = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    nombres,
                                    apellido_paterno,
                                    apellido_materno,
                                    correo,
                                    fecha_nacimiento,
                                    contrasena,
                                    ciudad_id
                               FROM Cliente
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                cliente = this.clienteEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return cliente;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }    
    
    
    @Override
    public ClienteEntidad guardar(ClienteGuardarDTO cliente) throws PersistenciaException {
        
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String insertCliente = """
                                    INSERT INTO Cliente (nombres,
                                                          apellido_paterno,
                                                          apellido_materno,
                                                          correo,
                                                          fecha_nacimiento,
                                                          contrasena,
                                                          ciudad_id
                                   ) 
                                                 VALUES (?, ?, ?, ?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setString(4, cliente.getCorreo());
            preparedStatement.setDate(5, cliente.getNacimiento());
            preparedStatement.setString(6, cliente.getContrasena());
            preparedStatement.setInt(7, cliente.getCiudad());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción del cliente falló, no se pudo insertar el registro.");
            }

            int idCliente = 0;
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                idCliente = (resultado.getInt(1));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return this.buscarPorId(idCliente);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } 
        
    }
    
    
    
    private ClienteEntidad clienteEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombres");
        String paterno = resultado.getString("apellido_paterno");
        String materno = resultado.getString("apellido_materno");
        String correo = resultado.getString("correo");
        Date nacimiento = resultado.getDate("fecha_nacimiento");
        String contrasena = resultado.getString("contrasena");
        int ciudad = resultado.getInt("ciudad_id");
        return new ClienteEntidad(id, nombre, paterno, materno, nacimiento, ciudad, contrasena, correo);
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
    
    
    

}
