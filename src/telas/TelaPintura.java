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

        if (modoDeDesenho.equals("Contornar"))
            contexto.setStroke(forma.getCor());

        if (modoDeDesenho.equals("Preencher"))
            contexto.setFill(forma.getCor());

        if (forma instanceof Quadrilatero){

            Quadrilatero quadrilatero = (Quadrilatero) forma;

            if (modoDeDesenho.equals("Contornar"))
                contexto.strokeRect(quadrilatero.getxInicial(), quadrilatero.getyInicial(), quadrilatero.getBase(), quadrilatero.getAltura());

            if (modoDeDesenho.equals("Preencher"))
                contexto.fillRect(quadrilatero.getxInicial(), quadrilatero.getyInicial(), quadrilatero.getBase(), quadrilatero.getAltura());

        } else if (forma instanceof Triangulo){

            Triangulo triangulo = (Triangulo) forma;

            double Ax, Ay, Bx, By, Cx, Cy;

            Ax = triangulo.getxInicial();
            Ay = triangulo.getyInicial();
            Bx = Ax + triangulo.getBase()/2;
            By = Ay + triangulo.getAltura();
            Cx = Ax - triangulo.getBase()/2; Cy = Ay + triangulo.getAltura();

            if (modoDeDesenho.equals("Contornar"))
                contexto.strokePolygon(new double [] {Ax, Bx, Cx},
                        new double [] {Ay, By, Cy}, 3);

            if (modoDeDesenho.equals("Preencher"))
                contexto.fillPolygon(new double [] {Ax, Bx, Cx},
                        new double [] {Ay, By, Cy}, 3);

        } else if (forma instanceof Circulo){

            Circulo circulo = (Circulo) forma;

            if (modoDeDesenho.equals("Contornar"))
                contexto.strokeOval(circulo.getxInicial(), circulo.getyInicial(), circulo.getRaio(), circulo.getRaio());

            if (modoDeDesenho.equals("Preencher"))
                contexto.fillOval(circulo.getxInicial(), circulo.getyInicial(), circulo.getRaio(), circulo.getRaio());
        }
    }
}
