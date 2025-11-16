package co.edu.uniquindio.poo.appdelivery.model.envio;

import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia;
import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;
import co.edu.uniquindio.poo.appdelivery.model.paquete.Paquete;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.tarifa.GestorTarifa;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Envio {

    private int idEnvio;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private double costo,distanciaporKm;
    private EstadoEnvio estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimadaEntrega;
    private Usuario usuario;
    private Repartidor repartidor;
    private Pago pago;
    private GestorTarifa gestorTarifa;
    private List<ServicioAdicional> serviciosAdicionales;
    private List<Incidencia> ListIncidencias;
    private List<INotificacionObserver> listNotificaciones;

    public Envio(int idEnvio, Direccion origen, Direccion destino, Paquete paquete,
                 double costo, EstadoEnvio estado, LocalDateTime fechaCreacion,
                 LocalDateTime fechaEstimadaEntrega, Usuario usuario, Repartidor repartidor,
                 Pago pago, GestorTarifa gestorTarifa) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.paquete = paquete;
        this.costo = costo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.usuario = usuario;
        this.repartidor = repartidor;
        this.pago = pago;
        this.gestorTarifa = gestorTarifa;
        this.serviciosAdicionales = new ArrayList<>();
        this.listNotificaciones = new ArrayList<>();
        this.Listincidencias = new ArrayList<>();
    }

    // Métodos de Servicios Adicionales
    public void agregarServicioAdicional(ServicioAdicional servicioAdicional) {
        this.serviciosAdicionales.add(servicioAdicional);
    }

    // Métodos de Incidencias
    public void agregarIncidencias(Incidencia incidencia) {
        this.listIncidencias.add(incidencia);
    }

    public List<Incidencia> obtenerIncidencias() {
        return this.listIncidencias;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(LocalDateTime fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public double getDistanciaporKm() {
        return distanciaporKm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    @Override
    public String toString() {
        return "Envio ID: " + idEnvio +
                ", Fecha estimada: " + fechaEstimadaEntrega +
                ", Dirección destino: " + destino;
    }

}
