package util;

import com.mycompany.motoamigodto.repartidor.RepartidorDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerarReporte {

    public static void generarReporteRepartidores(List<RepartidorDTO> repartidores) throws Exception {

        // 1. Calcular totales
        long total     = repartidores.size();
        long activos   = repartidores.stream()
                .filter(r -> "ACTIVO".equalsIgnoreCase(r.estado.toString())).count();
        long pendientes = repartidores.stream()
                .filter(r -> "PENDIENTE".equalsIgnoreCase(r.estado.toString())).count();
        long rechazados = repartidores.stream()
                .filter(r -> "RECHAZADO".equalsIgnoreCase(r.estado.toString())).count();

        // 2. Parámetros del reporte
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TOTAL",      (int) total);
        parametros.put("ACTIVOS",    (int) activos);
        parametros.put("PENDIENTES", (int) pendientes);
        parametros.put("RECHAZADOS", (int) rechazados);
        parametros.put("FECHA", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));

        // 3. Convertir lista a formato que JasperReports entiende
        List<Map<String, Object>> datos = new ArrayList<>();
        for (RepartidorDTO r : repartidores) {
            Map<String, Object> fila = new HashMap<>();
            fila.put("nombreCompleto",    r.nombreCompleto != null ? r.nombreCompleto : "");
            fila.put("correoElectronico", r.correoElectronico != null ? r.correoElectronico : "");
            fila.put("telefono",          r.telefono != null ? r.telefono : "");
            fila.put("estado",            r.estado != null ? r.estado.toString() : "");
            fila.put("fechaRegistro",     r.fechaRegistro != null ? 
                new SimpleDateFormat("dd/MM/yyyy").format(r.fechaRegistro) : "");
            datos.add(fila);
        }

        // 4. Cargar plantilla
        InputStream plantilla = GenerarReporte.class
                .getResourceAsStream("/reportes/reporte_repartidores.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(plantilla);

        // 5. Generar reporte
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        // 6. Mostrar en pantalla
        JasperViewer.viewReport(jasperPrint, false);
    }
}
