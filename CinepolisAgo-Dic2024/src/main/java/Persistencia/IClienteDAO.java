/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.ClienteGuardarDTO;
import Entidades.ClienteEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IClienteDAO {
    
    ClienteEntidad buscarPorId(int id) throws PersistenciaException;
    ClienteEntidad guardar(ClienteGuardarDTO cliente) throws PersistenciaException;
    
    List<String> obtenerCiudades() throws PersistenciaException;

}
