package com.mycompany.motoamigopersistencia.daos;

import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import entities.CuentaBancaria;
import entities.Documento;
import entities.Repartidor;
import enums.EstadoRepartidor;
import enums.TipoTransporte;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepartidorDAO implements IRepartidorDAO {

    private static final List<Repartidor> baseDatosRepartidores = new ArrayList<>();

    static {
        // Repartidor 1
        Repartidor r1 = new Repartidor();
        r1.setId("1");
        r1.setNombreCompleto("Juan Pérez Gómez");
        r1.setCorreoElectronico("juan.perez@email.com");
        r1.setTelefono("5551234567");
        r1.setContrasenia("pass1234");
        r1.setFechaRegistro(new Date());
        r1.setEstado(EstadoRepartidor.ACTIVO);
        CuentaBancaria cb1 = new CuentaBancaria();
        cb1.setNumeroCuenta("1234567890");
        cb1.setBanco("BBVA");
        cb1.setClabe("012345678901234567");
        r1.setCuentaBancaria(cb1);
        Documento d1 = new Documento();
        d1.setIne(new byte[]{1, 2, 3});
        d1.setFotoPerfil(new byte[]{1, 2, 3});
        d1.setAntecedentes(new byte[]{1, 2, 3});
        d1.setLicenciaConducir(new byte[]{1, 2, 3});
        d1.setTarjetaCirculacion(new byte[]{1, 2, 3});
        r1.setDocumento(d1);
        r1.setTipoTransporte(TipoTransporte.MOTO);
        baseDatosRepartidores.add(r1);

        // Repartidor 2
        Repartidor r2 = new Repartidor();
        r2.setTipoTransporte(TipoTransporte.AUTOMOVIL);
        r2.setId("2");
        r2.setNombreCompleto("María López Díaz");
        r2.setCorreoElectronico("maria.lopez@email.com");
        r2.setTelefono("5559876543");
        r2.setContrasenia("segura456");
        r2.setFechaRegistro(new Date());
        r2.setEstado(EstadoRepartidor.PENDIENTE);
        CuentaBancaria cb2 = new CuentaBancaria();
        cb2.setNumeroCuenta("9876543210");
        cb2.setBanco("Banamex");
        cb2.setClabe("002345678901234567");
        r2.setCuentaBancaria(cb2);
        Documento d2 = new Documento();
        d2.setIne(new byte[]{1, 2, 3});
        d2.setFotoPerfil(new byte[]{1, 2, 3});
        d2.setAntecedentes(new byte[]{1, 2, 3});
        d2.setLicenciaConducir(new byte[]{1, 2, 3});
        d2.setTarjetaCirculacion(new byte[]{1, 2, 3});
        r2.setDocumento(d2);
        baseDatosRepartidores.add(r2);

        // Repartidor 3
        Repartidor r3 = new Repartidor();
        r3.setId("3");
        r3.setNombreCompleto("Carlos Ruiz Sánchez");
        r3.setCorreoElectronico("carlos.ruiz@email.com");
        r3.setTelefono("5554567890");
        r3.setContrasenia("moto7890");
        r3.setFechaRegistro(new Date());
        r3.setEstado(EstadoRepartidor.INACTIVO);
        CuentaBancaria cb3 = new CuentaBancaria();
        cb3.setNumeroCuenta("4561237890");
        cb3.setBanco("Santander");
        cb3.setClabe("014345678901234567");
        r3.setCuentaBancaria(cb3);
        Documento d3 = new Documento();
        d3.setIne(new byte[]{1, 2, 3});
        d3.setFotoPerfil(new byte[]{1, 2, 3});
        d3.setAntecedentes(new byte[]{1, 2, 3});
        d3.setLicenciaConducir(new byte[]{1, 2, 3});
        d3.setTarjetaCirculacion(new byte[]{1, 2, 3});
        r3.setDocumento(d3);
        r3.setTipoTransporte(TipoTransporte.BICICLETA);
        baseDatosRepartidores.add(r3);

        System.out.println("Base de datos inicializada con 3 repartidores.");
    }

    @Override
    public boolean existeCorreo(String correo) throws PersistenciaException {
        for (Repartidor r : baseDatosRepartidores) {
            if (r.getCorreoElectronico().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Repartidor guardarRepartidor(Repartidor repartidor) throws PersistenciaException {
        if (repartidor.getId() == null || repartidor.getId().isEmpty()) {
            repartidor.setId(String.valueOf(baseDatosRepartidores.size() + 1));
        }
        baseDatosRepartidores.add(repartidor);
       
        System.out.println("Repartidor " + repartidor.getNombreCompleto() + " guardado. Total: " + baseDatosRepartidores.size());
        return repartidor;
    }

    @Override
    public Repartidor obtenerRepartidorSesion() throws PersistenciaException {
        if (!baseDatosRepartidores.isEmpty()) {
            return baseDatosRepartidores.get(0);
        }
        return null;
    }

    @Override
    public Repartidor buscarPorId(String id) throws PersistenciaException {
        for (Repartidor r : baseDatosRepartidores) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Repartidor repartidor) throws PersistenciaException {
        for (int i = 0; i < baseDatosRepartidores.size(); i++) {
            if (baseDatosRepartidores.get(i).getId().equals(repartidor.getId())) {
                baseDatosRepartidores.set(i, repartidor);
                System.out.println("Repartidor " + repartidor.getNombreCompleto() + " actualizado.");
                return;
            }
        }
        throw new PersistenciaException("No se encontró el repartidor con id: " + repartidor.getId());
    }

    @Override
    public List<Repartidor> listarTodos() throws PersistenciaException {
        return new ArrayList<>(baseDatosRepartidores);
    }
}
