package telas;

import javafx.scene.canvas.Canvas;

/**
 * Extensão da classe Canvas que tem a capacidade de guardar a ultima posição clicada.
 *
 */
public class TelaPintura extends Canvas {

    private double xInicial, yInicial;

    public double getxInicial() {
        return xInicial;
    }

    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    public double getyInicial() {
        return yInicial;
    }

    public void setyInicial(double yInicial) {
        this.yInicial = yInicial;
    }

}
