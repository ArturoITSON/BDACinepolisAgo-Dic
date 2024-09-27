/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.SalaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ISalaDAO {
    
    List<String> obtenerSalas() throws PersistenciaException;
    
    List<String> obtenerSalasPorSucursal(int idSucursal) throws PersistenciaException;
    
    List<SalaDTO> obtenerIdSalasPorSucursal(int idSucursal) throws PersistenciaException;
    
}
