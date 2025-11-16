package co.edu.uniquindio.poo.appdelivery.model.tarifa;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

public interface ITarifaStrategy {
    double calcularTarifa(Envio envio, Tarifa tarifa);
    String desgloseTarifa(Envio envio, Tarifa tarifa);

}
