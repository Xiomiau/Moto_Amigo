package com.mycompany.motoamigopersistencia.interfaces;
import entities.Emprendedor;
import com.mycompany.motoamigopersistencia.daos.PersistenciaException;

public interface IEmprendedorDAO {
    
    
    boolean existeCorreo(String correo) throws PersistenciaException;
    void guardarEmprendedor(Emprendedor emprendedor) throws PersistenciaException;
    Emprendedor obtenerEmprendedorSesion() throws PersistenciaException;
}
