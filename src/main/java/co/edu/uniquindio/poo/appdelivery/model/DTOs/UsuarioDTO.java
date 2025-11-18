package co.edu.uniquindio.poo.appdelivery.model.DTOs;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private List<DireccionDTO> direcciones;
    private int totalEnvios;

    public UsuarioDTO() {
        this.direcciones = new ArrayList<>();
    }

    public UsuarioDTO(String id, String nombre, String telefono, String email) {
        this();
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
    public List<DireccionDTO> getDirecciones() { return direcciones; }
    public void setDirecciones(List<DireccionDTO> direcciones) { this.direcciones = direcciones; }
    public int getTotalEnvios() { return totalEnvios; }
    public void setTotalEnvios(int totalEnvios) { this.totalEnvios = totalEnvios; }
}