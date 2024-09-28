/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.SucursalDTO;
import Entidades.SucursalEntidad;
import Persistencia.ISucursalDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo ITSON
 */
public class SucursalNegocio implements ISucursalNegocio {

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

    @Override
    public List<String> obtenerSucursalesPorCiudad(String ciudad) throws NegocioException {
        try {
            return sucursalDAO.obtenerSucursalesPorCiudad(ciudad);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener las sucursales por ciudad.");
        }
    }
    
    @Override
    public List<SucursalDTO> buscarSucursalPorIdCiudad(int idCiudad) throws NegocioException {

        try{

            List<SucursalEntidad> sucursalesEntidad = sucursalDAO.buscarSucursalesPorIdCiudad(idCiudad);
            List<SucursalDTO> sucursalesDTO = new ArrayList<>();

        for (SucursalEntidad entidad : sucursalesEntidad) {
            sucursalesDTO.add(convertirASucursalDTO(entidad));
        }
             return sucursalesDTO;
 
        }

        catch(PersistenciaException ex){
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    private SucursalDTO convertirASucursalDTO(SucursalEntidad sucursal) {
        return new SucursalDTO(
                sucursal.getId(),
                sucursal.getNombre(),
                sucursal.getCiudad_id()
        );
    }

}
