/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ISucursalNegocio {
    
    List<String> obtenerSucursales() throws NegocioException;
    
}
