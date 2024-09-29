/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.TicketDTO;
import DTOs.TicketFiltroTablaDTO;
import DTOs.TicketGuardarDTO;
import DTOs.TicketTablaDTO;
import java.util.List;

/**
 *
 * @author eduar
 */
public interface ITicketNegocio {
        
    List<TicketDTO> buscarTicketsPorId(int idTicket) throws NegocioException;
    
    TicketDTO guardar(TicketGuardarDTO ticket) throws NegocioException;
    
    List<TicketTablaDTO> buscarTicketsTabla(TicketFiltroTablaDTO filtro) throws NegocioException;
}
