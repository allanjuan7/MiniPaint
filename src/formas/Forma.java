package formas;

import javafx.scene.paint.Color;

public abstract class Forma {

    protected double xInicial, yInicial;
    protected Color cor;

    public Forma(double xInicial, double yInicial, Color cor){
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.cor = cor;
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
