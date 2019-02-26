package formas;

import javafx.scene.paint.Color;

public abstract class Forma {

    protected double xInicial, yInicial;
    protected Color cor;
    protected int id;

    public Forma(double xInicial, double yInicial, Color cor, int id){
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.cor = cor;
        this.id = id;
    }

    public double getxInicial() {
        return xInicial;
    }

    public double getyInicial() {
        return yInicial;
    }

    public Color getCor() {
        return cor;
    }

    public abstract void editar();

}
