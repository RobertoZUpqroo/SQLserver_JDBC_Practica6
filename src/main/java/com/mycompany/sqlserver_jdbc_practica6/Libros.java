package com.mycompany.sqlserver_jdbc_practica6;

import java.sql.Date;

public class Libros {
    private int codigoLibro;
    private String nombreLibro;
    private String editorial;
    private String autor;
    private String genero;
    private String paisAutor;
    private int numeroPaginas;
    private Date añoEdicion;
    private double precioLibro;

    // Constructor vacío
    public Libros() {
    }

    // Constructor con todos los atributos
    public Libros(int codigoLibro, String nombreLibro, String editorial, String autor, String genero,
                  String paisAutor, int numeroPaginas, Date añoEdicion, double precioLibro) {
        this.codigoLibro = codigoLibro;
        this.nombreLibro = nombreLibro;
        this.editorial = editorial;
        this.autor = autor;
        this.genero = genero;
        this.paisAutor = paisAutor;
        this.numeroPaginas = numeroPaginas;
        this.añoEdicion = añoEdicion;
        this.precioLibro = precioLibro;
    }

    // Getter y Setter para Codigo_libro
    public int getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(int codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    // Getter y Setter para Nombre_libro
    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    // Getter y Setter para Editorial
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    // Getter y Setter para Autor
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Getter y Setter para Genero
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Getter y Setter para Pais_autor
    public String getPaisAutor() {
        return paisAutor;
    }

    public void setPaisAutor(String paisAutor) {
        this.paisAutor = paisAutor;
    }

    // Getter y Setter para Numero_paginas
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    // Getter y Setter para Año_edicion
    public Date getAñoEdicion() {
        return añoEdicion;
    }

    public void setAñoEdicion(Date añoEdicion) {
        this.añoEdicion = añoEdicion;
    }

    // Getter y Setter para Precio_libro
    public double getPrecioLibro() {
        return precioLibro;
    }

    public void setPrecioLibro(double precioLibro) {
        this.precioLibro = precioLibro;
    }
}