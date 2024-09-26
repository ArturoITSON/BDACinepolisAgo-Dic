/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Arturo ITSON
 */
public class FuncionGuardarDTO {
    
    private float precio;
    private String diaFuncion;
    private int pelicula_id;
    private int sala_id;

    
    
    public FuncionGuardarDTO() {
    }
 
    
    public FuncionGuardarDTO(float precio, String diaFuncion, int pelicula_id, int sala_id) {
        this.precio = precio;
        this.diaFuncion = diaFuncion;
        this.pelicula_id = pelicula_id;
        this.sala_id = sala_id;
    }

    
    

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDiaFuncion() {
        return diaFuncion;
    }

    public void setDiaFuncion(String diaFuncion) {
        this.diaFuncion = diaFuncion;
    }

    public int getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(int pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public int getSala_id() {
        return sala_id;
    }

    public void setSala_id(int sala_id) {
        this.sala_id = sala_id;
    }

    @Override
    public String toString() {
        return "FuncionGuardarDTO{" + "precio=" + precio + ", diaFuncion=" + diaFuncion + ", pelicula_id=" + pelicula_id + ", sala_id=" + sala_id + '}';
    }


    
    
}
