/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sqlserver_jdbc_practica6;

import java.sql.Date;

public class PedidoLibro {
    private int codigoPedido;
    private int codigoLibro;
    private String nombreLibro;
    private String autorLibro;
    private Date fechaPedido;
    private int cantidad;

    public PedidoLibro(int codigoPedido, int codigoLibro, String nombreLibro, String autorLibro, Date fechaPedido, int cantidad) {
        this.codigoPedido = codigoPedido;
        this.codigoLibro = codigoLibro;
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.fechaPedido = fechaPedido;
        this.cantidad = cantidad;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
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

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}