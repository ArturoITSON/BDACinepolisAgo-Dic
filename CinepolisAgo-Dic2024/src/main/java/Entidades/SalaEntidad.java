/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author eduar
 */
public class SalaEntidad {
    private int id;
    private String nombre;
    private int asientos_disponibles;
    private float precio; 
    private int minutosLimpiza;
    private int sucursa_id;

    public SalaEntidad() {
    }

    public SalaEntidad(int id, String nombre, int asientos_disponibles, float precio, int minutosLimpiza, int sucursa_id) {
        this.id = id;
        this.nombre = nombre;
        this.asientos_disponibles = asientos_disponibles;
        this.precio = precio;
        this.minutosLimpiza = minutosLimpiza;
        this.sucursa_id = sucursa_id;
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

    public int getAsientos_disponibles() {
        return asientos_disponibles;
    }

    public void setAsientos_disponibles(int asientos_disponibles) {
        this.asientos_disponibles = asientos_disponibles;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getMinutosLimpiza() {
        return minutosLimpiza;
    }

    public void setMinutosLimpiza(int minutosLimpiza) {
        this.minutosLimpiza = minutosLimpiza;
    }

    public int getSucursa_id() {
        return sucursa_id;
    }

    public void setSucursa_id(int sucursa_id) {
        this.sucursa_id = sucursa_id;
    }

    @Override
    public String toString() {
        return "SalaDTO{" + "id=" + id + ", nombre=" + nombre + ", asientos_disponibles=" + asientos_disponibles + ", precio=" + precio + ", minutosLimpiza=" + minutosLimpiza + ", sucursa_id=" + sucursa_id + '}';
    }
}
