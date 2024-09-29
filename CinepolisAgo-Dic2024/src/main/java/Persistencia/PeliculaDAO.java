/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOs.PeliculaDTO;
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
public class PeliculaDAO implements IPeliculaDAO {

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
    public PeliculaEntidad guardar(PeliculaGuardarDTO pelicula) throws PersistenciaException {

        try {
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
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

            PreparedStatement preparedStatement = conexionGeneral.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
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

             // Confirmar la transacción
            conexionGeneral.commit();
            
            resultado.close();
            preparedStatement.close();
            conexionGeneral.close();

            return this.buscarPorId(idCliente);

        } catch (SQLException | PersistenciaException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al registrar el cliente, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
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
public PeliculaEntidad buscarPorTitulo(String titulo) throws PersistenciaException {
    try {
        // Utilizar el método ya existente para obtener la conexión, siguiendo el mismo patrón que otros métodos
        Connection connection = this.conexionBD.crearConexion();
        
        // Preparar la consulta
        String query = "SELECT * FROM peliculas WHERE titulo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, titulo);

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new PeliculaEntidad(
                resultSet.getInt("id"),
                resultSet.getString("titulo"),
                resultSet.getInt("clasificacion_id"),
                resultSet.getInt("duracion"),
                resultSet.getString("sinopsis"),
                resultSet.getInt("genero_id"),
                resultSet.getString("trailer"),
                resultSet.getString("link_imagen"),
                resultSet.getInt("pais_id")
            );
        }
    } catch (SQLException e) {
        throw new PersistenciaException("Error al buscar la película por título: " + titulo);
    }
    return null;
}

    @Override
    public PeliculaEntidad modificarPelicula(PeliculaModificarDTO pelicula) throws PersistenciaException {

        PreparedStatement preparedStatement = null;

        PeliculaEntidad peliculaEditada = new PeliculaEntidad();
        peliculaEditada.setClasificacion_id(pelicula.getClasificacion_id());
        peliculaEditada.setDuracion(pelicula.getDuracion());
        peliculaEditada.setGenero_id(pelicula.getGenero_id());
        peliculaEditada.setId(pelicula.getId());
        peliculaEditada.setPais_id(pelicula.getPais_id());
        peliculaEditada.setTitulo(pelicula.getTitulo());

        try {
            
            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);

            String sentenciaSql = "UPDATE Pelicula SET titulo = ?, clasificacion_id = ?, duracion = ?, genero_id = ?, pais_id = ? WHERE id = ?";
            preparedStatement = conexionGeneral.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, peliculaEditada.getTitulo());
            preparedStatement.setInt(2, peliculaEditada.getClasificacion_id());
            preparedStatement.setInt(3, peliculaEditada.getDuracion());
            preparedStatement.setInt(4, peliculaEditada.getGenero_id());
            preparedStatement.setInt(5, peliculaEditada.getPais_id());
            preparedStatement.setInt(6, peliculaEditada.getId());

            preparedStatement.executeUpdate();
            System.out.println(peliculaEditada.toString() + "dao");
            
            
            // Confirmar la transacción
            conexionGeneral.commit();
            
            preparedStatement.close();
            
            return peliculaEditada;

        } catch (SQLException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al registrar la sala, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }
    }

    @Override
    public PeliculaEntidad eliminarPelicula(int idPelicula) throws PersistenciaException {

        PreparedStatement preparedStatement = null;
        PeliculaEntidad peliculaEliminada = this.buscarPorId(idPelicula);

        try {

            this.conexionGeneral = this.conexionBD.crearConexion();
            this.conexionGeneral.setAutoCommit(false);
            
            String sentenciaSql = "DELETE FROM Pelicula WHERE id = ?";
            preparedStatement = conexionGeneral.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, peliculaEliminada.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            
            // Confirmar la transacción
            conexionGeneral.commit();
            
            return peliculaEliminada;

        } catch (SQLException ex) {
            try {
                // Deshacer cambios en caso de error
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error al querer hacer la transaccion " + ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al registrar la sala, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        } finally {
            try {
                if (this.conexionGeneral != null) {
                    this.conexionGeneral.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion de la base de datos");
            }
        }
    }

    @Override
    public List<String> obtenerPeliculas() throws PersistenciaException {
        List<String> peliculas = new ArrayList<>();
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT titulo FROM Pelicula";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                peliculas.add(resultado.getString("titulo"));
            }
            return peliculas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos de peliculas, inténtelo de nuevo.");
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (comandoSQL != null) {
                    comandoSQL.close();
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
    public List<PeliculaEntidad> obtenerTodasLasPeliculas() throws PersistenciaException {
    List<PeliculaEntidad> peliculas = new ArrayList<>();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT id, titulo, clasificacion_id, duracion, sinopsis, genero_id, trailer, link_imagen, pais_id FROM Pelicula";
        comandoSQL = conexion.createStatement();
        resultado = comandoSQL.executeQuery(codigoSQL);

        while (resultado.next()) {
            PeliculaEntidad pelicula = new PeliculaEntidad(
                resultado.getInt("id"),
                resultado.getString("titulo"),
                resultado.getInt("clasificacion_id"),
                resultado.getInt("duracion"),
                resultado.getString("sinopsis"),
                resultado.getInt("genero_id"),
                resultado.getString("trailer"),
                resultado.getString("link_imagen"),
                resultado.getInt("pais_id")
            );
            peliculas.add(pelicula);
        }

        return peliculas;
    } catch (SQLException ex) {
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de peliculas, inténtelo de nuevo.");
    } finally {
        try {
            if (resultado != null) resultado.close();
            if (comandoSQL != null) comandoSQL.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
        }
    }
}

    
    
    
    @Override
    public PeliculaEntidad obtenerPeliculas(int idPelicula) throws PersistenciaException {
    PeliculaEntidad peliculas = new PeliculaEntidad();
    Connection conexion = null;
    Statement comandoSQL = null;
    ResultSet resultado = null;

    try {
        conexion = this.conexionBD.crearConexion();
        String codigoSQL = "SELECT id, titulo, clasificacion_id, duracion, sinopsis, genero_id, trailer, link_imagen, pais_id FROM Pelicula WHERE id = ?";
        
        PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
        preparedStatement.setInt(1, idPelicula);
        
        resultado = preparedStatement.executeQuery();
        
        while (resultado.next()) {
                peliculas = this.peliculaEntidad(resultado);
            }

        return peliculas;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        throw new PersistenciaException("Ocurrió un error al leer la base de datos de peliculas, inténtelo de nuevo.");
    } finally {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (comandoSQL != null) {
                comandoSQL.close();
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
    
    private PeliculaDTO peliculaDTO(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String titulo = resultado.getString("titulo");
        int clasificacion = resultado.getInt("clasificacion_id");
        int duracion = resultado.getInt("duracion");
        String sinopsis = resultado.getString("sinopsis");
        int genero = resultado.getInt("genero_id");
        String trailer = resultado.getString("trailer");
        String link_imagen = resultado.getString("link_imagen");
        int pais = resultado.getInt("pais_id");
        return new PeliculaDTO(id, titulo, clasificacion, duracion, sinopsis, genero, trailer, link_imagen, pais);
    }

}
