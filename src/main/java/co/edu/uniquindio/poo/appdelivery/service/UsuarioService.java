package co.edu.uniquindio.poo.appdelivery.service;

import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsuarioService {

   public Direccion crearDireccion(Usuario usuario, int idDireccion, String ciudad, String alias,
                                   String calle, String coordenadas){

       for(Direccion d: usuario.getListDirecciones()){
           if(d.getIdDireccion() == idDireccion){
               System.out.println("La direccion ya existe");
               return null;
           }
       }

       Direccion nuevaDireccion = new Direccion(idDireccion, ciudad, alias, calle, coordenadas);
       usuario.getListDirecciones().add(nuevaDireccion);
       return nuevaDireccion;
   }
   public boolean actualizarInfoDireccionExistente(Direccion direccionNueva
           ,Usuario usuario, int idDireccion){

       for(Direccion direccionbuscar: usuario.getListDirecciones()){
           if(direccionbuscar.getIdDireccion() == idDireccion){
               direccionbuscar.setIdDireccion(direccionNueva.getIdDireccion());
               direccionbuscar.setAlias(direccionNueva.getAlias());
               direccionbuscar.setCalle(direccionNueva.getCalle());
               direccionbuscar.setCiudad(direccionNueva.getCalle());
               direccionbuscar.setCoordenadas(direccionNueva.getCoordenadas());

               return true;
           }
       }
       return false;
   }
   public boolean eliminarDireccion(int idDireccion, Usuario usuario){
       ArrayList<Direccion> direcciones = usuario.getListDirecciones();
       for(int i = 0; i<direcciones.size(); i++){
           if(direcciones.get(i).getIdDireccion() == idDireccion){
               direcciones.remove(i);
               return true;
           }
       }
       return false;
   }
   public String consultarDetallesDirecciones(Usuario usuario){
       if(usuario.getListDirecciones().isEmpty()){
           return "No hay direcciones asignadas";
       }
       StringBuilder mensaje = new StringBuilder();
       for(Direccion d: usuario.getListDirecciones()){
           mensaje.append(d.toString()).append("\n");
       }
       return mensaje.toString();
   }
   public String consultarDetallesEnvio(Usuario usuario){
       if(usuario.getListEnvios().isEmpty()){
           return "No hay envios asignados";
       }
       StringBuilder mensaje = new StringBuilder();
       for(Envio e: usuario.getListEnvios()){
           mensaje.append(e.toString()).append("\n");
       }
       return mensaje.toString();
   }
    public String filtrarEnviosFecha(LocalDateTime fechaConsultar, Usuario usuario) {
        if (usuario.getListEnvios().isEmpty()) {
            return "El usuario no tiene envíos registrados.";
        }
        StringBuilder mensaje = new StringBuilder("Envíos con fecha estimada de entrega "
                + fechaConsultar + ":\n");
        boolean encontrado = false;

        for (Envio e : usuario.getListEnvios()) {
            if (e.getFechaEstimadaEntrega().isEqual(fechaConsultar)) {
                mensaje.append(e.toString()).append("\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            return "No se encontraron envíos con fecha de entrega " + fechaConsultar + ".";
        }
        return mensaje.toString();
    }
    public String cancelarEnvio(int idEnvioCancelar, Usuario usuario) {
        if (usuario.getListEnvios().isEmpty()) {
            return "No hay envíos asignados para cancelar.";
        }
        Envio envio = buscarEnvioPorId(idEnvioCancelar, usuario);
        if (envio == null) {
            return "No se encontró ningún envío con el ID " + idEnvioCancelar + ".";
        }
        if (envio.getEstado() == EstadoEnvio.EN_RUTA) {
            envio.setEstado(EstadoEnvio.CANCELADO);
            return "El envío con ID " + idEnvioCancelar + " ha sido cancelado correctamente.";
        }
        return "El envío con ID " + idEnvioCancelar + " no puede cancelarse porque " +
                "está en estado: "
                + envio.getEstado() + ".";
    }

    public Envio buscarEnvioPorId(int id, Usuario usuario) {
        for (Envio e : usuario.getListEnvios()) {
            if (e.getIdEnvio() == id) return e;
        }
        return null;
    }


}
