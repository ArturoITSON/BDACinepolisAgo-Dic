/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.SalaDTO;
import DTOs.SalaFiltroTablaDTO;
import DTOs.SalaGuardarDTO;
import DTOs.SalaModificarDTO;
import DTOs.SalaTablaDTO;
import Entidades.SalaEntidad;
import Persistencia.ISalaDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilerias.Utilidades;

/**
 *
 * @author Arturo ITSON
 */
public class SalaNegocio implements ISalaNegocio {

    ISalaDAO salaDAO;

    public SalaNegocio(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    private SalaDTO convertirASalaDTO(SalaEntidad sala) {
        return new SalaDTO(
                sala.getId(),
                sala.getNombre(),
                sala.getAsientos_disponibles(),
                sala.getPrecio(),
                sala.getMinutosLimpiza(),
                sala.getSucursa_id()
        );
    }

    @Override
    public List<String> obtenerSalas() throws NegocioException {

        List<String> salas;
        try {
            salas = salaDAO.obtenerSalas();
            return salas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<String> obtenerSalasPorSucursal(int idSucursal) throws NegocioException {

        List<String> salas;
        try {
            salas = salaDAO.obtenerSalasPorSucursal(idSucursal);
            return salas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<SalaDTO> obtenerIdSalasPorSucursal(int idSucursal) throws NegocioException {

        List<SalaDTO> salas;
        try {
            salas = salaDAO.obtenerIdSalasPorSucursal(idSucursal);
            return salas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<SalaTablaDTO> buscarSalasTabla(SalaFiltroTablaDTO filtro) throws NegocioException {

        try {
            int offset = this.obtenerOFFSETMySQL(filtro.getLimit(), filtro.getOffset());
            filtro.setOffset(offset);

            List<SalaTablaDTO> clientesLista = this.salaDAO.buscarSalasTabla(filtro);
            if (clientesLista == null) {
                throw new NegocioException("No se encontraron registros con los filtros");
            }
            return clientesLista;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }
    
    @Override
    public SalaDTO guardar(SalaGuardarDTO sala) throws NegocioException{
    
        SalaDTO salaGuardar;
        try {
            salaGuardar = this.convertirASalaDTO(salaDAO.guardar(sala));
            return salaGuardar;
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }

    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }

    @Override
    public SalaDTO modificar(SalaModificarDTO sala) throws NegocioException {

        try {
            System.out.println(sala.toString() + "negocio");

            SalaDTO salaModificar = this.convertirASalaDTO(salaDAO.modificarSala(sala));

            return salaModificar;

        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public SalaDTO eliminar(int idSala) throws NegocioException {

        try {
            SalaDTO clienteEliminar = this.convertirASalaDTO(salaDAO.eliminarSala(idSala));

            return clienteEliminar;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }

}
