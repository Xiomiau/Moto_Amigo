/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.mycompany.motoamigopersistencia.daos.PersistenciaException;

/**
 *
 * @author xiomi
 */
public interface IAdministradorBO {
    void aprobarRepartidor(String id) throws PersistenciaException;
    void rechazarRepartidor(String id) throws PersistenciaException;
    void cambiarEstado(String id, enums.EstadoRepartidor nuevoEstado) throws PersistenciaException;
    void notificarRepartidor(String id, String mensaje);
    void generarReporte() throws PersistenciaException;
    
}
