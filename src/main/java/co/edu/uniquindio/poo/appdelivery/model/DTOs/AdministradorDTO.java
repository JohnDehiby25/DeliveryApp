package co.edu.uniquindio.poo.appdelivery.model.DTOs;

public class AdministradorDTO {
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private int totalUsuarios;
    private int totalRepartidores;
    private int totalEnvios;

    public AdministradorDTO() {}

    public AdministradorDTO(String id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
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
    public int getTotalUsuarios() { return totalUsuarios; }
    public void setTotalUsuarios(int totalUsuarios) { this.totalUsuarios = totalUsuarios; }
    public int getTotalRepartidores() { return totalRepartidores; }
    public void setTotalRepartidores(int totalRepartidores) { this.totalRepartidores = totalRepartidores; }
    public int getTotalEnvios() { return totalEnvios; }
    public void setTotalEnvios(int totalEnvios) { this.totalEnvios = totalEnvios; }
}