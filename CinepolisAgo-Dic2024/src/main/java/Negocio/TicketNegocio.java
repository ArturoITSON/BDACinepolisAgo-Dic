/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.FuncionDTO;
import DTOs.TicketDTO;
import DTOs.TicketFiltroTablaDTO;
import DTOs.TicketGuardarDTO;
import DTOs.TicketTablaDTO;
import Entidades.TicketEntidad;
import Persistencia.ITicketDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilerias.Utilidades;

/**
 *
 * @author eduar
 */
public class TicketNegocio implements ITicketNegocio{
    
    ITicketDAO ticketDAO;
    IFuncionNegocio funcionNegocio;

    public TicketNegocio(ITicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
    
    public TicketNegocio(ITicketDAO ticketDAO, IFuncionNegocio funcionNegocio) {
        this.ticketDAO = ticketDAO;
        this.funcionNegocio = funcionNegocio;
    }
    
    private TicketDTO convertirATicketDTO(TicketEntidad ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getQR(),
                ticket.getMetodoPago(),
                ticket.getCliente_id(),
                ticket.getFuncion_id()
        );
    }
    
    
    @Override
    public List<TicketDTO> buscarTicketsPorId(int idTicket) throws NegocioException {
        
        try{
            
            List<TicketEntidad> ticketsEntidad = ticketDAO.buscarTicketsPorId(idTicket);
            List<TicketDTO> ticketsDTO = new ArrayList<>();

            for (TicketEntidad ticket : ticketsEntidad) {
            ticketsDTO.add(convertirATicketDTO(ticket));
        }
          return ticketsDTO;
        }    
             
         catch(PersistenciaException ex){
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return null;
        }
    
    @Override
    public TicketDTO guardar(TicketGuardarDTO ticket) throws NegocioException {

        TicketDTO ticketGuardar;
        try {
            ticketGuardar = this.convertirATicketDTO(ticketDAO.guardar(ticket));
            return ticketGuardar;
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    @Override
public List<TicketTablaDTO> buscarTicketsTabla(TicketFiltroTablaDTO filtro) throws NegocioException {
    try {
        // La consulta ya trae el nombre de la película y la hora de la función
        List<TicketTablaDTO> ticketsLista = this.ticketDAO.buscarTicketsTabla(filtro);
        
        if (ticketsLista == null || ticketsLista.isEmpty()) {
            throw new NegocioException("No se encontraron registros con los filtros");
        }

        // No es necesario iterar y agregar nuevamente los datos de nombre y hora
        return ticketsLista;
        
    } catch (PersistenciaException ex) {
        System.out.println(ex.getMessage());
        throw new NegocioException(ex.getMessage());
    }
}

    
    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
}
