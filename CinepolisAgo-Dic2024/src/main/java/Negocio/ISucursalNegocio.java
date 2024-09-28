/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.SucursalDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ISucursalNegocio {
    
    List<String> obtenerSucursales() throws NegocioException;
    
    List<String> obtenerSucursalesPorCiudad(String ciudad) throws NegocioException;
    
    public List<SucursalDTO> buscarSucursalPorIdCiudad(int idCiudad) throws NegocioException;
    
    List<SucursalDTO> buscarSucursalesPorNombre(String nombreSucursal) throws NegocioException;
    
}
