/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.CiudadDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ICiudadNegocio {
    
    List<String> obtenerCiudades() throws NegocioException;

    List<CiudadDTO> obtenerCiudadesDTO() throws NegocioException;
    
    CiudadDTO buscarPorId(int id) throws NegocioException;
}
