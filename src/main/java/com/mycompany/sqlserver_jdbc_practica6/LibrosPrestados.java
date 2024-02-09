/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sqlserver_jdbc_practica6;

/**
 *
 * @author Pc
 */
// En la clase LibrosPrestados

public class LibrosPrestados {
    private int codigoLibro;
    private String nombreLibro;
    private int vecesPrestado;

    public LibrosPrestados(int codigoLibro, String nombreLibro, int vecesPrestado) {
        this.codigoLibro = codigoLibro;
        this.nombreLibro = nombreLibro;
        this.vecesPrestado = vecesPrestado;
    }

    public int getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(int codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }
}
