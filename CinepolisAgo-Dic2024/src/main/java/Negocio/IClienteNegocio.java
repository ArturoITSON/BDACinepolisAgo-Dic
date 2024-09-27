/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.ClienteBuscarDTO;
import DTOs.ClienteDTO;
import DTOs.ClienteFiltroTablaDTO;
import DTOs.ClienteGuardarDTO;
import DTOs.ClienteModificarDTO;
import DTOs.ClienteTablaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IClienteNegocio {
 
    ClienteDTO guardar(ClienteGuardarDTO cliente) throws NegocioException;
    ClienteDTO modificar(ClienteModificarDTO cliente) throws NegocioException;
    ClienteDTO eliminar(int idCliente) throws NegocioException;
    
    ClienteDTO buscarCliente(ClienteBuscarDTO cliente) throws NegocioException;

    List<ClienteTablaDTO> buscarClientesTabla(ClienteFiltroTablaDTO filtro) throws NegocioException;
}
