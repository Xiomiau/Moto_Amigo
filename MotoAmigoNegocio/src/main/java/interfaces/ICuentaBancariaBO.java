/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author xiomi
 */
public interface ICuentaBancariaBO {
    void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException;
    void validarCLABE(String clabe) throws NegocioException;
    void validarNumeroCuenta(String numeroCuenta) throws NegocioException;
    
}
