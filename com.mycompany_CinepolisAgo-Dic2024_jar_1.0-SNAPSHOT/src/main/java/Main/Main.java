/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Negocio.ClienteNegocio;
import Negocio.IClienteNegocio;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import Presentacion.FrmInicio;

/**
 *
 * @author Arturo ITSON
 */
public class Main {
    
    public static void main(String args[]){
    
        IConexionBD conexion = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
        FrmInicio inicio = new FrmInicio(clienteNegocio);
        inicio.setVisible(true);
    
    }
    
}
