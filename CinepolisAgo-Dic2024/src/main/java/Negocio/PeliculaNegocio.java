/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.PeliculaDTO;
import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaGuardarDTO;
import DTOs.PeliculaTablaDTO;
import Entidades.PeliculaEntidad;
import Persistencia.IPeliculaDAO;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilerias.Utilidades;

/**
 *
 * @author Arturo ITSON
 */
public class PeliculaNegocio implements IPeliculaNegocio{

    private IPeliculaDAO peliculaDAO;

    public PeliculaNegocio(IPeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }
    
    @Override
    public List<PeliculaTablaDTO> buscarPeliculasTabla(PeliculaFiltroTablaDTO filtro) throws NegocioException {
        
        try{
        int offset = this.obtenerOFFSETMySQL(filtro.getLimit(), filtro.getOffset());
        filtro.setOffset(offset);
        
        List<PeliculaTablaDTO> clientesLista = this.peliculaDAO.buscarPeliculasTabla(filtro);
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
    public PeliculaDTO guardar(PeliculaGuardarDTO pelicula) throws NegocioException{
    
        PeliculaDTO peliGuardar;
        try {
            peliGuardar = this.convertirAPeliculaDTO(peliculaDAO.guardar(pelicula));
            return peliGuardar;
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
      
    
    
    private PeliculaDTO convertirAPeliculaDTO(PeliculaEntidad pelicula) {
        return new PeliculaDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getClasificacion_id(),
                pelicula.getDuracion(),
                pelicula.getSinopsis(),
                pelicula.getGenero_id(),
                pelicula.getTrailer(),
                pelicula.getLink_imagen(),
                pelicula.getPais_id()
        );
    }
        
        
    private int obtenerOFFSETMySQL(int limit, int pagina) {
        return new Utilidades().RegresarOFFSETMySQL(limit, pagina);
    }
        
        
    }

    

