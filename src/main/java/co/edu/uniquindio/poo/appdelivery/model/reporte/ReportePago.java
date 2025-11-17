package co.edu.uniquindio.poo.appdelivery.model.reporte;

import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;

import java.util.ArrayList;

public class ReportePago extends Reporte {

    private ArrayList<Pago> listaPagos;

    public ReportePago(IFormatoExportacion formatoExportacion) {
        super(formatoExportacion);
        this.listaPagos = new ArrayList<>();
    }

    @Override
    public void generarReporte() {
        System.out.println("Generando reporte de pagos...");
        // Aquí pones la lógica real
        exportarFormato();
    }

    public void agregarPago(Pago pago) {
        listaPagos.add(pago);
    }
}