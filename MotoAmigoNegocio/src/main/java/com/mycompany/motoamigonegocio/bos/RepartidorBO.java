package com.mycompany.motoamigonegocio.bos;

import com.mycompany.motoamigonegocio.NegocioException;
import entities.Repartidor;
import interfaces.IRepartidorBO;
import java.util.List;

public class RepartidorBO implements IRepartidorBO {

    @Override
    public void validarNombreCompleto(String nombre) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }
    }

   

    @Override
    public void validarCorreoElectronico(String correo) throws NegocioException {
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo electrónico no puede estar vacío.");
        }
        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new NegocioException("El formato del correo electrónico no es válido.");
        }
    }

    @Override
    public void validarContrasena(String contrasena) throws NegocioException {
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía.");
        }
        if (contrasena.length() < 8) {
            throw new NegocioException("La contraseña debe tener al menos 8 caracteres.");
        }
    }

    @Override
    public void validarTelefono(String telefono) throws NegocioException {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }
        if (!telefono.matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe tener exactamente 10 dígitos.");
        }
    }

    @Override
    public boolean verificarCorreoDuplicado(String correo, List<Repartidor> lista) {
        for (Repartidor r : lista) {
            if (r.getCorreoElectronico() != null && r.getCorreoElectronico().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }
}
