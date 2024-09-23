/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.ClienteDTO;
import DTOs.ClienteGuardarDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IClienteNegocio {
 
    ClienteDTO guardar(ClienteGuardarDTO cliente) throws NegocioException;

    List<String> obtenerCiudades() throws NegocioException;
    
}
