package co.edu.uniquindio.poo.appdelivery.model.tarifa;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

public class TarifaDistanciaStrategy implements ITarifaStrategy{


    @Override
    public double calcularTarifa(Envio envio) {
        return envio.getDistanciaPorKm() ;
    }

    @Override
    public String desgloseTarifa(Envio envio) {
        return envio.;
    }
}
