package co.edu.uniquindio.poo.appdelivery.model.tarifa;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

public class Tarifa {

    private ITarifaStrategy estrategia;
    private static double tarifaInicial = 20000;

    public Tarifa(ITarifaStrategy estrategia){
        this.estrategia= estrategia;
    }

    public void setEstrategia(ITarifaStrategy estrategia){
        this.estrategia = estrategia;
    }
    public String obtenerDesgloseEnvio(Envio envio){
        return "Id Envio: " + envio.getIdEnvio() +
                "Direccion origen: " + envio.getOrigen() +
                "Direccion destino: " + envio.getDestino() +
                "Costo Total Tarifa: " + calcularTotalTarifa(envio);

    }
    public double calcularTotalTarifa(Envio envio){
        return tarifaInicial + estrategia.calcularTarifa(envio);
    }




}
