/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ICiudadDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo ITSON
 */
public class CiudadNegocio implements ICiudadNegocio{
    
    
    ICiudadDAO ciudadDAO;

    
    public CiudadNegocio(ICiudadDAO ciudadDAO) {
        this.ciudadDAO = ciudadDAO;
    }
    
    
    @Override
    public List<String> obtenerCiudades() throws NegocioException {
        
        List<String> ciudades;
        try {
            ciudades = ciudadDAO.obtenerCiudades();
            return ciudades;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}