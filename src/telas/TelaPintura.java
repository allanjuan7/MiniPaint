package telas;

import javafx.scene.canvas.Canvas;

/**
 * Extensão da classe Canvas que tem a capacidade de guardar a ultima posição clicada.
 *
 */
public class TelaPintura extends Canvas {

    private double lastX, lastY;

    public double getLastX() {
        return lastX;
    }

    public void setLastX(double lastX) {
        this.lastX = lastX;
    }

    public double getLastY() {
        return lastY;
    }

    public void setLastY(double lastY) {
        this.lastY = lastY;
    }

}
