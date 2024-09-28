/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.GeneroEntidad;
import java.util.List;

/**
 *
 * @author eduar
 */
public interface IGeneroDAO {
    
    List<String> obtenerGeneros() throws PersistenciaException;
    
    GeneroEntidad buscarGeneroPorId(int idGenero) throws PersistenciaException;
    
}
