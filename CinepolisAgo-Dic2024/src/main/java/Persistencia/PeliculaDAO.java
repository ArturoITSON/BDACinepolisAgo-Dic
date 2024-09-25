/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.PeliculaFiltroTablaDTO;
import DTOs.PeliculaGuardarDTO;
import DTOs.PeliculaModificarDTO;
import DTOs.PeliculaTablaDTO;
import Entidades.PeliculaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public class PeliculaDAO implements IPeliculaDAO{

    
    
    private IConexionBD conexionBD;
    private Connection conexionGeneral;
    
    
    public PeliculaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    @Override
    public List<PeliculaTablaDTO> buscarPeliculasTabla(PeliculaFiltroTablaDTO filtro) throws PersistenciaException {
                try {
            List<PeliculaTablaDTO> peliculaLista = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                                SELECT
                                     id,
                                     titulo,
                                     clasificacion_id,
                                     duracion,
                                     sinopsis,
                                     genero_id,
                                     trailer,
                                     link_imagen,
                                     pais_id
                                FROM Pelicula
                                WHERE CONCAT(titulo, ' ', genero_id, ' ', clasificacion_id) LIKE ?
                                LIMIT ? 
                                OFFSET ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, "%" + filtro.getFiltro() + "%");
            preparedStatement.setInt(2, filtro.getLimit());
            preparedStatement.setInt(3, filtro.getOffset());

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                if (peliculaLista == null) {
                    peliculaLista = new ArrayList<>();
                }
                peliculaLista.add(this.peliculaTablaDTO(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return peliculaLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }
 
    
    @Override
    public PeliculaEntidad guardar(PeliculaGuardarDTO pelicula) throws PersistenciaException{
    
           
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String insertCliente = """
                                    INSERT INTO Pelicula (titulo,
                                                          clasificacion_id,
                                                          duracion,
                                                          sinopsis,
                                                          genero_id,
                                                          trailer,
                                                          link_Imagen,
                                                          pais_id
                                   ) 
                                                 VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                                    """;

            PreparedStatement preparedStatement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pelicula.getTitulo());
            preparedStatement.setInt(2, pelicula.getClasificacion_id());
            preparedStatement.setInt(3, pelicula.getDuracion());
            preparedStatement.setString(4, pelicula.getSinopsis());
            preparedStatement.setInt(5, pelicula.getGenero_id());
            preparedStatement.setString(6, pelicula.getTrailer());
            preparedStatement.setString(7, pelicula.getLink_imagen());
            preparedStatement.setInt(8, pelicula.getPais_id());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción de la pelicula falló, no se pudo insertar el registro.");
            }

            int idCliente = 0;
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                idCliente = (resultado.getInt(1));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return this.buscarPorId(idCliente);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }    
        
    }


    @Override
    public PeliculaEntidad buscarPorId(int id) throws PersistenciaException {
        try {
            PeliculaEntidad pelicula = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    id,
                                    titulo,
                                    clasificacion_id,
                                    duracion,
                                    sinopsis,
                                    genero_id,
                                    trailer,
                                    link_imagen,
                                    pais_id
                               FROM Pelicula
                               WHERE id = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                pelicula = this.peliculaEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return pelicula;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
    }


    @Override
    public PeliculaEntidad modificarPelicula(PeliculaModificarDTO pelicula) throws PersistenciaException {
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        
        PeliculaEntidad peliculaEditada = new PeliculaEntidad();
        peliculaEditada.setClasificacion_id(pelicula.getClasificacion_id());
        peliculaEditada.setDuracion(pelicula.getDuracion());
        peliculaEditada.setGenero_id(pelicula.getGenero_id());
        peliculaEditada.setId(pelicula.getId());
        peliculaEditada.setPais_id(pelicula.getPais_id());
        peliculaEditada.setTitulo(pelicula.getTitulo());
        
        
        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "UPDATE Pelicula SET titulo = ?, clasificacion_id = ?, duracion = ?, genero_id = ?, pais_id = ? WHERE id = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, peliculaEditada.getTitulo());
            preparedStatement.setInt(2, peliculaEditada.getClasificacion_id());
            preparedStatement.setInt(3, peliculaEditada.getDuracion());
            preparedStatement.setInt(4, peliculaEditada.getGenero_id());
            preparedStatement.setInt(5, peliculaEditada.getPais_id());
            preparedStatement.setInt(6, peliculaEditada.getId());

            
            
            preparedStatement.executeUpdate();
            System.out.println(peliculaEditada.toString() + "dao");
            return peliculaEditada;
            
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al editar la pelicula: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
    
    
    @Override
    public PeliculaEntidad eliminarPelicula(int idPelicula) throws PersistenciaException {
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        PeliculaEntidad peliculaEliminada = this.buscarPorId(idPelicula);
        
            try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Pelicula WHERE id = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, peliculaEliminada.getId());

            preparedStatement.executeUpdate();
            
            return peliculaEliminada;
            
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar la pelicula: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
    
    
    private PeliculaEntidad peliculaEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String titulo = resultado.getString("titulo");
        int clasificacion = resultado.getInt("clasificacion_id");
        int duracion = resultado.getInt("duracion");
        String sinopsis = resultado.getString("sinopsis");
        int genero = resultado.getInt("genero_id");
        String trailer = resultado.getString("trailer");
        String linkImagen = resultado.getString("link_imagen");
        int pais = resultado.getInt("pais_id");
        return new PeliculaEntidad(id, titulo, clasificacion, duracion, sinopsis, genero, trailer, linkImagen, pais);
    }  

    
    private PeliculaTablaDTO peliculaTablaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String titulo = resultado.getString("titulo");
        int clasificacion = resultado.getInt("clasificacion_id");
        int duracion = resultado.getInt("duracion");
        String sinopsis = resultado.getString("sinopsis");
        int genero = resultado.getInt("genero_id");
        String trailer = resultado.getString("trailer");
        String link_imagen = resultado.getString("link_imagen");
        int pais = resultado.getInt("pais_id");
        return new PeliculaTablaDTO(id, titulo, clasificacion, duracion, sinopsis, genero, trailer, link_imagen, pais);
    }

}
