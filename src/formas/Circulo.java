package formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circulo extends Forma{

    private double raio;

    // No caso do círculo, as coordenadas iniciais representam o seu centro.
    public Circulo(double xInicial, double yInicial, Color cor, GraphicsContext contexto, double raio){
        super(xInicial, yInicial, cor, contexto);
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    // AINDA NÃO FOI TESTADO
    public void desenhar(){
        contexto.setStroke(cor);
        contexto.strokeOval(xInicial, yInicial, raio, raio);
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
