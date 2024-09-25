/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Negocio.CiudadNegocio;
import Negocio.ClienteNegocio;
import Negocio.ICiudadNegocio;
import Negocio.IClienteNegocio;
import Negocio.IPeliculaNegocio;
import Negocio.PeliculaNegocio;
import Persistencia.CiudadDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.ICiudadDAO;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import Persistencia.IPeliculaDAO;
import Persistencia.PeliculaDAO;
import Presentacion.FrmInicio;

/**
 *
 * @author Arturo ITSON
 */
public class Main {
    
    public static void main(String args[]){
    
        IConexionBD conexion = new ConexionBD();
        
        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
        IPeliculaNegocio peliculaNegocio = new PeliculaNegocio(peliculaDAO);       
        ICiudadNegocio ciudadNegocio = new CiudadNegocio(ciudadDAO);
        
        FrmInicio inicio = new FrmInicio(clienteNegocio, peliculaNegocio, ciudadNegocio);
        inicio.setVisible(true);
    
    }
    
}
