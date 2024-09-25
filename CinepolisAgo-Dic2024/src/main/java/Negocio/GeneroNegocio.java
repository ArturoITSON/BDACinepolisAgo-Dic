/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.IGeneroDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class GeneroNegocio implements IGeneroNegocio {

    IGeneroDAO generoDAO;

    
    public GeneroNegocio(IGeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }
    
    
    @Override
    public List<String> obtenerGeneros() throws NegocioException {
        
        List<String> generos;
        try {
            generos = generoDAO.obtenerGeneros();
            return generos;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
