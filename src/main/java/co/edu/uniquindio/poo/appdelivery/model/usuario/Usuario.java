package co.edu.uniquindio.poo.appdelivery.model.usuario;

import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.persona.Persona;

import java.util.ArrayList;

public class Usuario extends Persona {

    private ArrayList<Direccion> listDirecciones;
    private ArrayList<Envio> listEnvios;

    public Usuario(String nombre, String id, String telefono, String email){

        super(nombre,id,telefono,email);
        this.listDirecciones = new ArrayList<>();
        this.listEnvios = new ArrayList<>();
    }
    public Usuario(){
        super();
        this.listDirecciones = new ArrayList<>();
        this.listEnvios = new ArrayList<>();
    }

    public ArrayList<Direccion> getListDirecciones() {
        return listDirecciones;
    }

    public void setListDirecciones(ArrayList<Direccion> listDirecciones) {
        this.listDirecciones = listDirecciones;
    }

    public ArrayList<Envio> getListEnvios() {
        return listEnvios;
    }

    public void setListEnvios(ArrayList<Envio> listEnvios) {
        this.listEnvios = listEnvios;
    }

    @Override
    public String toString() {
        return "Usuario " +
                "listDirecciones = " + listDirecciones +
                ", listEnvios = " + listEnvios +
                ", email = " + email + '\'' +
                ", id = " + id + '\'' +
                ", telefono = " + telefono + '\'' +
                ", nombre = " + nombre + '\'';
    }
}
