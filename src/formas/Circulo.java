package formas;

import javafx.scene.paint.Color;

public class Circulo extends Forma{

    private double raio;

    public Circulo(double xInicial, double yInicial, Color cor, double raio, int id){
        super(xInicial, yInicial, cor, id);
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public String toString(){
        return "Círculo " + id;
    }
}
