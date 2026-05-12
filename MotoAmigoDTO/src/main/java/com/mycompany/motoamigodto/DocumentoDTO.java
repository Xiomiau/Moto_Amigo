package com.mycompany.motoamigodto;

public class DocumentoDTO {

    public byte[] ine;
    public byte[] fotoPerfil;
    public byte[] antecedentes;
    public byte[] licenciaConducir;
    public byte[] tarjetaCirculacion;

    public DocumentoDTO() {
    }

    public byte[] getIne() {
        return ine;
    }

    public void setIne(byte[] ine) {
        this.ine = ine;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public byte[] getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(byte[] antecedentes) {
        this.antecedentes = antecedentes;
    }

    public byte[] getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(byte[] licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public byte[] getTarjetaCirculacion() {
        return tarjetaCirculacion;
    }

    public void setTarjetaCirculacion(byte[] tarjetaCirculacion) {
        this.tarjetaCirculacion = tarjetaCirculacion;
    }
}
