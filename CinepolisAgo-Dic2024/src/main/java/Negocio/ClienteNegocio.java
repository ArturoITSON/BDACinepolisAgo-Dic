/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.ClienteBuscarDTO;
import DTOs.ClienteDTO;
import DTOs.ClienteFiltroTablaDTO;
import DTOs.ClienteGuardarDTO;
import DTOs.ClienteModificarDTO;
import DTOs.ClienteTablaDTO;
import Entidades.ClienteEntidad;
import Persistencia.IClienteDAO;
import Persistencia.PersistenciaException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;
import utilerias.Utilidades;

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

        // Validar que el cliente tenga al menos 13 años
        LocalDate fechaNacimiento = cliente.getNacimiento().toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        if (edad < 13) {
            throw new NegocioException("Debes tener al menos 13 años para registrarte.");
        }

        try {
            // Verificar si el correo ya está registrado
            if (clienteDAO.existeCorreo(cliente.getCorreo())) {
                throw new NegocioException("El correo electrónico ya está registrado. Utiliza uno diferente.");
            }

            // Encriptar la contraseña
            String hashedPassword = BCrypt.hashpw(cliente.getContrasena(), BCrypt.gensalt());
            cliente.setContrasena(hashedPassword);

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
    public ClienteDTO buscarCliente(ClienteBuscarDTO cliente) throws NegocioException {
        try {
            ClienteEntidad clienteBuscado = clienteDAO.buscarCliente(cliente);
            if (clienteBuscado == null) {
                throw new NegocioException("Usuario o contraseña incorrectos");
            }

            // Verificar la contraseña
            if (BCrypt.checkpw(cliente.getContrasena(), clienteBuscado.getContrasena())) {
                return this.convertirAClienteDTO(clienteBuscado);
            } else {
                throw new NegocioException("Usuario o contraseña incorrectos");
            }
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar cliente");
        }
    }
    
    @Override
    public List<ClienteTablaDTO> buscarClientesTabla(ClienteFiltroTablaDTO filtro) throws NegocioException {
        
        try{
        int offset = this.obtenerOFFSETMySQL(filtro.getLimit(), filtro.getOffset());
        filtro.setOffset(offset);
        
        List<ClienteTablaDTO> clientesLista = this.clienteDAO.buscarClientesTabla(filtro);
            if (clientesLista == null) {
                throw new NegocioException("No se encontraron registros con los filtros");
            }
            return clientesLista;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }
    
    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
    
    @Override
    public ClienteDTO modificar(ClienteModificarDTO cliente) throws NegocioException {

        try {
            System.out.println(cliente.toString() + "negocio");

            ClienteDTO clienteModificar = this.convertirAClienteDTO(clienteDAO.modificarCliente(cliente));

            return clienteModificar;

        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }


    @Override
    public ClienteDTO eliminar(int idCliente) throws NegocioException {

        try {
            ClienteDTO clienteEliminar = this.convertirAClienteDTO(clienteDAO.eliminarCliente(idCliente));

            return clienteEliminar;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }

}
