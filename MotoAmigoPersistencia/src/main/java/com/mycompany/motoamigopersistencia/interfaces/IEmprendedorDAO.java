package com.mycompany.motoamigopersistencia.interfaces;
import entities.Emprendedor;
import com.mycompany.motoamigopersistencia.exepciones.PersistenciaException;
import java.util.List;

public interface IEmprendedorDAO {
    
    boolean existeCorreo(String correo) throws PersistenciaException;
    void guardarEmprendedor(Emprendedor emprendedor) throws PersistenciaException;
    Emprendedor buscarporId(String id) throws PersistenciaException;
    List<Emprendedor> listarTodos()throws PersistenciaException;
    Emprendedor actualizar(String id) throws PersistenciaException;
}
