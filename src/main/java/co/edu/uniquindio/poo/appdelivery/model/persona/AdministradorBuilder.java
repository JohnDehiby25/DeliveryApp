package co.edu.uniquindio.poo.appdelivery.model.persona;

import co.edu.uniquindio.poo.appdelivery.model.admin.Administrador;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia;
import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

import java.util.ArrayList;

public class AdministradorBuilder extends PersonaBuilder{

    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Repartidor> listRepartidores;
    private ArrayList<Envio> listEnvios;
    private ArrayList<Pago> listPagos;
    private ArrayList<Incidencia> listIncidencias;

    public AdministradorBuilder(String nombre, String id, String telefono, String email){

        super(nombre,id,telefono,email);
        this.listUsuarios = new ArrayList<>();
        this.listRepartidores = new ArrayList<>();
        this.listEnvios = new ArrayList<>();
        this.listPagos = new ArrayList<>();
        this.listIncidencias = new ArrayList<>();
    }

    public AdministradorBuilder withListUsuarios(ArrayList<Usuario> listUsuarios){
        this.listUsuarios = listUsuarios;
        return this;
    }
    public AdministradorBuilder withListRepartidores(ArrayList<Repartidor> listRepartidores){
        this.listRepartidores = listRepartidores;
        return this;
    }
    public AdministradorBuilder withListEnvios(ArrayList<Envio> listEnvios){
        this.listEnvios = listEnvios;
        return this;
    }
    public AdministradorBuilder withListPagos(ArrayList<Pago> listPagos){
        this.listPagos = listPagos;
        return this;
    }
    public AdministradorBuilder withListIncidencias(ArrayList<Incidencia> listIncidencias){
        this.listIncidencias = listIncidencias;
        return this;
    }
    public Administrador buildAdministrador(){
        return new Administrador(nombre,id,telefono,email);
    }
}
