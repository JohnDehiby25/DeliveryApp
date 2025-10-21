package co.edu.uniquindio.poo.appdelivery.model;

import java.util.ArrayList;

public class Repartidor extends Persona{
    private String documento;
    private Tipodisponibilidad tipodisponibilidad;
    private int zonaCobertura;
    private ArrayList<Envios> listaEnvios;

    public Repartidor(String nombre, String id, String telefono, String email, String documento, Tipodisponibilidad tipodisponibilidad, int zonaCobertura) {
        super(nombre, id, telefono, email);
        this.documento = documento;
        this.tipodisponibilidad = tipodisponibilidad;
        this.zonaCobertura = zonaCobertura;
        this.listaEnvios = new ArrayList<>();

    }


}
