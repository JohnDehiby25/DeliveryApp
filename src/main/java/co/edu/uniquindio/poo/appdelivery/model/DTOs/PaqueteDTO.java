package co.edu.uniquindio.poo.appdelivery.model.DTOs;

public class PaqueteDTO {
    private double peso;
    private int ancho;
    private int largo;
    private int costoPaquete;

    public PaqueteDTO(double peso, int largo, int costoPaquete, int ancho) {
        this.peso = peso;
        this.largo = largo;
        this.costoPaquete = costoPaquete;
        this.ancho = ancho;
    }
    public PaqueteDTO(){}

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCostoPaquete() {
        return costoPaquete;
    }

    public void setCostoPaquete(int costoPaquete) {
        this.costoPaquete = costoPaquete;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}
