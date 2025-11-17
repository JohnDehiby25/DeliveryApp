package co.edu.uniquindio.poo.appdelivery.model.pago;

public class PagoTarjetaCredito extends Pago {

    @Override
    public ProcesadorPago crearProcesador() {
        return new ProcesadorTarjeta();
    }
}