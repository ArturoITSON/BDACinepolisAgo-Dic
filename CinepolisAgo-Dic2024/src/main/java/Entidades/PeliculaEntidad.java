/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Arturo ITSON
 */
public class PeliculaEntidad {
    
    private int id;
    private String titulo;
    private int clasificacion_id;
    private int duracion;
    private String sinopsis;
    private int genero_id;
    private String trailer;
    private String link_imagen;
    private int pais_id;

    
    public PeliculaEntidad() {
        
    }

    public PeliculaEntidad(int id, String titulo, int clasificacion_id, int duracion, String sinopsis, int genero_id, String trailer, String link_imagen, int pais_id) {
        this.id = id;
        this.titulo = titulo;
        this.clasificacion_id = clasificacion_id;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.genero_id = genero_id;
        this.trailer = trailer;
        this.link_imagen = link_imagen;
        this.pais_id = pais_id;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getClasificacion_id() {
        return clasificacion_id;
    }

    public void setClasificacion_id(int clasificacion_id) {
        this.clasificacion_id = clasificacion_id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getLink_imagen() {
        return link_imagen;
    }

    public void setLink_imagen(String link_imagen) {
        this.link_imagen = link_imagen;
    }

    public int getPais_id() {
        return pais_id;
    }

    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }

    @Override
    public String toString() {
        return "PeliculaEntidad{" + "id=" + id + ", titulo=" + titulo + ", clasificacion_id=" + clasificacion_id + ", duracion=" + duracion + ", sinopsis=" + sinopsis + ", genero_id=" + genero_id + ", trailer=" + trailer + ", link_imagen=" + link_imagen + ", pais_id=" + pais_id + '}';
    }
    
}
