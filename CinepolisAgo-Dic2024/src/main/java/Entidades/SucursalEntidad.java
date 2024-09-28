/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Arturo ITSON
 */
public class SucursalEntidad {
    
    private int id;
    private String nombre;
    private int ciudad_id;

    public SucursalEntidad() {
    }

    
    
    
    public SucursalEntidad(int id, String nombre, int ciudad_id) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad_id = ciudad_id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(int ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    
    @Override
    public String toString() {
        return "SucursalEntidad{" + "id=" + id + ", nombre=" + nombre + ", ciudad_id=" + ciudad_id + '}';
    }
    
}
