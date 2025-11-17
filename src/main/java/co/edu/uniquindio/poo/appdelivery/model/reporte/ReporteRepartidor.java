package co.edu.uniquindio.poo.appdelivery.model.reporte;

import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;

import java.util.ArrayList;

public class ReporteRepartidor extends Reporte {

    private ArrayList<Repartidor> listaRepartidores;

    public ReporteRepartidor(IFormatoExportacion formatoExportacion) {
        super(formatoExportacion);
        this.listaRepartidores = new ArrayList<>();
    }

    @Override
    public void generarReporte() {
        System.out.println("Generando reporte de repartidores...");
        exportarFormato();
    }

    public void agregarRepartidor(Repartidor repartidor) {
        listaRepartidores.add(repartidor);
    }
}
