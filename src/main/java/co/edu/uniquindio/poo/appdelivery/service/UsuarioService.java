package co.edu.uniquindio.poo.appdelivery.service;

import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

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

}
