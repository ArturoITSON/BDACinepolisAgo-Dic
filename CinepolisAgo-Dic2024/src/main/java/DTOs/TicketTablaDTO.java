/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author eduar
 */
public class TicketTablaDTO {
    private int id;
    private String QR;
    private float precio;
    private String metodoPago;
    private int cliente_id;
    private int funcion_id;
    private String funcionNombre; 
    private String horaFuncion;

    public TicketTablaDTO() {
    }

    public TicketTablaDTO(int id, String QR, float precio, String metodoPago, int cliente_id, int funcion_id) {
        this.id = id;
        this.QR = QR;
        this.precio = precio;
        this.metodoPago = metodoPago;
        this.cliente_id = cliente_id;
        this.funcion_id = funcion_id;
    }

    public TicketTablaDTO(int id, String QR, float precio, String metodoPago, int cliente_id, int funcion_id, String funcionNombre, String horaFuncion) {
    this.id = id;
    this.QR = QR;
    this.precio = precio;
    this.metodoPago = metodoPago;
    this.cliente_id = cliente_id;
    this.funcion_id = funcion_id;
    this.funcionNombre = funcionNombre;
    this.horaFuncion = horaFuncion;      
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

    public String getFuncionNombre() {
        return funcionNombre;
    }

    public void setFuncionNombre(String funcionNombre) {
        this.funcionNombre = funcionNombre;
    }

    public String getHoraFuncion() {
        return horaFuncion;
    }

    public void setHoraFuncion(String horaFuncion) {
        this.horaFuncion = horaFuncion;
    }
    
    
}
