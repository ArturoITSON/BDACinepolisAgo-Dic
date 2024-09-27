/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.FuncionDTO;
import DTOs.FuncionFiltroTablaDTO;
import DTOs.FuncionGuardarDTO;
import DTOs.FuncionTablaDTO;
import Entidades.FuncionEntidad;
import Persistencia.IFuncionDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilerias.Utilidades;

/**
 *
 * @author Arturo ITSON
 */
public class FuncionNegocio implements IFuncionNegocio {

    
    private IFuncionDAO funcionDAO;

    public FuncionNegocio(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }
    
    
    @Override
    public List<FuncionTablaDTO> buscarFuncionesTabla(FuncionFiltroTablaDTO filtro) throws NegocioException {
        
        try{
        int offset = this.obtenerOFFSETMySQL(filtro.getLimit(), filtro.getOffset());
        filtro.setOffset(offset);
        
        List<FuncionTablaDTO> funcionesLista = this.funcionDAO.buscarFuncionesTabla(filtro);
            if (funcionesLista == null) {
                throw new NegocioException("No se encontraron registros con los filtros");
            }
            return funcionesLista;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }    
    }

    
    @Override
    public FuncionDTO guardar(FuncionGuardarDTO funcion) throws NegocioException {

        FuncionDTO funcionGuardar;
        try {
            funcionGuardar = this.convertirAFuncionDTO(funcionDAO.guardar(funcion));
            return funcionGuardar;
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public FuncionDTO eliminar(int idFuncion) throws NegocioException {
        
        try {
            FuncionDTO funcionEliminar = this.convertirAFuncionDTO(funcionDAO.eliminarFuncion(idFuncion));
            
            return funcionEliminar;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    
    
    private FuncionDTO convertirAFuncionDTO(FuncionEntidad funcion) {
        return new FuncionDTO(
                funcion.getId(),
                funcion.getPrecio(),
                funcion.getEmpezaFuncion(),
                funcion.getTerminoFuncion(),
                funcion.getDiaFuncion(),
                funcion.getPelicula_id(),
                funcion.getSala_id()
        );
    }
        
        
    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
    
}
