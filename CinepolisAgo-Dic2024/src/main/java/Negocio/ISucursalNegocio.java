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
    
    List<SucursalDTO> obtenerSucursalDTO() throws NegocioException;
    
    SucursalDTO buscarPorId(int id) throws NegocioException;
    
    List<SucursalDTO> buscarSucursalPorIdCiudad(int idCiudad) throws NegocioException;
    
}
