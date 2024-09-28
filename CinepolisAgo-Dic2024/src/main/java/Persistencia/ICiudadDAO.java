/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.CiudadDTO;
import Entidades.CiudadEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ICiudadDAO {
    
    List<String> obtenerCiudades() throws PersistenciaException;
    
    public List<CiudadDTO> obtenerCiudadesDTO() throws PersistenciaException;

    List<CiudadEntidad> obtenerTodasLasCiudades() throws PersistenciaException;
    
    CiudadEntidad buscarPorId(int id) throws PersistenciaException;
}
