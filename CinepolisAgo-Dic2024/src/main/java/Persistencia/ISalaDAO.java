/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.SalaDTO;
import DTOs.SalaFiltroTablaDTO;
import DTOs.SalaGuardarDTO;
import DTOs.SalaModificarDTO;
import DTOs.SalaTablaDTO;
import Entidades.SalaEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ISalaDAO {
    
    List<String> obtenerSalas() throws PersistenciaException;
    
    List<String> obtenerSalasPorSucursal(int idSucursal) throws PersistenciaException;
    
    List<SalaDTO> obtenerIdSalasPorSucursal(int idSucursal) throws PersistenciaException;
    
    List<SalaTablaDTO> buscarSalasTabla(SalaFiltroTablaDTO filtro) throws PersistenciaException;
    
    public SalaEntidad guardar(SalaGuardarDTO pelicula) throws PersistenciaException;
    
    public SalaEntidad modificarSala(SalaModificarDTO sala) throws PersistenciaException;
    
    public SalaEntidad eliminarSala(int idSala) throws PersistenciaException;
    
}
