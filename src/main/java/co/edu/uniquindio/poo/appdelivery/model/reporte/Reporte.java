package co.edu.uniquindio.poo.appdelivery.model.reporte;

public abstract class Reporte {

    protected IFormatoExportacion formatoExportacion;

    public Reporte(IFormatoExportacion formatoExportacion) {
        this.formatoExportacion = formatoExportacion;
    }

    public void exportarFormato() {
        formatoExportacion.exportarFormato();
    }

    public abstract void generarReporte();
}
