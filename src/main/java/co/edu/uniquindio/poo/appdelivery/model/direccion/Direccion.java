package co.edu.uniquindio.poo.appdelivery.model.direccion;

public class Direccion {

    private int idDireccion;
    private String ciudad;
    private String alias;
    private String calle;
    private String coordenadas;


    public Direccion(int idDireccion, String ciudad, String alias, String calle, String coordenadas) {
        this.idDireccion = idDireccion;
        this.ciudad = ciudad;
        this.alias = alias;
        this.calle = calle;
        this.coordenadas = coordenadas;
    }
    public Direccion(){

    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String consultarDetalles() {
        return "Ciudad: " + ciudad +
                ", Calle: " + calle +
                ", Alias: " + alias +
                ", Coordenadas: " + coordenadas;
    }
}
