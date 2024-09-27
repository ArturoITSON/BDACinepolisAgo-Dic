/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.sql.Date;

/**
 *
 * @author eduar
 */
public class ClienteTablaDTO {
    private int idCliente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date nacimiento;
    private int ciudad;
    private String contrasena;
    private String correo;
    
    public ClienteTablaDTO() {
        
    }

    public ClienteTablaDTO(int idCliente, String nombres, String apellidoPaterno, String apellidoMaterno, Date nacimiento, int ciudad, String contrasena, String correo) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nacimiento = nacimiento;
        this.ciudad = ciudad;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "ClienteTablaDTO{" + "idCliente=" + idCliente + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", nacimiento=" + nacimiento + ", ciudad=" + ciudad + ", contrasena=" + contrasena + ", correo=" + correo + '}';
    }
}
