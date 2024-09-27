/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;


import Negocio.CiudadNegocio;
import Negocio.ClasificacionNegocio;
import Negocio.ClienteNegocio;
import Negocio.FuncionNegocio;
import Negocio.GeneroNegocio;
import Negocio.ICiudadNegocio;
import Negocio.IClasificacionNegocio;
import Negocio.IClienteNegocio;
import Negocio.IFuncionNegocio;
import Negocio.IGeneroNegocio;
import Negocio.IPaisNegocio;
import Negocio.IPeliculaNegocio;
import Negocio.ISalaNegocio;
import Negocio.ISucursalNegocio;
import Negocio.PaisNegocio;
import Negocio.PeliculaNegocio;
import Negocio.SalaNegocio;
import Negocio.SucursalNegocio;
import Persistencia.CiudadDAO;
import Persistencia.ClasificacionDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.FuncionDAO;
import Persistencia.GeneroDAO;
import Persistencia.ICiudadDAO;
import Persistencia.IClasificacionDAO;

import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import Persistencia.IFuncionDAO;
import Persistencia.IGeneroDAO;
import Persistencia.IPaisDAO;
import Persistencia.IPeliculaDAO;
import Persistencia.ISalaDAO;
import Persistencia.ISucursalDAO;
import Persistencia.PaisDAO;
import Persistencia.PeliculaDAO;
import Persistencia.SalaDAO;
import Persistencia.SucursalDAO;
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
        IGeneroDAO generoDAO = new GeneroDAO(conexion);
        IClasificacionDAO clasificacionDAO = new ClasificacionDAO(conexion);
        IPaisDAO paisDAO = new PaisDAO(conexion);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        IFuncionDAO funcionDAO = new FuncionDAO(conexion);
        ISalaDAO SalaDAO = new SalaDAO(conexion);
        ISucursalDAO sucursalDao = new SucursalDAO(conexion);
        
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
        IPeliculaNegocio peliculaNegocio = new PeliculaNegocio(peliculaDAO);
        IGeneroNegocio generoNegocio = new GeneroNegocio(generoDAO);
        IClasificacionNegocio clasificacionNegocio = new ClasificacionNegocio(clasificacionDAO);
        IPaisNegocio paisNegocio = new PaisNegocio(paisDAO);
        ICiudadNegocio ciudadNegocio = new CiudadNegocio(ciudadDAO);
        IFuncionNegocio funcionNegocio = new FuncionNegocio(funcionDAO);
        ISalaNegocio salaNegocio = new SalaNegocio(SalaDAO);
        ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDao);
                
        
        FrmInicio inicio = new FrmInicio(clienteNegocio, peliculaNegocio, generoNegocio, clasificacionNegocio, paisNegocio, ciudadNegocio, funcionNegocio, salaNegocio,
                                        sucursalNegocio);
        inicio.setVisible(true);
    
    }
    
}
