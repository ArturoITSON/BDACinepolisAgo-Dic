/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.SalaDTO;
import Persistencia.ISalaDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo ITSON
 */
public class SalaNegocio implements ISalaNegocio{

    ISalaDAO salaDAO;

    
    public SalaNegocio(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }
    
    
    @Override
    public List<String> obtenerSalas() throws NegocioException {
        
        List<String> salas;
        try {
            salas = salaDAO.obtenerSalas();
            return salas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @Override
    public List<String> obtenerSalasPorSucursal(int idSucursal) throws NegocioException {
        
        List<String> salas;
        try {
            salas = salaDAO.obtenerSalasPorSucursal(idSucursal);
            return salas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<SalaDTO> obtenerIdSalasPorSucursal(int idSucursal) throws NegocioException {
        
        List<SalaDTO> salas;
        try {
            salas = salaDAO.obtenerIdSalasPorSucursal(idSucursal);
            return salas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    }
    
    
    
}
