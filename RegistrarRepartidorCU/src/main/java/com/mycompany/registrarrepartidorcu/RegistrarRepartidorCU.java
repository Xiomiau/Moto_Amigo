package com.mycompany.registrarrepartidorcu;

import entities.Repartidor;
import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigodto.DocumentoDTO;
import com.mycompany.motoamigodto.repartidor.RepartidorDTO;
import com.mycompany.motoamigodto.repartidor.TipoTransporteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import enums.EstadoRepartidor;
import interfaces.IAdministradorBO;
import interfaces.ICuentaBancariaBO;
import interfaces.IDocumentoBO;
import interfaces.IRepartidorBO;
import java.util.ArrayList;
import java.util.List;

public class RegistrarRepartidorCU implements IRegistrarRepartidorCU {

    private IRepartidorBO repartidorBO;
    private IDocumentoBO documentoBO;
    private ICuentaBancariaBO cuentaBancariaBO;
    private IRepartidorDAO repartidorDAO;
    private ConvertidorRepartidor convertidor;
    private IAdministradorBO administradorBO;

    public RegistrarRepartidorCU(IRepartidorBO repartidorBO,
            IDocumentoBO documentoBO,
            ICuentaBancariaBO cuentaBancariaBO,
            IRepartidorDAO repartidorDAO,
            ConvertidorRepartidor convertidor,
            IAdministradorBO administradorBO) {

        this.repartidorBO = repartidorBO;
        this.documentoBO = documentoBO;
        this.cuentaBancariaBO = cuentaBancariaBO;
        this.repartidorDAO = repartidorDAO;
        this.convertidor = convertidor;
        this.administradorBO = administradorBO;
    }

    @Override
    public void validarDatosPersonales(String nombreCompleto, String correo, String contrasena, String telefono) throws NegocioException {
        repartidorBO.validarNombreCompleto(nombreCompleto);
        repartidorBO.validarCorreoElectronico(correo);
        repartidorBO.validarContrasena(contrasena);
        repartidorBO.validarTelefono(telefono);
    }

    @Override
    public void validarDocumentosPersonales(DocumentoDTO documento) throws NegocioException {
        documentoBO.validarINE(documento.ine);
        documentoBO.validarFotoPerfil(documento.fotoPerfil);
        documentoBO.validarAntecedentes(documento.antecedentes);
    }

    @Override
    public void validarDocumentacionTransporte(TipoTransporteDTO tipoTransporte, DocumentoDTO documento) throws NegocioException {
        if (tipoTransporte == null) {
            throw new NegocioException("Debes seleccionar un tipo de transporte.");
        }
        if (tipoTransporte == TipoTransporteDTO.MOTO || tipoTransporte == TipoTransporteDTO.AUTOMOVIL) {
            documentoBO.validarLicencia(documento.licenciaConducir);
            documentoBO.validarTarjetaCirculacion(documento.tarjetaCirculacion);
        }
    }

    @Override
    public void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        cuentaBancariaBO.validarCuentaBancaria(cuentaBancaria);
    }

    @Override
    public void guardarRepartidor(RepartidorDTO datosEntrada) throws NegocioException, Exception {
        validarDatosPersonales(
                datosEntrada.nombreCompleto,
                datosEntrada.correoElectronico,
                datosEntrada.contrasenia,
                datosEntrada.telefono
        );
        validarDocumentosPersonales(datosEntrada.documento);
        validarDocumentacionTransporte(datosEntrada.tipoTransporte, datosEntrada.documento);
        validarCuentaBancaria(datosEntrada.cuentaBancaria);

        if (repartidorDAO.existeCorreo(datosEntrada.correoElectronico)) {
            throw new NegocioException("El correo electrónico ya está registrado en el sistema.");
        }
        Repartidor nuevoRepartidor = convertidor.mapearDtoAEntidad(datosEntrada);
        nuevoRepartidor.setEstado(EstadoRepartidor.PENDIENTE);
        repartidorDAO.guardarRepartidor(nuevoRepartidor);
    }

    @Override
    public void aprobarRepartidor(String id) throws Exception {
        administradorBO.aprobarRepartidor(id);
    }

    @Override
    public void rechazarRepartidor(String id) throws Exception {
        administradorBO.rechazarRepartidor(id);
    }

    @Override
    public void cambiarEstado(String id, String nuevoEstado) throws Exception {
        administradorBO.cambiarEstado(id, EstadoRepartidor.valueOf(nuevoEstado));
    }

    @Override
    public List<RepartidorDTO> listarRepartidores() throws Exception {
        List<Repartidor> repartidoresDAO = repartidorDAO.listarTodos();
        List<RepartidorDTO> repartidoresDTO = new ArrayList<>();
        
        for (Repartidor r : repartidoresDAO) {
           repartidoresDTO.add(convertidor.mapearEntidadADTO(r));
           
        }
        return repartidoresDTO;
        
    }
    @Override
    public void generarReporte() throws Exception {
        administradorBO.generarReporte();
    }

    
}
