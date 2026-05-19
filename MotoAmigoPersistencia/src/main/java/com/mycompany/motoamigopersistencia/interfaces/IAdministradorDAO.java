/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia.interfaces;

import com.mycompany.motoamigopersistencia.exepciones.PersistenciaException;
import entities.Administrador;

/**
 *
 * @author xiomi
 */
public interface IAdministradorDAO {
    
    Administrador guardarAdministrador(Administrador admin) throws PersistenciaException;
    boolean eliminarAdministrador(String id) throws PersistenciaException;
    
}
