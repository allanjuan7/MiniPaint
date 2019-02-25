package telas;

import formas.Circulo;
import formas.Forma;
import formas.Quadrilatero;
import formas.Triangulo;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;

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

    public void desenhar(Forma f){
        GraphicsContext contexto = getGraphicsContext2D();

        if (f instanceof Quadrilatero){
            Quadrilatero quadrilatero = (Quadrilatero) f;
            contexto.setStroke(quadrilatero.getCor());
            contexto.strokeRect(quadrilatero.getxInicial(), quadrilatero.getyInicial(), quadrilatero.getBase(), quadrilatero.getAltura());
        } else if (f instanceof Triangulo){

            Triangulo triangulo = (Triangulo) f;
            contexto.setFill(triangulo.getCor());
            Polygon poligon = new Polygon();
            double Ax, Ay, Bx, By, Cx, Cy;
            Ax = triangulo.getxInicial();
            Ay = triangulo.getyInicial();
            Bx = Ax + triangulo.getBase()/2;
            By = Ay + triangulo.getAltura();
            Cx = Ax - triangulo.getBase()/2; Cy = Ay + triangulo.getAltura();

        /* Desenhamos o polígono passando como parâmetros
        um vetor com as coordenadas x, outro com as respectivas coordenadas e y, e o número de vértices*/
            contexto.fillPolygon(new double [] {Ax, Bx, Cx},
                    new double [] {Ay, By, Cy}, 3);
        } else if (f instanceof Circulo){
            Circulo circulo = (Circulo) f;
            contexto.setStroke(circulo.getCor());
            contexto.strokeOval(circulo.getxInicial(), circulo.getyInicial(), circulo.getRaio(), circulo.getRaio());
        }
    }
}
