/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.ClienteBuscarDTO;
import DTOs.ClienteGuardarDTO;
import Entidades.ClienteEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IClienteDAO {
    
    ClienteEntidad buscarPorId(int id) throws PersistenciaException;
    ClienteEntidad buscarCliente(ClienteBuscarDTO cliente) throws PersistenciaException;
    ClienteEntidad guardar(ClienteGuardarDTO cliente) throws PersistenciaException;
        
    boolean existeCorreo(String correo) throws PersistenciaException;
}
