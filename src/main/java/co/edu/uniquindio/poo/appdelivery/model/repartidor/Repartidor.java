package co.edu.uniquindio.poo.appdelivery.model.repartidor;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.persona.Persona;

import java.util.ArrayList;

public class Repartidor extends Persona {

    private String documento;
    private TipoDisponibilidad tipodisponibilidad;
    private int zonaCobertura;
    private ArrayList<Envio> listEnvios;

    public Repartidor(String nombre, String id, String telefono, String email, String documento,
                      TipoDisponibilidad tipodisponibilidad, int zonaCobertura) {

        super(nombre, id, telefono, email);
        this.documento = documento;
        this.tipodisponibilidad = tipodisponibilidad;
        this.zonaCobertura = zonaCobertura;
        this.listEnvios = new ArrayList<>();
    }
    public Repartidor(){
        super();
        this.listEnvios = new ArrayList<>();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDisponibilidad getTipodisponibilidad() {
        return tipodisponibilidad;
    }

    public void setTipodisponibilidad(TipoDisponibilidad tipodisponibilidad) {
        this.tipodisponibilidad = tipodisponibilidad;
    }

    public ArrayList<Envio> getListEnvios() {
        return listEnvios;
    }

    public void setListEnvios(ArrayList<Envio> listEnvios) {
        this.listEnvios = listEnvios;
    }

    public int getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(int zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

}
