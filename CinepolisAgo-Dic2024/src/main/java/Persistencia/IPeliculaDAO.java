/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaGuardarDTO;
import DTOs.PeliculaModificarDTO;
import DTOs.PeliculaTablaDTO;
import Entidades.PeliculaEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IPeliculaDAO {
    
    List<PeliculaTablaDTO> buscarPeliculasTabla(PeliculaFiltroTablaDTO filtro) throws PersistenciaException;
    
    PeliculaEntidad guardar(PeliculaGuardarDTO pelicula) throws PersistenciaException;
    
    PeliculaEntidad buscarPorId(int id) throws PersistenciaException;
    
    PeliculaEntidad modificarPelicula(PeliculaModificarDTO pelicula) throws PersistenciaException;
    
    PeliculaEntidad eliminarPelicula(int idPelicula) throws PersistenciaException;
    
}
