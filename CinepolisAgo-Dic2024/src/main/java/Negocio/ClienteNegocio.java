/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.ClienteDTO;
import DTOs.ClienteGuardarDTO;
import Entidades.ClienteEntidad;
import Persistencia.IClienteDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo ITSON
 */
public class ClienteNegocio implements IClienteNegocio {

    IClienteDAO clienteDAO;


    
    public ClienteNegocio(IClienteDAO clienteDao) {
        this.clienteDAO = clienteDao;
    }
    
    
    
    @Override
    public ClienteDTO guardar(ClienteGuardarDTO cliente) throws NegocioException {
        
        try {

            ClienteEntidad clienteGuardado = this.clienteDAO.guardar(cliente);
            System.out.println(clienteGuardado);
            return this.convertirAClienteDTO(clienteGuardado);
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());

        }
   
    }
    
    
    
     private ClienteDTO convertirAClienteDTO(ClienteEntidad cliente) {
        return new ClienteDTO(
                cliente.getIdCliente(),
                cliente.getNombres(),
                cliente.getApellidoPaterno(),
                cliente.getApelldioMaterno(),
                cliente.getNacimiento(),
                cliente.getCiudad(),
                cliente.getContrasena(),
                cliente.getCorreo()
        );
    }

    @Override
    public List<String> obtenerCiudades() throws NegocioException {
        
        List<String> ciudades;
        try {
            ciudades = clienteDAO.obtenerCiudades();
            return ciudades;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
    }
    
     
     
}
