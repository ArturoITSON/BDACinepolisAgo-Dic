/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.ClienteBuscarDTO;
import DTOs.ClienteFiltroTablaDTO;
import DTOs.ClienteGuardarDTO;
import DTOs.ClienteModificarDTO;
import DTOs.ClienteTablaDTO;
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
    public List<ClienteTablaDTO> buscarClientesTabla(ClienteFiltroTablaDTO filtro) throws PersistenciaException {
                try {
            List<ClienteTablaDTO> clienteLista = null;
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
                                FROM cliente
                                WHERE CONCAT(nombres, ' ', correo, ' ', apellido_paterno) LIKE ?
                                LIMIT ? 
                                OFFSET ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, "%" + filtro.getFiltro() + "%");
            preparedStatement.setInt(2, filtro.getLimit());
            preparedStatement.setInt(3, filtro.getOffset());

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                if (clienteLista == null) {
                    clienteLista = new ArrayList<>();
                }
                clienteLista.add(this.clienteTablaDTO(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return clienteLista;
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
    
    @Override
    public ClienteEntidad modificarCliente(ClienteModificarDTO cliente) throws PersistenciaException {
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        
        ClienteEntidad clienteEditada = new ClienteEntidad();
        clienteEditada.setIdCliente(cliente.getId());
        clienteEditada.setNombres(cliente.getNombres());
        clienteEditada.setApellidoPaterno(cliente.getApellidoPaterno());
        clienteEditada.setApelldioMaterno(cliente.getApellidoMaterno());
        clienteEditada.setCorreo(cliente.getCorreo());
        clienteEditada.setNacimiento(cliente.getNacimiento());
        clienteEditada.setContrasena(cliente.getContrasena());
        clienteEditada.setCiudad(cliente.getCiudad());
        
        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "UPDATE Cliente SET nombres = ?, apellido_paterno = ?, apellido_materno = ?, correo = ?, fecha_nacimiento = ?, contrasena = ?, ciudad_id = ? WHERE id = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, clienteEditada.getNombres());
            preparedStatement.setString(2, clienteEditada.getApellidoPaterno());
            preparedStatement.setString(3, clienteEditada.getApelldioMaterno());
            preparedStatement.setString(4, clienteEditada.getCorreo());
            preparedStatement.setDate(5, clienteEditada.getNacimiento());
            preparedStatement.setString(6, clienteEditada.getContrasena());
            preparedStatement.setInt(7, clienteEditada.getCiudad());
            preparedStatement.setInt(8, clienteEditada.getIdCliente());

            
            
            preparedStatement.executeUpdate();
            System.out.println(clienteEditada.toString() + "dao");
            return clienteEditada;
            
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al editar la pelicula: " + ex.getMessage());
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
    
    
    @Override
    public ClienteEntidad eliminarCliente(int idCliente) throws PersistenciaException {
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ClienteEntidad clienteEliminada = this.buscarPorId(idCliente);
        
            try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Cliente WHERE id = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idCliente);

            preparedStatement.executeUpdate();
            
            return clienteEliminada;
            
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar el cliente: " + ex.getMessage());
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

    @Override
    public ClienteEntidad buscarCliente(ClienteBuscarDTO cliente) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();

            // Consulta solo por correo, sin comparar la contraseña
            String sentenciaSql = """
            SELECT id, nombres, apellido_paterno, apellido_materno, correo, 
                   fecha_nacimiento, contrasena, ciudad_id
            FROM Cliente WHERE correo = ?
        """;

            PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
            comandoSQL.setString(1, cliente.getCorreo());

            ResultSet resultado = comandoSQL.executeQuery();

            if (resultado.next()) {
                return new ClienteEntidad(
                        resultado.getInt("id"),
                        resultado.getString("nombres"),
                        resultado.getString("apellido_paterno"),
                        resultado.getString("apellido_materno"),
                        resultado.getString("correo"),
                        resultado.getDate("fecha_nacimiento"),
                        resultado.getString("contrasena"),
                        resultado.getInt("ciudad_id")
                );
            } else {
                return null; // Retorna null si no encuentra el cliente
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PersistenciaException("Error al buscar cliente: " + ex.getMessage());
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
        return new ClienteEntidad(id, nombre, paterno, materno, correo, nacimiento, contrasena, ciudad);
    }


    @Override
    public boolean existeCorreo(String correo) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();

            String sql = "SELECT COUNT(*) FROM Cliente WHERE correo = ?";
            PreparedStatement comandoSQL = conexion.prepareStatement(sql);
            comandoSQL.setString(1, correo);

            ResultSet resultado = comandoSQL.executeQuery();

            if (resultado.next()) {
                int count = resultado.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PersistenciaException("Error al verificar el correo: " + ex.getMessage());
        }
    }
    
    private ClienteTablaDTO clienteTablaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombres = resultado.getString("nombres");
        String apellidoPaterno = resultado.getString("apellido_paterno");
        String apellidoMaterno = resultado.getString("apellido_materno");
        String correo = resultado.getString("correo");
        Date nacimiento = resultado.getDate("fecha_nacimiento");
        String contraseña = resultado.getString("contrasena");
        int ciudad = resultado.getInt("ciudad_id");
        return new ClienteTablaDTO(id, nombres, apellidoPaterno, apellidoMaterno, nacimiento, ciudad, contraseña, correo);
    }
}
