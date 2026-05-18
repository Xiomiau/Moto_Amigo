/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.registrarrepartidorcu;

import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigodto.DocumentoDTO;
import com.mycompany.motoamigodto.repartidor.RepartidorDTO;
import com.mycompany.motoamigodto.repartidor.TipoTransporteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 *
 * @author xiomi
 */
public interface IRegistrarRepartidorCU {
    
    // Flujo Repartidor
    void validarDatosPersonales(String nombreCompleto, String correo, String contrasena, String telefono) throws NegocioException;
    void validarDocumentosPersonales(DocumentoDTO documento) throws NegocioException;
    void validarDocumentacionTransporte(TipoTransporteDTO tipoTransporte, DocumentoDTO documento) throws NegocioException;
    void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException;
    void guardarRepartidor(RepartidorDTO datosEntrada) throws NegocioException, Exception;
    
    // Flujo Administrador
    void aprobarRepartidor(String id) throws Exception;
    void rechazarRepartidor(String id) throws Exception;
    void cambiarEstado(String id, String nuevoEstado) throws Exception;
    List<RepartidorDTO> listarRepartidores() throws Exception;
    void generarReporte() throws Exception;
}
