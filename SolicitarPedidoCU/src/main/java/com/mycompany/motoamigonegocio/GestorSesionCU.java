package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorEmprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigopersistencia.daos.EmprendedorDAO;
import com.mycompany.motoamigopersistencia.interfaces.IEmprendedorDAO;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.daos.RepartidorDAO;

public class GestorSesionCU {
    
    
    private IEmprendedorDAO emprendedorDAO;
    private IRepartidorDAO repartidorDAO;
    private ConvertidorEmprendedor convEmprendedor;
    private ConvertidorRepartidor convRepartidor;

    public GestorSesionCU() {
        this.emprendedorDAO = new EmprendedorDAO();
        this.repartidorDAO = new RepartidorDAO();
        this.convEmprendedor = new ConvertidorEmprendedor();
        this.convRepartidor = new ConvertidorRepartidor();
    }

    public EmprendedorDTO obtenerEmprendedorLogueado() throws Exception {
        return convEmprendedor.mapearEntidadADTO(emprendedorDAO.obtenerEmprendedorSesion());
    }

    public RepartidorDTO obtenerRepartidorLogueado() throws Exception {
        return convRepartidor.mapearEntidadADTO(repartidorDAO.obtenerRepartidorSesion());
    }
}