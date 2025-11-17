package co.edu.uniquindio.poo.appdelivery.model.persona;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.TipoDisponibilidad;

import java.util.ArrayList;

public class RepartidorBuilder extends PersonaBuilder{

    private String documento;
    private TipoDisponibilidad tipodisponibilidad;
    private int zonaCobertura;
    private ArrayList<Envio> listEnvios;

    public RepartidorBuilder(String nombre, String id, String telefono, String email,
                             String documento,TipoDisponibilidad tipodisponibilidad,
                             int zonaCobertura){

        super(nombre,id, telefono,email);
        this.documento = documento;
        this.tipodisponibilidad=tipodisponibilidad;
        this.zonaCobertura=zonaCobertura;
        this.listEnvios = new ArrayList<>();

    }

    public RepartidorBuilder withDocumento(String documento){
        this.documento = documento;
        return this;
    }
    public RepartidorBuilder withTipoDisponibilidad(TipoDisponibilidad tipodisponibilidad){
        this.tipodisponibilidad = tipodisponibilidad;
        return this;
    }
    public RepartidorBuilder withZonaCobertura(int zonaCobertura){
        this.zonaCobertura=zonaCobertura;
        return this;
    }
    public RepartidorBuilder withListEnvios(ArrayList<Envio> listEnvios){
        this.listEnvios=listEnvios;
        return this;
    }
    public Repartidor buildRepartidor(){
        return new Repartidor(nombre,id,telefono,email,documento,tipodisponibilidad,zonaCobertura);
    }
}
