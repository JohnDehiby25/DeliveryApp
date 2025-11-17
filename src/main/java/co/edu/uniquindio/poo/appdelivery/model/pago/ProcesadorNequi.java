package co.edu.uniquindio.poo.appdelivery.model.pago;

public class ProcesadorNequi implements ProcesadorPago {
    @Override
    public ResultadoPago procesar(double monto) {
        System.out.println("Procesando pago con Nequi...");
        return Math.random() < 0.95 ? ResultadoPago.EXITOSO : ResultadoPago.FALLIDO;
    }
}