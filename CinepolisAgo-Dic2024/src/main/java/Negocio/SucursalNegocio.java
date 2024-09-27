/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ISucursalDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo ITSON
 */
public class SucursalNegocio implements ISucursalNegocio{
    
    
    ISucursalDAO sucursalDAO;

    
    public SucursalNegocio(ISucursalDAO sucursalDAO) {
        this.sucursalDAO = sucursalDAO;
    }
    
    
    @Override
    public List<String> obtenerSucursales() throws NegocioException {
        
        List<String> sucursales;
        try {
            sucursales = sucursalDAO.obtenerSucursal();
            return sucursales;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
