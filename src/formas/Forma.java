package formas;

import javafx.scene.paint.Color;

public abstract class Forma {

    protected double xInicial, yInicial;
    //protected GraphicsContext contexto;
    protected Color cor;

    public Forma(double xInicial, double yInicial, Color cor){
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.cor = cor;
    }

    /* ALLAN: Mantive apenas os Getters no encapsulamento.
    Já que não iremos alterar propriamente os valores da nossa forma, faria sentido ter métodos SETTERS?*/

    public double getxInicial() {
        return xInicial;
    }

    public double getyInicial() {
        return yInicial;
    }

    public Color getCor() {
        return cor;
    }

    public abstract void desenhar();

    public abstract void editar();
}
