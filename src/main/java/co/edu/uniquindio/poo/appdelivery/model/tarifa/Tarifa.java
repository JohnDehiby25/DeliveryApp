package co.edu.uniquindio.poo.appdelivery.model.tarifa;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

public class Tarifa {

    private ITarifaStrategy estrategia;
    private double tarifaBase = 20000;

    public Tarifa(ITarifaStrategy estrategia){
        this.estrategia= estrategia;
    }

    public void setEstrategia(ITarifaStrategy estrategia){
        this.estrategia = estrategia;
    }
    public String obtenerDesgloseEnvio(Envio envio){
        return estrategia.desgloseTarifa(envio,this) +
                "Id Envio: " + envio.getIdEnvio() +
                "Direccion origen: " + envio.getOrigen() +
                "Direccion destino: " + envio.getDestino() +
                "Costo Total Tarifa: " + calcularTotalTarifa(envio);

    }
    public double calcularTotalTarifa(Envio envio){
        return tarifaBase + estrategia.calcularTarifa(envio,this);
    }
    public void setTarifaBase(double tarifaBase){
        this.tarifaBase = tarifaBase;
    }

}
