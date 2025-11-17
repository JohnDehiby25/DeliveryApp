package co.edu.uniquindio.poo.appdelivery.model.incidencia;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

import java.time.LocalDateTime;

public class Incidencia {

    private String incidencia;
    private String descripcion;
    private LocalDateTime fecha;
    private String tipoIncidencia;
    private boolean estado;
    private Envio envio;

    public Incidencia(String incidencia, String descripcion, LocalDateTime fecha, String tipoIncidencia,
                      boolean estado, Envio envio) {
        this.incidencia = incidencia;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipoIncidencia = tipoIncidencia;
        this.estado = estado;
        this.envio = envio;
    }
    public Incidencia(){}

    public String obtenerResumen() {
        return "Incidencia: " + incidencia + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Fecha: " + fecha + "\n" +
                "Tipo: " + tipoIncidencia + "\n" +
                "Estado: " + estado + "\n";
    }


    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
