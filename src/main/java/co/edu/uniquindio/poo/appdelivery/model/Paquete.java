package co.edu.uniquindio.poo.appdelivery.model;

public class Paquete {

    private double peso;
    private int ancho;
    private int largo;
    private int costoPaquete;

    public Paquete(double peso, int ancho, int largo, int costoPaquete) {
        this.peso = peso;
        this.ancho = ancho;
        this.largo = largo;
        this.costoPaquete = costoPaquete;

    }
    public double calcularCostoPaquete() {
        double costoBase = 5000; // Costo base fijo
        double costoPorPeso = 2000; // Costo adicional por cada kilogramo
        double costoPorDimension = 1000; // Costo adicional por cada centímetro de ancho o largo

        double costoTotal = costoBase + (costoPorPeso * peso) + (costoPorDimension * (ancho + largo));
        return costoTotal;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getCostoPaquete() {
        return costoPaquete;
    }

    public void setCostoPaquete(int costoPaquete) {
        this.costoPaquete = costoPaquete;
    }
}


