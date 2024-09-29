/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Arturo ITSON
 */
public class DatosCarteleraDTO {
    
    PeliculaDTO pelicula;
    FuncionDTO funcion;
    SalaDTO sala;
    ClienteBuscarDTO cliente;

    
    
    public DatosCarteleraDTO() {
    }

    public DatosCarteleraDTO(PeliculaDTO pelicula, FuncionDTO funcion, SalaDTO sala, ClienteBuscarDTO cliente) {
        this.pelicula = pelicula;
        this.funcion = funcion;
        this.sala = sala;
        this.cliente = cliente;
    }

    public PeliculaDTO getPelicula() {
        return pelicula;
    }

    public void setPelicula(PeliculaDTO pelicula) {
        this.pelicula = pelicula;
    }

    public FuncionDTO getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionDTO funcion) {
        this.funcion = funcion;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }

    public ClienteBuscarDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBuscarDTO cliente) {
        this.cliente = cliente;
    }
    
    
    
    
}
