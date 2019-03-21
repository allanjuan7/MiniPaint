package telas;

import formas.Circulo;
import formas.Forma;
import formas.Quadrilatero;
import formas.Triangulo;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;

/**
 * TelaPintura é uma subclasse de Canvas que guarda a ultima posição clicada.
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

    public void desenhar(Forma forma, String modoDeDesenho){

        GraphicsContext contexto = getGraphicsContext2D();
        forma.setModoDeDesenho(modoDeDesenho);

        forma.desenhar(contexto);
    }
}
