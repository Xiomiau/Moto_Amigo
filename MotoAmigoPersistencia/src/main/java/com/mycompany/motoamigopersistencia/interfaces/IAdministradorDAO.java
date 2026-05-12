/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia.interfaces;

import com.mycompany.motoamigodominio.entities.Administrador;

/**
 *
 * @author xiomi
 */
public interface IAdministradorDAO {
    
    Administrador guardarAdministrador(Administrador admin);
    boolean eliminarAdministrador(String id);
    
}
