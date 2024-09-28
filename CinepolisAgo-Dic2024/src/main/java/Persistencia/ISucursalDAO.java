/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.SucursalEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ISucursalDAO {
    
    List<String> obtenerSucursal() throws PersistenciaException;
    
    SucursalEntidad buscarPorId(int id) throws PersistenciaException;
    
    List<SucursalEntidad> obtenerTodasLasSucursales() throws PersistenciaException;
    
    List<SucursalEntidad> buscarSucursalesPorIdCiudad(int idCiudad) throws PersistenciaException;
}
