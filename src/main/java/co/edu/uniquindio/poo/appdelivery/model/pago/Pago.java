package co.edu.uniquindio.poo.appdelivery.model.pago;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.tarifa.GestorTarifa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Pago {

    protected double monto;
    protected EstadoPago estadoPago;
    protected Envio envio;

    public abstract ProcesadorPago crearProcesador();

    public boolean validarPago() {
        if (monto <= 0) {
            System.out.println("El monto no puede ser cero o negativo.");
            return false;
        }
        if (envio == null) {
            System.out.println("El pago no tiene envío asociado.");
            return false;
        }
        return true;
    }

    public void actualizarEstado(EstadoPago nuevoEstado) {
        this.estadoPago = nuevoEstado;
        System.out.println("Estado de pago actualizado a: " + nuevoEstado);
    }

    public void pagarTarifa() {

        double total = GestorTarifa.getInstance()
                .getTarifa()
                .calcularTotalTarifa(envio);

        if (monto < total) {
            System.out.println("Monto insuficiente. Faltan: $" + (total - monto));
            estadoPago = EstadoPago.RECHAZADO;
            return;
        }

        ProcesadorPago procesador = crearProcesador();

        ResultadoPago resultado = procesador.procesar(total);

        if (resultado == ResultadoPago.EXITOSO) {
            estadoPago = EstadoPago.APROBADO;
            double cambio = monto - total;
            System.out.println("Pago exitoso. Cambio: $" + cambio);
        } else {
            estadoPago = EstadoPago.RECHAZADO;
            System.out.println("El pago fue rechazado.");
        }
    }
}

