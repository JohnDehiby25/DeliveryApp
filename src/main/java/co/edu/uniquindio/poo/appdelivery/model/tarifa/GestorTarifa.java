package co.edu.uniquindio.poo.appdelivery.model.tarifa;

public class GestorTarifa {

    private static GestorTarifa instance;
    private Tarifa tarifa;

    private GestorTarifa(){
        tarifa = new Tarifa(new TarifaDistanciaStrategy());
    }

    public static GestorTarifa getInstance(){
        if(instance == null){
            instance = new GestorTarifa();
        }
        return instance;
    }
    public Tarifa getTarifa(){
        return tarifa;
    }
    public void setTarifa(Tarifa tarifa){
        this.tarifa = tarifa;
    }

}
