/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.CiudadDTO;
import Entidades.CiudadEntidad;
import Persistencia.ICiudadDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
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
    
    
    @Override
    public List<CiudadDTO> obtenerCiudadesDTO() throws NegocioException {
    try {
        List<CiudadEntidad> ciudadesEntidad = ciudadDAO.obtenerTodasLasCiudades();
        List<CiudadDTO> ciudadesDTO = new ArrayList<>();

        for (CiudadEntidad entidad : ciudadesEntidad) {
            ciudadesDTO.add(convertirACiudadDTO(entidad));
        }

        return ciudadesDTO;
    } catch (PersistenciaException ex) {
        Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
        throw new NegocioException("Error al obtener la lista de ciudades");
    }
    
    }
    
    
    @Override
    public CiudadDTO buscarPorId(int id) throws NegocioException {
        
        try{
             return this.convertirACiudadDTO(ciudadDAO.buscarPorId(id));
 
        }
        
        catch(PersistenciaException ex){
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    private CiudadDTO convertirACiudadDTO(CiudadEntidad ciudad) {
        return new CiudadDTO(
                ciudad.getId(),
                ciudad.getNombre()
        );
    }
    
    
}
