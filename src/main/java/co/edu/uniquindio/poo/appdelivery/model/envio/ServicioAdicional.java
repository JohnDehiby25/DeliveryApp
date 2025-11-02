package co.edu.uniquindio.poo.appdelivery.model.envio;

public class ServicioAdicional {
    private String idServicio;
    private TipoServicioAdicional tipoServicioAdicional;
    private double valorAsegurado;
    private String descripcion;

    public ServicioAdicional(String idServicio, double valorAsegurado, String descripcion,
                             TipoServicioAdicional tipoServicioAdicional) {
        this.idServicio = idServicio;
        this.valorAsegurado = valorAsegurado;
        this.descripcion = descripcion;
        this.tipoServicioAdicional = tipoServicioAdicional;
    }
    public ServicioAdicional(){}

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(double valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }

    public TipoServicioAdicional getTipoServicioAdicional() {
        return tipoServicioAdicional;
    }

    public void setTipoServicioAdicional(TipoServicioAdicional tipoServicioAdicional) {
        this.tipoServicioAdicional = tipoServicioAdicional;
    }
}
