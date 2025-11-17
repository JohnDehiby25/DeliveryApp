package co.edu.uniquindio.poo.appdelivery.service;

import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;

import java.util.List;

public class RepartidorService {

    private List<Repartidor> repartidores;

    public Repartidor buscarRepartidorEntity(String documentoRepartidor) {
        for (Repartidor  repartidor : repartidores) {
            if (repartidor.getDocumento().equalsIgnoreCase(documentoRepartidor)) {
                return repartidor;
            }
        }
        return null;
    }

}
