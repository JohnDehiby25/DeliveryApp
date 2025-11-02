package co.edu.uniquindio.poo.appdelivery.model.DTOs;

public class DireccionDTO {
    private int idDireccion;
    private String ciudad;
    private String alias;
    private String calle;
    private String coordenadas;

    public DireccionDTO(){}

    public DireccionDTO(int idDireccion, String coordenadas, String calle, String alias, String ciudad) {
        this.idDireccion = idDireccion;
        this.coordenadas = coordenadas;
        this.calle = calle;
        this.alias = alias;
        this.ciudad = ciudad;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public String getCalle() {
        return calle;
    }

    public String getAlias() {
        return alias;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
