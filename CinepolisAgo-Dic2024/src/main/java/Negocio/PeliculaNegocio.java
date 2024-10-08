/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.PeliculaDTO;
import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaGuardarDTO;
import DTOs.PeliculaModificarDTO;
import DTOs.PeliculaTablaDTO;
import Entidades.PeliculaEntidad;
import Persistencia.IPeliculaDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
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
      
    
    @Override
    public PeliculaDTO modificar(PeliculaModificarDTO pelicula) throws NegocioException {

        try {
            System.out.println(pelicula.toString() + "negocio");

            PeliculaDTO peliculaModificar = this.convertirAPeliculaDTO(peliculaDAO.modificarPelicula(pelicula));
            
            return peliculaModificar;
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    
    @Override
    public PeliculaDTO eliminar(int idPelicula) throws NegocioException {
        
        try {
            PeliculaDTO peliculaEliminar = this.convertirAPeliculaDTO(peliculaDAO.eliminarPelicula(idPelicula));
            
            return peliculaEliminar;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    @Override
    public List<String> obtenerPeliculas() throws NegocioException {
        
        List<String> peliculas;
        try {
            peliculas = peliculaDAO.obtenerPeliculas();
            return peliculas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<PeliculaDTO> obtenerPeliculasDTO() throws NegocioException {
    try {
        List<PeliculaEntidad> peliculasEntidad = peliculaDAO.obtenerTodasLasPeliculas();
        List<PeliculaDTO> peliculasDTO = new ArrayList<>();

        for (PeliculaEntidad entidad : peliculasEntidad) {
            peliculasDTO.add(convertirAPeliculaDTO(entidad));
        }

        return peliculasDTO;
    } catch (PersistenciaException ex) {
        Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
        throw new NegocioException("Error al obtener la lista de películas");
    }
}

    
    
    @Override
    public PeliculaDTO buscarPorId(int id) throws NegocioException {
        
        try{
             return this.convertirAPeliculaDTO(peliculaDAO.buscarPorId(id));
 
        }
        
        catch(PersistenciaException ex){
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public PeliculaDTO buscarPorNombre(String titulo) throws NegocioException {
    try {
        PeliculaEntidad peliculaEntidad = peliculaDAO.buscarPorTitulo(titulo);
        
        if (peliculaEntidad == null) {
            throw new NegocioException("La película con título \"" + titulo + "\" no fue encontrada.");
        }
        
        return this.convertirAPeliculaDTO(peliculaEntidad);
        
    } catch (PersistenciaException ex) {
        Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
        throw new NegocioException(ex.getMessage());
    }
}
    
    
    
    @Override
    public PeliculaDTO obtenerPeliculasPorId(int idPelicula) throws NegocioException {
        
        PeliculaDTO peliculas;
        try {
            peliculas = this.convertirAPeliculaDTO(peliculaDAO.obtenerPeliculas(idPelicula));
            return peliculas;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    }
    
    
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

    

