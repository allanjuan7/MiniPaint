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



    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    public double getxInicial() {
        return xInicial;
    }

    public void setyInicial(double yInicial) {
        this.yInicial = yInicial;
    }

    public double getyInicial() {
        return yInicial;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return cor;
    }

    public abstract String toString();

}
