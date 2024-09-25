/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.IClasificacionDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo ITSON
 */
public class ClasificacionNegocio implements IClasificacionNegocio{
    
    IClasificacionDAO clasificacionDAO;

    
    public ClasificacionNegocio(IClasificacionDAO clasificacionDAO) {
        this.clasificacionDAO = clasificacionDAO;
    }
    
    
    @Override
    public List<String> obtenerClasificaciones() throws NegocioException {
        
        List<String> ciudades;
        try {
            ciudades = clasificacionDAO.obtenerClasificaciones();
            return ciudades;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
