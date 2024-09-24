/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.PeliculaDTO;
import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaGuardarDTO;
import DTOs.PeliculaTablaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IPeliculaNegocio {
    
    List<PeliculaTablaDTO> buscarPeliculasTabla(PeliculaFiltroTablaDTO filtro) throws NegocioException;
    
    PeliculaDTO guardar(PeliculaGuardarDTO pelicula) throws NegocioException;
    
}
