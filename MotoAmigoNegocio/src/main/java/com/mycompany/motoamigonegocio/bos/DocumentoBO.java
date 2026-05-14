package com.mycompany.motoamigonegocio.bos;

import com.mycompany.motoamigonegocio.NegocioException;
import interfaces.IDocumentoBO;

public class DocumentoBO implements IDocumentoBO {

    @Override
    public void validarINE(byte[] ine) throws NegocioException {
        if (ine == null || ine.length == 0) {
            throw new NegocioException("El documento INE no puede estar vacío.");
        }
    }

    @Override
    public void validarFotoPerfil(byte[] foto) throws NegocioException {
        if (foto == null || foto.length == 0) {
            throw new NegocioException("La fotografía de perfil no puede estar vacía.");
        }
    }

    @Override
    public void validarAntecedentes(byte[] archivo) throws NegocioException {
        if (archivo == null || archivo.length == 0) {
            throw new NegocioException("El documento de antecedentes no puede estar vacío.");
        }
    }

    @Override
    public void validarLicencia(byte[] licencia) throws NegocioException {
        if (licencia == null || licencia.length == 0) {
            throw new NegocioException("La licencia no puede estar vacía.");
        }
    }

    @Override
    public void validarTarjetaCirculacion(byte[] tarjeta) throws NegocioException {
        if (tarjeta == null || tarjeta.length == 0) {
            throw new NegocioException("La tarjeta de circulación no puede estar vacía.");
        }
    }
}
