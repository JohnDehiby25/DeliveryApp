package co.edu.uniquindio.poo.appdelivery.model.envio;

public class NotificacionSMS implements INotificacionObserver{

    @Override
    public void actualizar(Envio envio) {
        System.out.println("Notificacion Email: El envio " +
                envio + "ha cambiado de estado" + envio.getEstado());
    }

}
