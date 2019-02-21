package Formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Quadrilatero extends Forma {

    private double base, altura;

    public Quadrilatero(double xInicial, double yInicial, Color cor, GraphicsContext contexto, double base, double altura){
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

        contexto.setStroke(cor);
        contexto.strokeRect(xInicial, yInicial, base, altura);
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
