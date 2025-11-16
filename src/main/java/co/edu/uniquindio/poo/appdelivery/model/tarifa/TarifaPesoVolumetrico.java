package co.edu.uniquindio.poo.appdelivery.model.tarifa;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;

public class TarifaPesoVolumetrico implements ITarifaStrategy{

    @Override
    public double calcularTarifa(Envio envio,Tarifa tarifa) {
        double valorPesoPaquete = envio.getPaquete().getPeso();
        return valorPesoPaquete * envio.getCosto();
    }

    @Override
    public String desgloseTarifa(Envio envio,Tarifa tarifa) {
        return "El precio de la Tarifa tomando en cuenta el peso es: " + calcularTarifa(envio,tarifa);
    }
}
