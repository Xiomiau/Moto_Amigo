package com.mycompany.motoamigopersistencia.daos;

import com.mycompany.motoamigopersistencia.interfaces.IEmprendedorDAO;
import entities.Emprendedor;
import java.util.ArrayList;
import java.util.List;

public class EmprendedorDAO implements IEmprendedorDAO {

    private static List<Emprendedor> baseDatosEmprendedores = new ArrayList<>();

    @Override
    public boolean existeCorreo(String correo) throws PersistenciaException {
        for (Emprendedor e : baseDatosEmprendedores) {
            if (e.getCorreoElectronico().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarEmprendedor(Emprendedor emprendedor) throws PersistenciaException {
        // Autoincremento de idd
        if (emprendedor.getId() == null || emprendedor.getId().isEmpty()) {
            emprendedor.setId("EMP-" + (baseDatosEmprendedores.size() + 1));
        }

        baseDatosEmprendedores.add(emprendedor);

        System.out.println("Emprendedor " + emprendedor.getNombreNegocio() + " guardado exitosamente. Total registros: " + baseDatosEmprendedores.size());
    }

    @Override
    public Emprendedor buscarporId(String id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Emprendedor> listarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Emprendedor actualizar(String id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
