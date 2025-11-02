package co.edu.uniquindio.poo.appdelivery.model.DTOs;

import java.time.LocalDateTime;

public class EnvioDTO {
    private int idEnvio,idUsuario,idRepartidor;
    private String estado;
    private double costo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimadaEntrega;

    private DireccionDTO origen;
    private DireccionDTO destino;
    private PaqueteDTO paquete;

    public EnvioDTO(){}

    public EnvioDTO(int idEnvio, int idRepartidor, int idUsuario,
                    PaqueteDTO paquete, DireccionDTO destino,
                    DireccionDTO origen, LocalDateTime fechaEstimadaEntrega,
                    LocalDateTime fechaCreacion, double costo, String estado) {

        this.idEnvio = idEnvio;
        this.idRepartidor = idRepartidor;
        this.idUsuario = idUsuario;
        this.paquete = paquete;
        this.destino = destino;
        this.origen = origen;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.fechaCreacion = fechaCreacion;
        this.costo = costo;
        this.estado = estado;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PaqueteDTO getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteDTO paquete) {
        this.paquete = paquete;
    }

    public DireccionDTO getDestino() {
        return destino;
    }

    public void setDestino(DireccionDTO destino) {
        this.destino = destino;
    }

    public DireccionDTO getOrigen() {
        return origen;
    }

    public void setOrigen(DireccionDTO origen) {
        this.origen = origen;
    }

    public LocalDateTime getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(LocalDateTime fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
