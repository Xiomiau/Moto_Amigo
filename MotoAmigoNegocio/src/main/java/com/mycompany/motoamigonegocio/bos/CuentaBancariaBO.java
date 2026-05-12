package com.mycompany.motoamigonegocio.bos;

import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigonegocio.NegocioException;

public class CuentaBancariaBO {

    public void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        if (cuentaBancaria == null) {
            throw new NegocioException("Los datos de cuenta bancaria son obligatorios.");
        }
        validarCLABE(cuentaBancaria.clabe);
        validarNumeroCuenta(cuentaBancaria.numeroCuenta);
    }

    public void validarCLABE(String clabe) throws NegocioException {
        if (clabe == null || clabe.trim().isEmpty()) {
            throw new NegocioException("La CLABE es obligatoria.");
        }
        if (!clabe.matches("\\d{18}")) {
            throw new NegocioException("La CLABE debe tener exactamente 18 dígitos numéricos.");
        }
    }

    public void validarNumeroCuenta(String numeroCuenta) throws NegocioException {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new NegocioException("El número de cuenta es obligatorio.");
        }
        if (!numeroCuenta.matches("\\d{10}")) {
            throw new NegocioException("El número de cuenta debe tener 10 dígitos.");
        }
    }
}
