/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.Repartidor;
import java.util.List;

/**
 *
 * @author xiomi
 */
public interface IReporteBO {
    void calcularTotales(List<Repartidor> listaRepartidores);
    void exportarPDF();
    public int getTotalRepartidores();
    public int getTotalActivos();
    public int getTotalPendientes();
    public int getTotalBloqueados();
    public int getTotalInactivos();
}
