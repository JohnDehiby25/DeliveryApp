package co.edu.uniquindio.poo.appdelivery.model.DTOs;


import co.edu.uniquindio.poo.appdelivery.model.repartidor.TipoDisponibilidad;

public class RepartidorDTO {
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private String documento;
    private String estado;
    private int zonaCobertura;
    private int totalEnviosAsignados;

    public RepartidorDTO() {}

    public RepartidorDTO(String id, String nombre, String telefono, String email,
                         String documento, String estado, int zonaCobertura) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.documento = documento;
        this.estado = estado;
        this.zonaCobertura = zonaCobertura;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getZonaCobertura() { return zonaCobertura; }
    public void setZonaCobertura(int zonaCobertura) { this.zonaCobertura = zonaCobertura; }
    public int getTotalEnviosAsignados() { return totalEnviosAsignados; }
    public void setTotalEnviosAsignados(int totalEnviosAsignados) { this.totalEnviosAsignados = totalEnviosAsignados; }

    public TipoDisponibilidad getTipoDisponibilidad() {
        return estado != null ? TipoDisponibilidad.valueOf(estado) : null;
    }

    public void setTipoDisponibilidad(TipoDisponibilidad tipo) {
        this.estado = tipo != null ? tipo.name() : null;
    }

}
