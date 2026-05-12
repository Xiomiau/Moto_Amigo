package com.mycompany.registrarrepartidorcu;

import com.mycompany.motoamigodominio.entities.Repartidor;
import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigodto.DocumentoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;
import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigopersistencia.daos.RepartidorDAO;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;

public class RegistrarRepartidorCU {

    private IRepartidorDAO repartidorDAO;
    private ConvertidorRepartidor convertidor;
    private RepartidorBO repartidorBO;
    private DocumentoBO documentoBO;
    private CuentaBancariaBO cuentaBancariaBO;

    public RegistrarRepartidorCU() {
        this.repartidorDAO = new RepartidorDAO();
        this.convertidor = new ConvertidorRepartidor();
        this.repartidorBO = new RepartidorBO();
        this.documentoBO = new DocumentoBO();
        this.cuentaBancariaBO = new CuentaBancariaBO();
    }

    public void validarFormUno(String nombreCompleto, String correo,String contrasena, String telefono) throws NegocioException {
        repartidorBO.validarNombreCompleto(nombreCompleto);
        repartidorBO.validarCorreoElectronico(correo);
        repartidorBO.validarContrasena(contrasena);
        repartidorBO.validarTelefono(telefono);
    }

   
    public void validarFormDos(DocumentoDTO documento) throws NegocioException {
        documentoBO.validarINE(documento.ine);
        documentoBO.validarFotoPerfil(documento.fotoPerfil);
        documentoBO.validarAntecedentes(documento.antecedentes);
    }

    
    public void validarFormTres(String tipoTransporte,DocumentoDTO documento) throws NegocioException {
        if (tipoTransporte == null || tipoTransporte.trim().isEmpty()) {
            throw new NegocioException("Debes seleccionar un tipo de transporte.");
        }
        if (tipoTransporte.equals("MOTO_AUTO")) {
            documentoBO.validarLicencia(documento.licenciaConducir);
            documentoBO.validarTarjetaCirculacion(documento.tarjetaCirculacion);
        }
    }

   
    public void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        cuentaBancariaBO.validarCuentaBancaria(cuentaBancaria);
    }

    
    public void ejecutarRegistro(RepartidorDTO datosEntrada) throws NegocioException, Exception {

        validarFormUno(
                datosEntrada.nombreCompleto,
                datosEntrada.correoElectronico,
                datosEntrada.contrasenia,
                datosEntrada.telefono
        );
        validarFormDos(datosEntrada.documento);
        validarFormTres(datosEntrada.tipoTransporte, datosEntrada.documento);
        validarCuentaBancaria(datosEntrada.cuentaBancaria);

        if (repartidorDAO.existeCorreo(datosEntrada.correoElectronico)) {
            throw new NegocioException("El correo electrónico ya está registrado en el sistema.");
        }

        Repartidor nuevoRepartidor = convertidor.mapearDtoAEntidad(datosEntrada);
        repartidorDAO.guardarRepartidor(nuevoRepartidor);
    }
}
