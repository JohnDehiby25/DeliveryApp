package co.edu.uniquindio.poo.appdelivery.model.reporte;


public class FormatoPDF implements IFormatoExportacion {

    @Override
    public void exportarFormato() {
        System.out.println("Exportando reporte en formato PDF...");
    }
}
