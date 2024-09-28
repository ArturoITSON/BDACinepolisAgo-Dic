/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.GeneroDTO;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author eduar
 */
public interface IGeneroNegocio {
    
    List<String> obtenerGeneros() throws NegocioException;
    
    GeneroDTO buscarGeneroPorId(int idGenero) throws PersistenciaException;
    
}
