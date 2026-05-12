package entities;

import enums.EstadoEmprendedor;

public class Emprendedor {

    private String id;
    private String nombre;
    private String apellidop;
    private String apellidoM;
    private String correoElectronico;
    private String telefono;
    private String direccion;
    private String contrasenia;
    private String Direccion;
    

    // Archivo adjunto (INE)
    private byte[] documentoIdentificacionINE;

    private String cuentaBancaria;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
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

    public byte[] getDocumentoIdentificacionINE() {
        return documentoIdentificacionINE;
    }

    public void setDocumentoIdentificacionINE(byte[] documentoIdentificacionINE) {
        this.documentoIdentificacionINE = documentoIdentificacionINE;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
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
