package co.edu.uniquindio.poo.appdelivery.model.reporte;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

import java.util.ArrayList;

public class ReporteEnvio extends Reporte {

    private ArrayList<Envio> listaEnvios;

    public ReporteEnvio(IFormatoExportacion formatoExportacion) {
        super(formatoExportacion);
        this.listaEnvios = new ArrayList<>();
    }

    @Override
    public void generarReporte() {
        System.out.println("Generando reporte de envíos...");
        exportarFormato();
    }

    public void agregarEnvio(Envio envio) {
        listaEnvios.add(envio);
    }
}