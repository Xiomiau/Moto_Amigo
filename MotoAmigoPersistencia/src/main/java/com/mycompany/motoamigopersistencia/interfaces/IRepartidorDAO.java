package com.mycompany.motoamigopersistencia.interfaces;

import entities.Repartidor;
import com.mycompany.motoamigopersistencia.exepciones.PersistenciaException;
import java.util.List;

public interface IRepartidorDAO {

    /**
     * Verifica si un correo electrónico ya está registrado en la base de datos.
     */
    boolean existeCorreo(String correo) throws PersistenciaException;

    Repartidor guardarRepartidor(Repartidor repartidor) throws PersistenciaException;

    Repartidor buscarPorId(String id) throws PersistenciaException;

    void actualizar(Repartidor repartidor) throws PersistenciaException;

    List<Repartidor> listarTodos() throws PersistenciaException;
}
