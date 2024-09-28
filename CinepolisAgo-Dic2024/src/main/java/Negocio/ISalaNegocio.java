/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.SalaDTO;
import DTOs.SalaFiltroTablaDTO;
import DTOs.SalaGuardarDTO;
import DTOs.SalaModificarDTO;
import DTOs.SalaTablaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface ISalaNegocio {
    
    List<String> obtenerSalas() throws NegocioException;
    
    List<String> obtenerSalasPorSucursal(int idSucursal) throws NegocioException;
    
    List<SalaDTO> obtenerIdSalasPorSucursal(int idSucursal) throws NegocioException;
    
    List<SalaTablaDTO> buscarSalasTabla(SalaFiltroTablaDTO filtro) throws NegocioException;
    
    public SalaDTO guardar(SalaGuardarDTO sala) throws NegocioException;
    
    public SalaDTO eliminar(int idSala) throws NegocioException;
    
    public SalaDTO modificar(SalaModificarDTO sala) throws NegocioException;
    
    SalaDTO buscarSalasPorNombre(String nombreSucursal) throws NegocioException;
    
}
