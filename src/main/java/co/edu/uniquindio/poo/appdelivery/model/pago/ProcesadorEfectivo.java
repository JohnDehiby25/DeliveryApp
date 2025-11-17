package co.edu.uniquindio.poo.appdelivery.model.pago;

public class ProcesadorEfectivo implements ProcesadorPago{
    @Override
    public ResultadoPago procesar(double monto) {
        System.out.println("Pago en efectivo recibido.");
        return ResultadoPago.EXITOSO;
    }
}
