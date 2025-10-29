package co.edu.uniquindio.poo.appdelivery.model.admin;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia;
import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;
import co.edu.uniquindio.poo.appdelivery.model.persona.Persona;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

import java.util.ArrayList;

public class Administrador extends Persona {

    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Repartidor> listRepartidores;
    private ArrayList<Envio> listEnvios;
    private ArrayList<Pago> listPagos;
    private ArrayList<Incidencia> listIncidencias;

    public Administrador(String nombre, String id, String telefono, String email){
        super(nombre,id,telefono,email);
        this.listUsuarios = new ArrayList<>();
        this.listRepartidores = new ArrayList<>();
    }
    //constructor vacio para mejor manejo de la info de la clase

    public Administrador(){
        super();
        this.listUsuarios = new ArrayList<>();
        this.listRepartidores = new ArrayList<>();
        this.listEnvios = new ArrayList<>();
        this.listPagos = new ArrayList<>();
        this.listIncidencias = new ArrayList<>();
    }

    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public ArrayList<Repartidor> getListRepartidores() {
        return listRepartidores;
    }

    public void setListRepartidores(ArrayList<Repartidor> listRepartidores) {
        this.listRepartidores = listRepartidores;
    }

    public ArrayList<Envio> getListEnvios() {
        return listEnvios;
    }

    public void setListEnvios(ArrayList<Envio> listEnvios) {
        this.listEnvios = listEnvios;
    }

    public ArrayList<Pago> getListPagos() {
        return listPagos;
    }

    public void setListPagos(ArrayList<Pago> listPagos) {
        this.listPagos = listPagos;
    }

    public ArrayList<Incidencia> getListIncidencias() {
        return listIncidencias;
    }

    public void setListIncidencias(ArrayList<Incidencia> listIncidencias) {
        this.listIncidencias = listIncidencias;
    }
}
