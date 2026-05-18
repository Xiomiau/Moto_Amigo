package com.mycompany.motoamigonegocio.bos;

import entities.Repartidor;
import enums.EstadoRepartidor;
import interfaces.IReporteBO;
import java.util.List;

public class ReporteBO implements IReporteBO{

    private int totalRepartidores;
    private int totalActivos;
    private int totalPendientes;
    private int totalRechazados;
    private int totalInactivos;

    @Override
    public void calcularTotales(List<Repartidor> listaRepartidores) {
        if (listaRepartidores == null) return;

        totalRepartidores = listaRepartidores.size();
        totalActivos    = 0;
        totalPendientes = 0;
        totalRechazados = 0;
        totalInactivos  = 0;

        for (Repartidor r : listaRepartidores) {
            if (r.getEstado() == EstadoRepartidor.ACTIVO)    totalActivos++;
            else if (r.getEstado() == EstadoRepartidor.PENDIENTE)  totalPendientes++;
            else if (r.getEstado() == EstadoRepartidor.RECHAZADO)  totalRechazados++;
            else if (r.getEstado() == EstadoRepartidor.INACTIVO)   totalInactivos++;
        }
    }

    @Override
    public void exportarPDF() {
        System.out.println("Exportando reporte PDF...");
        System.out.println("Total repartidores : " + totalRepartidores);
        System.out.println("Activos            : " + totalActivos);
        System.out.println("Pendientes         : " + totalPendientes);
        System.out.println("Bloqueados         : " + totalRechazados);
        System.out.println("Inactivos          : " + totalInactivos);
    }

    @Override
    public int getTotalRepartidores() { return totalRepartidores; }
    @Override
    public int getTotalActivos()      { return totalActivos; }
    @Override
    public int getTotalPendientes()   { return totalPendientes; }
    @Override
    public int getTotalRechazados()   { return totalRechazados; }
    @Override
    public int getTotalInactivos()    { return totalInactivos; }
}
