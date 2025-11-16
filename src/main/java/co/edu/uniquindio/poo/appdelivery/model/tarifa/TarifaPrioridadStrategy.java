package co.edu.uniquindio.poo.appdelivery.model.tarifa;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

public class TarifaPrioridadStrategy implements ITarifaStrategy {

    @Override
    public double calcularTarifa(Envio envio,Tarifa tarifa) {
        if(envio.getCosto() > 400000 && envio.getDistanciaporKm() < 8){
            tarifa.setTarifaBase(0);
        }
        return envio.getCosto();
    }

    @Override
    public String desgloseTarifa(Envio envio,Tarifa tarifa) {
        return "El costo de la tarifa prioritaria es de: " + calcularTarifa(envio,tarifa) +
                " ya que se cobra solo el envio por compras mayores a 400.000";
    }

}
