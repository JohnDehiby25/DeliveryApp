package co.edu.uniquindio.poo.appdelivery.model.persona;

import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

import java.util.ArrayList;

public class UsuarioBuilder extends PersonaBuilder{

    private ArrayList<Direccion> listDirecciones;
    private ArrayList<Envio> listEnvios;

    public UsuarioBuilder(String nombre, String id, String telefono, String email){

        super(nombre,id,telefono,email);
        this.listDirecciones = new ArrayList<>();
        this.listEnvios = new ArrayList<>();

    }

    public UsuarioBuilder withDirecciones(ArrayList<Direccion> listDirecciones){
        this.listDirecciones = listDirecciones;
        return this;

    }
    public UsuarioBuilder withEnvios(ArrayList<Envio> listEnvios){
        this.listEnvios = listEnvios;
        return this;

    }
    public Usuario buildUsuario(){
        return new Usuario(nombre,id,telefono,email);
    }
}
