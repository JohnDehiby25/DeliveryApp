package co.edu.uniquindio.poo.appdelivery.model;

import java.time.LocalDateTime;

public class Incidencia {

    private String incidencia;
    private String descripcion;
    private LocalDateTime fecha;
    private String tipoIncidencia;
    private boolean estado;
    private Envio envio;

    public Incidencia(String incidencia, String descripcion, LocalDateTime fecha, String tipoIncidencia, boolean estado, Envio envio) {
        this.incidencia = incidencia;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipoIncidencia = tipoIncidencia;
        this.estado = estado;
        this.envio = envio;
    }
    public String obtenerResumen() {
        return "Incidencia: " + incidencia + ", Tipo: " + tipoIncidencia + ", Fecha: " + fecha;
    }
}
