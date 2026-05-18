/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import java.util.Date;

/**
 *
 * @author xiomi
 */
public class ReporteDTO {
    
    private String totulo;
    private Date fechaGeneracion;
    private int totalRepartidores;
    private int totalPendientes;
    private int totalActivos;
    private int totalRechazados;

    public String getTotulo() {
        return totulo;
    }

    public void setTotulo(String totulo) {
        this.totulo = totulo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getTotalRepartidores() {
        return totalRepartidores;
    }

    public void setTotalRepartidores(int totalRepartidores) {
        this.totalRepartidores = totalRepartidores;
    }

    public int getTotalPendientes() {
        return totalPendientes;
    }

    public void setTotalPendientes(int totalPendientes) {
        this.totalPendientes = totalPendientes;
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(int totalActivos) {
        this.totalActivos = totalActivos;
    }

    public int getTotalRechazados() {
        return totalRechazados;
    }

    public void setTotalRechazados(int totalRechazados) {
        this.totalRechazados = totalRechazados;
    }
    
    
    
    
}
