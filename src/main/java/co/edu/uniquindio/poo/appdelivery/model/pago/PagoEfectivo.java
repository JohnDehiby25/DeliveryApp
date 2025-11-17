package co.edu.uniquindio.poo.appdelivery.model.pago;

public class PagoEfectivo extends Pago {

    @Override
    public ProcesadorPago crearProcesador() {
        return new ProcesadorEfectivo();
    }
}
