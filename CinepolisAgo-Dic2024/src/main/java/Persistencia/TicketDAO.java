/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.TicketDTO;
import DTOs.TicketGuardarDTO;
import Entidades.TicketEntidad;
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
public class TicketDAO implements ITicketDAO{
    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    @Override
    public TicketEntidad guardar(TicketGuardarDTO ticket) throws PersistenciaException {

        try {
            Connection conexion = this.conexionBD.crearConexion();
            String insertTicket = """
                                    INSERT INTO Ticket (QR,
                                                          metodoPago,
                                                          cliente_id,
                                                          funcion_id
                                                          
                                   ) 
                                                 VALUES (?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexion.prepareStatement(insertTicket, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ticket.getQR());
            preparedStatement.setString(2, ticket.getMetodoPago());
            preparedStatement.setInt(2, ticket.getCliente_id());
            preparedStatement.setInt(3, ticket.getFuncion_id());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción de el ticket falló, no se pudo insertar el registro.");
            }

            int idTicket = 0;
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                idTicket = (resultado.getInt(1));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return this.buscarPorId(idTicket);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }

    }
    
    @Override
    public TicketEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            TicketEntidad ticket = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    QR,
                                    metodoPago,
                                    cliente_id,
                                    funcion_id
                               FROM Ticket
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                ticket = this.ticketEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return ticket;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
    
    @Override
    public List<TicketEntidad> buscarTicketsPorId(int id) throws PersistenciaException {
        
        List<TicketEntidad> tickets = new ArrayList<>();
        
        try {
            TicketEntidad ticket = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    QR,
                                    metodoPago,
                                    cliente_id,
                                    funcion_id
                               FROM Ticket
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                ticket = new TicketEntidad(
                    resultado.getInt("id"),
                    resultado.getString("QR"),
                    resultado.getString("metodoPago"),
                    resultado.getInt("pelicula_id"),
                    resultado.getInt("sala_id")
                );
                tickets.add(ticket);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return tickets;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    } 
    
    private TicketEntidad ticketEntidad(ResultSet resultado) throws SQLException {
        String qr = resultado.getString("QR");
        String metodoPago = resultado.getString("metodoPago");
        int cliente_id = resultado.getInt("cliente_id");
        int funcion_id = resultado.getInt("funcion_id");
        return new TicketEntidad(funcion_id, qr, metodoPago, cliente_id, funcion_id);
    }
}
