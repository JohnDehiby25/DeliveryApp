package co.edu.uniquindio.poo.appdelivery.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Envio {

    private int idEnvio;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private double costo;
    private EstadoEnvio estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimadaEntrega;
    private Usuario usuario;
    private Repartidor repartidor;
    private Pago pago;
    private GestorTarifa gestorTarifa;
    private List<ServicioAdicional> serviciosAdicionales;
    private List<Incidencia> Listincidencias;
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
    // Métodos de Estado
    public EstadoEnvio actualizarEstado(EstadoEnvio nuevoEstado) {
        this.estado = nuevoEstado;
        notificarObserversNotificacion();
        return this.estado;
    }

    // Métodos de Servicios Adicionales
    public void agregarServicioAdicional(ServicioAdicional servicioAdicional) {
        this.serviciosAdicionales.add(servicioAdicional);
    }

    // Métodos de Cálculo de Costos
    public double calcularCostoTotalEnvio() {
        double costoTotal = this.costo;

        for (ServicioAdicional servicio : serviciosAdicionales) {
            costoTotal += servicio.getCosto();
        }

        return costoTotal;
    }

    public double calcularCostoTotalTarifaYEnvio(Tarifa tarifa) {
        double costoTarifa = tarifa.calcularCosto(this.paquete);
        double costoServicios = 0;

        for (ServicioAdicional servicio : serviciosAdicionales) {
            costoServicios += servicio.getCosto();
        }

        return costoTarifa + costoServicios;
    }

    // Metodo de Detalles
    public String detallesEnvio() {
        StringBuilder detalles = new StringBuilder();
        detalles.append("=== DETALLES DEL ENVÍO ===\n");
        detalles.append("ID Envío: ").append(idEnvio).append("\n");
        detalles.append("Origen: ").append(origen != null ? origen.toString() : "N/A").append("\n");
        detalles.append("Destino: ").append(destino != null ? destino.toString() : "N/A").append("\n");
        detalles.append("Estado: ").append(estado).append("\n");
        detalles.append("Costo: $").append(costo).append("\n");
        detalles.append("Fecha Creación: ").append(fechaCreacion).append("\n");
        detalles.append("Fecha Estimada Entrega: ").append(fechaEstimadaEntrega).append("\n");
        detalles.append("Usuario: ").append(usuario != null ? usuario.getNombre() : "N/A").append("\n");
        detalles.append("Repartidor: ").append(repartidor != null ? repartidor.getNombre() : "No asignado").append("\n");

        if (!serviciosAdicionales.isEmpty()) {
            detalles.append("\nServicios Adicionales:\n");
            for (ServicioAdicional servicio : serviciosAdicionales) {
                detalles.append("  - ").append(servicio.getNombre()).append(": $").append(servicio.getCosto()).append("\n");
            }
        }

        detalles.append("\nCosto Total: $").append(calcularCostoTotalEnvio()).append("\n");

        return detalles.toString();
    }

    // Métodos de Pago
    public void registrarPago(Pago pagoRegistrar) {
        this.pago = pagoRegistrar;
        pagoRegistrar.setEnvio(this);
    }

    public Pago obtenerPagoPorFecha(LocalDateTime fechaObtener) {
        if (this.pago != null && this.pago.getFechaPago().equals(fechaObtener)) {
            return this.pago;
        }
        return null;
    }

    public void consultarComprobantePago() {
        if (this.pago != null) {
            System.out.println("=== COMPROBANTE DE PAGO ===");
            System.out.println("ID Envío: " + idEnvio);
            System.out.println("Monto: $" + pago.getMonto());
            System.out.println("Método de Pago: " + pago.getMetodoPago());
            System.out.println("Fecha: " + pago.getFechaPago());
            System.out.println("Estado: " + pago.getEstadoPago());
            System.out.println("===========================");
        } else {
            System.out.println("No hay pago registrado para este envío.");
        }
    }

    // Métodos de Incidencias
    public void agregarIncidencias(Incidencia incidencia) {
        this.listIncidencias.add(incidencia);
    }

    public List<Incidencia> obtenerIncidencias() {
        return this.listIncidencias;
    }

    public Incidencia getIncidenciaActual() {
        if (!listIncidencias.isEmpty()) {
            return listIncidencias.get(listIncidencias.size() - 1);
        }
        return null;
    }

    public String obtenerResumenIncidencias() {
        if (listIncidencias.isEmpty()) {
            return "No hay incidencias registradas para este envío.";
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("=== RESUMEN DE INCIDENCIAS ===\n");
        resumen.append("Total de incidencias: ").append(listIncidencias.size()).append("\n\n");

        for (int i = 0; i < listIncidencias.size(); i++) {
            Incidencia inc = listIncidencias.get(i);
            resumen.append("Incidencia #").append(i + 1).append(":\n");
            resumen.append("  Tipo: ").append(inc.getTipo()).append("\n");
            resumen.append("  Descripción: ").append(inc.getDescripcion()).append("\n");
            resumen.append("  Fecha: ").append(inc.getFecha()).append("\n");
            resumen.append("  Estado: ").append(inc.getEstado()).append("\n\n");
        }

        return resumen.toString();
    }

    // Métodos de Notificaciones (Patrón Observer)
    public List<INotificacionObserver> agregarObserversNotificacion(INotificacionObserver observer) {
        this.listNotificaciones.add(observer);
        return this.listNotificaciones;
    }

    public List<INotificacionObserver> notificarObserversNotificacion() {
        for (INotificacionObserver observer : listNotificaciones) {
            observer.actualizar(this);
        }
        return this.listNotificaciones;
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
}
