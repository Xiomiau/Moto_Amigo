package entities;

import enums.EstadoEmprendedor;

public class Emprendedor {

    private String id;
    private String nombrecompleto;
    private String correoElectronico;
    private String telefono;
    private String direccion;
    private String contrasenia;
    private Documento documento;
    private CuentaBancaria cuentaBancaria;
    private String nombreNegocio;
    private String descripcionNegocio;
    private EstadoEmprendedor estado;

    public Emprendedor() {
        this.estado = EstadoEmprendedor.ACTIVO;
    }

    // --- GETTERS Y SETTERS ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDescripcionNegocio() {
        return descripcionNegocio;
    }

    public void setDescripcionNegocio(String descripcionNegocio) {
        this.descripcionNegocio = descripcionNegocio;
    }

    public EstadoEmprendedor getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmprendedor estado) {
        this.estado = estado;
    }
}
