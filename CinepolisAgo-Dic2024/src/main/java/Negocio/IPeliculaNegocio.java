/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.PeliculaDTO;
import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaGuardarDTO;
import DTOs.PeliculaModificarDTO;
import DTOs.PeliculaTablaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IPeliculaNegocio {
    
    List<PeliculaTablaDTO> buscarPeliculasTabla(PeliculaFiltroTablaDTO filtro) throws NegocioException;
    
    PeliculaDTO guardar(PeliculaGuardarDTO pelicula) throws NegocioException;
    
    PeliculaDTO modificar(PeliculaModificarDTO pelicula) throws NegocioException;
    
    PeliculaDTO eliminar(int idPelicula) throws NegocioException;
    
    PeliculaDTO buscarPorId(int id) throws NegocioException;
    
    PeliculaDTO buscarPorNombre(String titulo) throws NegocioException;
    
    List<PeliculaDTO> obtenerPeliculasDTO() throws NegocioException;
    
    List<String> obtenerPeliculas() throws NegocioException;
    
    PeliculaDTO obtenerPeliculasPorId(int idPelicula) throws NegocioException;
            
}
