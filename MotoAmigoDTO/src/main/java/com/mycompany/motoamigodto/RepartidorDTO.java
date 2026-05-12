package com.mycompany.motoamigodto;

import java.util.Date;

public class RepartidorDTO {

    public String id;
    public String nombreCompleto;
    public String correoElectronico;
    public String telefono;
    public String contrasenia;
    public String tipoTransporte;  
    public Date fechaRegistro;
    public CuentaBancariaDTO cuentaBancaria;
    public DocumentoDTO documento;
    public EstadoRepartidorDTO estado;

    public RepartidorDTO() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public String getTipoTransporte() { return tipoTransporte; }
    public void setTipoTransporte(String tipoTransporte) { this.tipoTransporte = tipoTransporte; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public CuentaBancariaDTO getCuentaBancaria() { return cuentaBancaria; }
    public void setCuentaBancaria(CuentaBancariaDTO cuentaBancaria) { this.cuentaBancaria = cuentaBancaria; }

    public DocumentoDTO getDocumento() { return documento; }
    public void setDocumento(DocumentoDTO documento) { this.documento = documento; }

    public EstadoRepartidorDTO getEstado() { return estado; }
    public void setEstado(EstadoRepartidorDTO estado) { this.estado = estado; }
}
