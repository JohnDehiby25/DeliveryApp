package co.edu.uniquindio.poo.appdelivery.model.pago;

public class PagoNequi extends Pago {

    @Override
    public ProcesadorPago crearProcesador() {
        return new ProcesadorNequi();
    }
}