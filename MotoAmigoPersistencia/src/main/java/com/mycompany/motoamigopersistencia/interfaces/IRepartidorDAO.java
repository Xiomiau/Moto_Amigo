package com.mycompany.motoamigopersistencia.interfaces;

import entities.Repartidor;
import com.mycompany.motoamigopersistencia.daos.PersistenciaException;
import java.util.List;

public interface IRepartidorDAO {
    
    /**
     * Verifica si un correo electrónico ya está registrado en la base de datos.
     */
    boolean existeCorreo(String correo) throws PersistenciaException;
    
    
    void guardarRepartidor(Repartidor repartidor) throws PersistenciaException;
    
    //Simula obtener el repartidor que inició sesión
    Repartidor obtenerRepartidorSesion() throws PersistenciaException;
    
   
Repartidor buscarPorId(String id) throws PersistenciaException;


void actualizar(Repartidor repartidor) throws PersistenciaException;


List<Repartidor> listarTodos() throws PersistenciaException;
}
