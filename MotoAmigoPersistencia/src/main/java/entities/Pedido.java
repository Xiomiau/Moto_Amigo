package entities;

import enums.EstadoPedido;

public class Pedido {

    private String id;
    private String direccionOrigen;
    private String direccionDestino;
    private String tipoPaquete;
    private String descripcionPaquete;
    private double pesoAproximado;

    
    private double distanciaKm;
    private int tiempoEstimadoMinutos;
    private double costo;

    private EstadoPedido estado;

    private String idEmprendedor;
    private String idRepartidor;

    public Pedido() {
        this.estado = EstadoPedido.PUBLICADO; 
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public double getPesoAproximado() {
        return pesoAproximado;
    }

    public void setPesoAproximado(double pesoAproximado) {
        this.pesoAproximado = pesoAproximado;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoEstimadoMinutos() {
        return tiempoEstimadoMinutos;
    }

    public void setTiempoEstimadoMinutos(int tiempoEstimadoMinutos) {
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }
}
