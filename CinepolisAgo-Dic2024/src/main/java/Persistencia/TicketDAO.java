/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.TicketDTO;
import DTOs.TicketFiltroTablaDTO;
import DTOs.TicketGuardarDTO;
import DTOs.TicketTablaDTO;
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
public class TicketDAO implements ITicketDAO {

    private IConexionBD conexionBD;
    private Connection conexionGeneral;

    public TicketDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;

    }

    @Override
    public TicketEntidad guardar(TicketGuardarDTO ticket) throws PersistenciaException {

        try {
            Connection conexion = this.conexionBD.crearConexion();
            String insertTicket = """
                                    INSERT INTO Ticket (QR,
                                                          precio,
                                                          metodoPago,
                                                          cliente_id,
                                                          funcion_id
                                                          
                                   ) 
                                                 VALUES (?, ?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexion.prepareStatement(insertTicket, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ticket.getQR());
            preparedStatement.setFloat(2, ticket.getPrecio());
            preparedStatement.setString(3, ticket.getMetodoPago());
            preparedStatement.setInt(4, ticket.getCliente_id());
            preparedStatement.setInt(5, ticket.getFuncion_id());

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
            System.out.println("aqui");
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
                                    id,
                                    QR,
                                    precio,
                                    metodoPago,
                                    cliente_id,
                                    funcion_id
                               FROM Ticket
                               WHERE cliente_id = ?
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
                                    precio,
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
                        resultado.getFloat("precio"),
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

    @Override
    public List<TicketTablaDTO> buscarTicketsTabla(TicketFiltroTablaDTO filtro) throws PersistenciaException {
        try {
            List<TicketTablaDTO> ticketLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();

            // Ajustar la consulta para hacerla más general
            String codigoSQL = """
                            SELECT 
                                t.id AS ticket_id,
                                t.QR,
                                t.precio,
                                t.metodoPago,
                                t.cliente_id,
                                t.funcion_id,
                                p.titulo AS peliculaTitulo, 
                                f.empezarFuncion AS horaFuncion
                            FROM 
                                Ticket t
                            JOIN 
                                Funcion f ON t.funcion_id = f.id
                            JOIN 
                                Pelicula p ON f.pelicula_id = p.id
                            WHERE 
                                p.titulo LIKE ?
                            LIMIT ? 
                            OFFSET ?
                           """;

            // Si el filtro está vacío, traerá todos los registros
            String filtroBusqueda = filtro.getFiltro().isEmpty() ? "%" : "%" + filtro.getFiltro() + "%";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, filtroBusqueda);
            preparedStatement.setInt(2, filtro.getLimit());
            preparedStatement.setInt(3, filtro.getOffset());

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                TicketTablaDTO ticket = new TicketTablaDTO(
                        resultado.getInt("ticket_id"),
                        resultado.getString("QR"),
                        resultado.getFloat("precio"),
                        resultado.getString("metodoPago"),
                        resultado.getInt("cliente_id"),
                        resultado.getInt("funcion_id"),
                        resultado.getString("peliculaTitulo"), // Título de la película
                        resultado.getString("horaFuncion") // Hora de la función
                );
                ticketLista.add(ticket);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return ticketLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }

    private TicketEntidad ticketEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String qr = resultado.getString("QR");
        String metodoPago = resultado.getString("metodoPago");
        float precio = resultado.getFloat("precio");
        int cliente_id = resultado.getInt("cliente_id");
        int funcion_id = resultado.getInt("funcion_id");
        return new TicketEntidad(id, qr, precio, metodoPago, cliente_id, funcion_id);
    }

    private TicketTablaDTO ticketTablaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String qr = resultado.getString("QR");
        String metodoPago = resultado.getString("metodoPago");
        float precio = resultado.getFloat("precio");
        int cliente_id = resultado.getInt("cliente_id");
        int funcion_id = resultado.getInt("funcion_id");

        return new TicketTablaDTO(id, qr, precio, metodoPago, cliente_id, funcion_id);
    }
}
