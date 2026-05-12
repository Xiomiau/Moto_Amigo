package com.mycompany.motoamigonegocio.bos;

import entities.Repartidor;
import enums.EstadoRepartidor;
import java.util.List;

public class ReporteBO {

    private int totalRepartidores;
    private int totalActivos;
    private int totalPendientes;
    private int totalBloqueados;
    private int totalInactivos;

    public void calcularTotales(List<Repartidor> listaRepartidores) {
        if (listaRepartidores == null) return;

        totalRepartidores = listaRepartidores.size();
        totalActivos    = 0;
        totalPendientes = 0;
        totalBloqueados = 0;
        totalInactivos  = 0;

        for (Repartidor r : listaRepartidores) {
            if (r.getEstado() == EstadoRepartidor.ACTIVO)    totalActivos++;
            else if (r.getEstado() == EstadoRepartidor.PENDIENTE)  totalPendientes++;
            else if (r.getEstado() == EstadoRepartidor.BLOQUEADO)  totalBloqueados++;
            else if (r.getEstado() == EstadoRepartidor.INACTIVO)   totalInactivos++;
        }
    }

    public void exportarPDF() {
        System.out.println("Exportando reporte PDF...");
        System.out.println("Total repartidores : " + totalRepartidores);
        System.out.println("Activos            : " + totalActivos);
        System.out.println("Pendientes         : " + totalPendientes);
        System.out.println("Bloqueados         : " + totalBloqueados);
        System.out.println("Inactivos          : " + totalInactivos);
    }

    public int getTotalRepartidores() { return totalRepartidores; }
    public int getTotalActivos()      { return totalActivos; }
    public int getTotalPendientes()   { return totalPendientes; }
    public int getTotalBloqueados()   { return totalBloqueados; }
    public int getTotalInactivos()    { return totalInactivos; }
}
