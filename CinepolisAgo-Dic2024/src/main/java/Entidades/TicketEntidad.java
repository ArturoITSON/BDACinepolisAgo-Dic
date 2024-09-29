/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Arturo ITSON
 */
public class TicketEntidad {
    
    private int id;
    private String QR;
    private float precio;
    private String metodoPago;
    private int cliente_id;
    private int funcion_id;

    public TicketEntidad() {
    }

    public TicketEntidad(int id, String QR, float precio, String metodoPago, int cliente_id, int funcion_id) {
        this.id = id;
        this.QR = QR;
        this.metodoPago = metodoPago;
        this.cliente_id = cliente_id;
        this.funcion_id = funcion_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getFuncion_id() {
        return funcion_id;
    }

    public void setFuncion_id(int funcion_id) {
        this.funcion_id = funcion_id;
    }
    
    
}
