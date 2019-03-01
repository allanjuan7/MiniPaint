package formas;

import javafx.scene.paint.Color;

public class Quadrilatero extends Forma {

    private double base, altura;

    public Quadrilatero(double xInicial, double yInicial, Color cor, double base, double altura, int id){
        super(xInicial, yInicial, cor, id);
        this.base = base;
        this.altura = altura;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getBase() {
        return base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getAltura() {
        return altura;
    }

    public String toString(){
        return "Quadrilátero " + id;
    }
}
