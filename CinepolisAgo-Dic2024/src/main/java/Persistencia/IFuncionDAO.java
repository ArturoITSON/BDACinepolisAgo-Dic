/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOs.FuncionFiltroTablaDTO;
import DTOs.FuncionGuardarDTO;
import DTOs.FuncionTablaDTO;
import Entidades.FuncionEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IFuncionDAO {
    
    List<FuncionTablaDTO> buscarFuncionesTabla(FuncionFiltroTablaDTO filtro) throws PersistenciaException;
    
    FuncionEntidad guardar(FuncionGuardarDTO funcion) throws PersistenciaException;
    
    FuncionEntidad buscarPorId(int id) throws PersistenciaException;
    
    
    FuncionEntidad eliminarFuncion(int idFuncion) throws PersistenciaException;
    
    List<FuncionEntidad> buscarFuncionesPorIdSala(int idSala) throws PersistenciaException;
    
    List<FuncionEntidad> buscarFuncionesPorIdSalaYIdPelicula(int idSala, int idPelicula) throws PersistenciaException;
    
}
