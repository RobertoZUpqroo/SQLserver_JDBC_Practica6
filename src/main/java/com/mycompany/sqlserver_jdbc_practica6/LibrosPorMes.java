/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sqlserver_jdbc_practica6;

/**
 *
 * @author Pc
 */
public class LibrosPorMes {
    private int mes;
    private String nombreLibro;
    private int cantidad;

    public LibrosPorMes(int mes, String nombreLibro, int cantidad) {
        this.mes = mes;
        this.nombreLibro = nombreLibro;
        this.cantidad = cantidad;
    }

    public int getMes() {
        return mes;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public int getCantidad() {
        return cantidad;
    }
}
