package co.edu.uniquindio.poo.appdelivery.model.pago;

public class ProcesadorTarjeta implements ProcesadorPago {
    @Override
    public ResultadoPago procesar(double monto) {
        System.out.println("Procesando tarjeta...");
        return Math.random() < 0.9 ? ResultadoPago.EXITOSO : ResultadoPago.FALLIDO;
    }
}