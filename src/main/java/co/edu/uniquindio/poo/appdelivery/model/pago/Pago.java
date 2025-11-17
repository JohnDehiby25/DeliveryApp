package co.edu.uniquindio.poo.appdelivery.model.pago;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.tarifa.GestorTarifa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Pago {

    protected String idPago;
    protected double monto;
    protected LocalDate fechaPago;
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

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    public String mostrarComprobantePago() {
        StringBuilder comprobante = new StringBuilder();

        comprobante.append("==========================================\n");
        comprobante.append("          COMPROBANTE DE PAGO\n");
        comprobante.append("==========================================\n");
        comprobante.append("ID de Pago: ").append(idPago != null ? idPago : "N/A").append("\n");
        comprobante.append("Monto Pagado: $").append(String.format("%.2f", monto)).append("\n");
        comprobante.append("Estado del Pago: ").append(estadoPago != null ? estadoPago : "N/A").append("\n");
        comprobante.append("Fecha y Hora: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
        comprobante.append("Tipo de Pago: ").append(this.getClass().getSimpleName()).append("\n");

        if (envio != null) {
            comprobante.append("\n--- INFORMACIÓN DEL ENVÍO ---\n");
            comprobante.append("ID Envío: ").append(envio.getIdEnvio()).append("\n");
            comprobante.append("Estado Envío: ").append(envio.getEstado() != null ? envio.getEstado() : "N/A").append("\n");

            double totalTarifa = GestorTarifa.getInstance().getTarifa().calcularTotalTarifa(envio);
            comprobante.append("Tarifa del Envío: $").append(String.format("%.2f", totalTarifa)).append("\n");

            if (monto > totalTarifa && estadoPago == EstadoPago.APROBADO) {
                double cambio = monto - totalTarifa;
                comprobante.append("Cambio: $").append(String.format("%.2f", cambio)).append("\n");
            }
        }

        comprobante.append("==========================================\n");

        return comprobante.toString();
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

}

