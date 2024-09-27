/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.Time;


/**
 *
 * @author Arturo ITSON
 */
public class FuncionEntidad {
 
    private int id;
    private float precio;
    private Time empezaFuncion;
    private Time terminoFuncion;
    private String diaFuncion;
    private int pelicula_id;
    private int sala_id;

    
    
    public FuncionEntidad() {
        
    }

    public FuncionEntidad(int id, float precio, Time empezaFuncion, Time terminoFuncion, String diaFuncion, int pelicula_id, int sala_id) {
        this.id = id;
        this.precio = precio;
        this.empezaFuncion = empezaFuncion;
        this.terminoFuncion = terminoFuncion;
        this.diaFuncion = diaFuncion;
        this.pelicula_id = pelicula_id;
        this.sala_id = sala_id;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Time getEmpezaFuncion() {
        return empezaFuncion;
    }

    public void setEmpezaFuncion(Time empezaFuncion) {
        this.empezaFuncion = empezaFuncion;
    }

    public Time getTerminoFuncion() {
        return terminoFuncion;
    }

    public void setTerminoFuncion(Time terminoFuncion) {
        this.terminoFuncion = terminoFuncion;
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
        return "FuncionEntidad{" + "id=" + id + ", precio=" + precio + ", empezaFuncion=" + empezaFuncion + ", terminoFuncion=" + terminoFuncion + ", diaFuncion=" + diaFuncion + ", pelicula_id=" + pelicula_id + ", sala_id=" + sala_id + '}';
    }

    
    
}
