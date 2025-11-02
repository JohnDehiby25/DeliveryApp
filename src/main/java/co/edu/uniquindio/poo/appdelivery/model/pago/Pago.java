package co.edu.uniquindio.poo.appdelivery.model.pago;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pago {

    private String idPago;
    private double monto;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;

    public Pago(String idPago, double monto, LocalDateTime fechaPago, MetodoPago metodoPago, EstadoPago estadoPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void pagarTarifa() {
        if (estadoPago == EstadoPago.APROBADO) {
            System.out.println(" Iniciando pago con método: " + metodoPago);
            ResultadoPago resultado = procesarPago();

            if (resultado == ResultadoPago.EXITOSO) {
                estadoPago = EstadoPago.APROBADO;
                fechaPago = LocalDateTime.now();
                System.out.println("Pago exitoso. Monto: $" + monto);
                mostrarComprobantePago();
            } else {
                estadoPago = EstadoPago.RECHAZADO;
                System.out.println(" No se pudo procesar el pago.");
            }

        } else if (estadoPago == EstadoPago.APROBADO) {
            System.out.println("Este pago ya fue realizado anteriormente.");
        } else {
            System.out.println(" No se puede procesar el pago, estado actual: " + estadoPago);
        }
    }

    public void mostrarComprobantePago() {
        if (estadoPago == EstadoPago.APROBADO) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println("\n*** COMPROBANTE DE PAGO ***");
            System.out.println("ID Pago: " + idPago);
            System.out.println("Monto: $" + monto);
            System.out.println("Fecha: " + fechaPago.format(formato));
            System.out.println("Método: " + metodoPago);
            System.out.println("Estado: " + estadoPago);
            System.out.println("******************************\n");
        } else {
            System.out.println(" No hay comprobante disponible, el pago no ha sido completado.");
        }
    }

    public ResultadoPago procesarPago() {
        double random = Math.random();
        if (random < 0.9) {
            System.out.println(" Transacción aprobada.");
            return ResultadoPago.EXITOSO;
        } else {
            System.out.println("Error en la transacción, intente nuevamente.");
            return ResultadoPago.FALLIDO;
        }
    }
}
