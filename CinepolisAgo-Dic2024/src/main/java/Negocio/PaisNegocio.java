/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.IPaisDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class PaisNegocio implements IPaisNegocio {

    IPaisDAO paisDAO;

    
    public PaisNegocio(IPaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }
    
    
    @Override
    public List<String> obtenerPaises() throws NegocioException {
        
        List<String> paises;
        try {
            paises = paisDAO.obtenerPaises();
            return paises;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
