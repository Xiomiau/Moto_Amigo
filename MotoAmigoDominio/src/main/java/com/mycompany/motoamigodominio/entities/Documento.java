package com.mycompany.motoamigodominio.entities;



/**
 *
 * @author xiomi
 */
public class Documento {
    
    private String id;
    private byte[] ine;
    private byte[] fotoPerfil;
    private byte[] antecedentes;
    private byte[] licenciaConducir;
    private byte[] tarjetaCirculacion;
    

    public Documento() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
    
}
