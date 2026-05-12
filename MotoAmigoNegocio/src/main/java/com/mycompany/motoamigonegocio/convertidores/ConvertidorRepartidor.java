package com.mycompany.motoamigonegocio.convertidores;

import entities.CuentaBancaria;
import entities.Documento;
import entities.Repartidor;
import enums.EstadoRepartidor;
import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigodto.DocumentoDTO;
import com.mycompany.motoamigodto.EstadoRepartidorDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.Date;

public class ConvertidorRepartidor {

    // ─── Enum DTO ↔ Dominio ───────────────────────────────────────────────────

    public EstadoRepartidor convertirEstadoADominio(EstadoRepartidorDTO estadoDTO) {
        if (estadoDTO == null) return null;
        
        switch (estadoDTO) {
            case ACTIVO:    return EstadoRepartidor.ACTIVO;
            case INACTIVO:  return EstadoRepartidor.INACTIVO;
            case BLOQUEADO: return EstadoRepartidor.BLOQUEADO;
            default:        return EstadoRepartidor.PENDIENTE;
        }
    }

    public EstadoRepartidorDTO convertirEstadoADTO(EstadoRepartidor estado) {
        if (estado == null) return null;
        switch (estado) {
            case ACTIVO:    return EstadoRepartidorDTO.ACTIVO;
            case INACTIVO:  return EstadoRepartidorDTO.INACTIVO;
            case BLOQUEADO: return EstadoRepartidorDTO.BLOQUEADO;
            default:        return EstadoRepartidorDTO.PENDIENTE;
        }
    }

    // ─── CuentaBancaria ↔ CuentaBancariaDTO ──────────────────────────────────

    public CuentaBancaria convertirCuentaADominio(CuentaBancariaDTO dto) {
        if (dto == null) return null;
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setNumeroCuenta(dto.numeroCuenta);
        cuenta.setBanco(dto.banco);
        cuenta.setClabe(dto.clabe);
        return cuenta;
    }

    public CuentaBancariaDTO convertirCuentaADTO(CuentaBancaria cuenta) {
        if (cuenta == null) return null;
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        dto.numeroCuenta = cuenta.getNumeroCuenta();
        dto.banco = cuenta.getBanco();
        dto.clabe = cuenta.getClabe();
        return dto;
    }

    // ─── Documento ↔ DocumentoDTO ────────────────────────────────────────────

    public Documento convertirDocumentoADominio(DocumentoDTO dto) {
        if (dto == null) return null;
        Documento doc = new Documento();
        doc.setIne(dto.ine);
        doc.setFotoPerfil(dto.fotoPerfil);
        doc.setAntecedentes(dto.antecedentes);
        doc.setLicenciaConducir(dto.licenciaConducir);
        doc.setTarjetaCirculacion(dto.tarjetaCirculacion);
        return doc;
    }

    public DocumentoDTO convertirDocumentoADTO(Documento doc) {
        if (doc == null) return null;
        DocumentoDTO dto = new DocumentoDTO();
        dto.ine = doc.getIne();
        dto.fotoPerfil = doc.getFotoPerfil();
        dto.antecedentes = doc.getAntecedentes();
        dto.licenciaConducir = doc.getLicenciaConducir();
        dto.tarjetaCirculacion = doc.getTarjetaCirculacion();
        return dto;
    }

    // ─── RepartidorDTO → Repartidor ───────────────────────────────────────────

    public Repartidor mapearDtoAEntidad(RepartidorDTO dto) {
        Repartidor entidad = new Repartidor();
        entidad.setId(dto.id);
        entidad.setNombreCompleto(dto.nombreCompleto);
        entidad.setCorreoElectronico(dto.correoElectronico);
        entidad.setTelefono(dto.telefono);
        entidad.setContrasenia(dto.contrasenia);
        entidad.setFechaRegistro(new Date());  
        entidad.setCuentaBancaria(convertirCuentaADominio(dto.cuentaBancaria));
        entidad.setDocumento(convertirDocumentoADominio(dto.documento));
        return entidad;
    }

    // ─── Repartidor → RepartidorDTO ───────────────────────────────────────────

    public RepartidorDTO mapearEntidadADTO(Repartidor entidad) {
        if (entidad == null) return null;
        RepartidorDTO dto = new RepartidorDTO();
        dto.id = entidad.getId();
        dto.nombreCompleto = entidad.getNombreCompleto();
        dto.correoElectronico = entidad.getCorreoElectronico();
        dto.telefono = entidad.getTelefono();
        dto.fechaRegistro = entidad.getFechaRegistro();
        dto.cuentaBancaria = convertirCuentaADTO(entidad.getCuentaBancaria());
        dto.documento = convertirDocumentoADTO(entidad.getDocumento());
        dto.estado = convertirEstadoADTO(entidad.getEstado());
        return dto;
    }
}
