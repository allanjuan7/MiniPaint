package Formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import telas.TelaPintura;

public class Triangulo extends Forma {

    private double base, altura;

    public Triangulo(double xInicial, double yInicial, Color cor, GraphicsContext contexto, double base, double altura){
        super(xInicial, yInicial, cor, contexto);
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    // AINDA NÃO FOI TESTADO
    public void desenhar(){
        // Cuidado - o Polygon pode causar ataques epilépticos aos espectadores mais sensíveis!
        Polygon triangulo = new Polygon();
        double Ax, Ay, Bx, By, Cx, Cy;

        Ax = xInicial; Ay = yInicial;
        Bx = xInicial + base/2; By = yInicial + altura;
        Cx = xInicial - base/2; Cy = yInicial + altura;

        /* Desenhamos o polígono passando como parâmetros
        um vetor com as coordenadas x, outro com as respectivas coordenadas e y, e o número de vértices*/
        contexto.fillPolygon(new double [] {Ax, Bx, Cx},
                new double [] {Ay, By, Cy}, 3);
    }

    // A FAZER
    public void editar(){
        /* Esse método deve:

        1) Abrir uma janela PopUp que permitirá que o usuário edite a forma

        2) Apagar a forma velha e desenhar a nova forma com valores atualizados

        3) Atualizar os valores salvos em "Figura"
         */
    }
}
