/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.TicketGuardarDTO;
import Entidades.TicketEntidad;
import java.util.List;

/**
 *
 * @author eduar
 */
public interface ITicketDAO {
    
    public TicketEntidad guardar(TicketGuardarDTO ticket) throws PersistenciaException;
    
    public TicketEntidad buscarPorId(int id) throws PersistenciaException;
    
    public List<TicketEntidad> buscarTicketsPorId(int id) throws PersistenciaException;
    
}
