/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.ClienteDTO;
import DTOs.ClienteGuardarDTO;

/**
 *
 * @author Arturo ITSON
 */
public interface IClienteNegocio {
 
    ClienteDTO guardar(ClienteGuardarDTO cliente) throws NegocioException;

    
}
