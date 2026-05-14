package com.mycompany.registrarrepartidorcu;

import entities.Repartidor;
import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigodto.DocumentoDTO;
import com.mycompany.motoamigodto.repartidor.RepartidorDTO;
import com.mycompany.motoamigodto.repartidor.TipoTransporteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.bos.AdministradorBO;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;
import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigopersistencia.daos.RepartidorDAO;
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

    public RegistrarRepartidorCU() {
        this.repartidorDAO = new RepartidorDAO();
        this.convertidor = new ConvertidorRepartidor();
        this.repartidorBO = new RepartidorBO();
        this.documentoBO = new DocumentoBO();
        this.cuentaBancariaBO = new CuentaBancariaBO();
        this.administradorBO = new AdministradorBO(repartidorDAO);
    }

    @Override
    public void validarFormUno(String nombreCompleto, String correo, String contrasena, String telefono) throws NegocioException {
        repartidorBO.validarNombreCompleto(nombreCompleto);
        repartidorBO.validarCorreoElectronico(correo);
        repartidorBO.validarContrasena(contrasena);
        repartidorBO.validarTelefono(telefono);
    }

    @Override
    public void validarFormDos(DocumentoDTO documento) throws NegocioException {
        documentoBO.validarINE(documento.ine);
        documentoBO.validarFotoPerfil(documento.fotoPerfil);
        documentoBO.validarAntecedentes(documento.antecedentes);
    }

    @Override
    public void validarFormTres(TipoTransporteDTO tipoTransporte, DocumentoDTO documento) throws NegocioException {
        if (tipoTransporte == null) {
            throw new NegocioException("Debes seleccionar un tipo de transporte.");
        }
        if (tipoTransporte == TipoTransporteDTO.MOTO) {
            documentoBO.validarLicencia(documento.licenciaConducir);
            documentoBO.validarTarjetaCirculacion(documento.tarjetaCirculacion);
        }
    }

    @Override
    public void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        cuentaBancariaBO.validarCuentaBancaria(cuentaBancaria);
    }

    @Override
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
        administradorBO.cambiarEstado(id, EstadoRepartidor.BLOQUEADO);
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
