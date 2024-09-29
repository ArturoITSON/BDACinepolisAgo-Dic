/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.FuncionDTO;
import DTOs.FuncionFiltroTablaDTO;
import DTOs.FuncionGuardarDTO;
import DTOs.FuncionTablaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IFuncionNegocio {
    
    List<FuncionTablaDTO> buscarFuncionesTabla(FuncionFiltroTablaDTO filtro) throws NegocioException;
    
    FuncionDTO guardar(FuncionGuardarDTO funcion) throws NegocioException;
    
    FuncionDTO eliminar(int idFuncion) throws NegocioException;
    
    List<FuncionDTO> buscarFuncionesPorIdSala(int idSala) throws NegocioException;
    
    List<FuncionDTO> buscarFuncionesPorIdSalaYIdPelicula(int idSala, int idPelilcula) throws NegocioException;
    
    FuncionDTO buscarFuncionPorId(int idFuncion) throws NegocioException;        
}
