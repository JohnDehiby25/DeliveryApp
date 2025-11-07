package co.edu.uniquindio.poo.appdelivery.model.tarifa;

public class GestorTarifa {

    private static GestorTarifa instance;
    private Tarifa tarifa;

    private GestorTarifa(Tarifa tarifa ){
        this.tarifa = tarifa;
    }

    public static GestorTarifa getInstance(){
        if(instance == null){
            instance = new GestorTarifa(tarifa);
        }
        return instance;
    }
    public Tarifa getTarifa(){
        return tarifa;
    }

}
