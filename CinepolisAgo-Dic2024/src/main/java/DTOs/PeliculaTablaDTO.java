/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Arturo ITSON
 */
public class PeliculaTablaDTO {
    
    private int id;
    private String titulo;
    private int clasifiacion_id;
    private int duracion;
    private String sinopsis;
    private int genero_id;
    private String trailer;
    private String linkImagen;
    private int pais;

    
    
    public PeliculaTablaDTO() {
    }

    public PeliculaTablaDTO(int id, String titulo, int clasifiacion_id, int duracion, String sinopsis, int genero_id, String trailer, String linkImagen, int pais) {
        this.id = id;
        this.titulo = titulo;
        this.clasifiacion_id = clasifiacion_id;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.genero_id = genero_id;
        this.trailer = trailer;
        this.linkImagen = linkImagen;
        this.pais = pais;
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

    public int getClasifiacion_id() {
        return clasifiacion_id;
    }

    public void setClasifiacion_id(int clasifiacion_id) {
        this.clasifiacion_id = clasifiacion_id;
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

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "PeliculaTablaDTO{" + "id=" + id + ", titulo=" + titulo + ", clasifiacion_id=" + clasifiacion_id + ", duracion=" + duracion + ", sinopsis=" + sinopsis + ", genero_id=" + genero_id + ", trailer=" + trailer + ", linkImagen=" + linkImagen + ", pais=" + pais + '}';
    }

    
    
    
    
}
