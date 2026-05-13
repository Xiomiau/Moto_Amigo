package entities;

import enums.EstadoRepartidor;
import enums.TipoTransporte;
import java.util.Date;

/**
 *
 * @author xiomi
 */

public class Repartidor {

    private String id;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private String contrasenia;
    private Date fechaRegistro;
    private CuentaBancaria cuentaBancaria;
    private Documento documento;
    private EstadoRepartidor estado;
    private TipoTransporte tipoTransporte;

    public Repartidor() {
        this.estado = EstadoRepartidor.PENDIENTE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public EstadoRepartidor getEstado() {
        return estado;
    }

    public void setEstado(EstadoRepartidor estado) {
        this.estado = estado;
    }

    public TipoTransporte getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }
    
    
}
