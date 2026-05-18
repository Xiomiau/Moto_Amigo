package com.mycompany.motoamigonegocio.bos;

import entities.Repartidor;
import enums.EstadoRepartidor;
import com.mycompany.motoamigopersistencia.daos.PersistenciaException;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import interfaces.IAdministradorBO;

public class AdministradorBO implements IAdministradorBO {

    private IRepartidorDAO repartidorDAO;

    public AdministradorBO(IRepartidorDAO repartidorDAO) {
        this.repartidorDAO = repartidorDAO;
    }

    @Override
    public void aprobarRepartidor(String id) throws PersistenciaException {
        Repartidor repartidor = repartidorDAO.buscarPorId(id);
        if (repartidor == null) {
            throw new IllegalArgumentException("No se encontró el repartidor con id: " + id);
        }
        repartidor.setEstado(EstadoRepartidor.ACTIVO);
        repartidorDAO.actualizar(repartidor);
        notificarRepartidor(id, "Su solicitud ha sido aprobada. Ya puede comenzar a recibir pedidos.");
    }

    @Override
    public void rechazarRepartidor(String id) throws PersistenciaException {
        Repartidor repartidor = repartidorDAO.buscarPorId(id);
        if (repartidor == null) {
            throw new IllegalArgumentException("No se encontró el repartidor con id: " + id);
        }
        repartidor.setEstado(EstadoRepartidor.RECHAZADO);
        repartidorDAO.actualizar(repartidor);
        notificarRepartidor(id, "Su solicitud ha sido rechazada. Contacte al administrador para más información.");
    }

    @Override
    public void cambiarEstado(String id, EstadoRepartidor nuevoEstado) throws PersistenciaException {
        Repartidor repartidor = repartidorDAO.buscarPorId(id);
        if (repartidor == null) {
            throw new IllegalArgumentException("No se encontró el repartidor con id: " + id);
        }
        repartidor.setEstado(nuevoEstado);
        repartidorDAO.actualizar(repartidor);
    }

    @Override
    public void notificarRepartidor(String id, String mensaje) {
        // Pendiente de implementar con servicio de notificaciones
        System.out.println("Notificación enviada al repartidor [" + id + "]: " + mensaje);
    }

    @Override
    public void generarReporte() throws PersistenciaException {
        ReporteBO reporteBO = new ReporteBO();
        reporteBO.calcularTotales(repartidorDAO.listarTodos());
    }
}
