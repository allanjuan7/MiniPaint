package formas;

import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class Forma implements Serializable {

    protected double xInicial, yInicial;
    transient protected Color cor;
    protected int id;
    private String rgbString;


    public Forma(double xInicial, double yInicial, Color cor, int id) throws Exception{

        if (xInicial < 0 || yInicial < 0) {
            throw new ValorDeEntradaNegativoException();
        }

        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.cor = cor;
        this.id = id;
    }

    public void setxInicial(double xInicial) throws ValorDeEntradaNegativoException {
        if (xInicial < 0){
            throw new ValorDeEntradaNegativoException();
        }

        this.xInicial = xInicial;

    }

    public double getxInicial() {
        return xInicial;
    }

    public void setyInicial(double yInicial) throws ValorDeEntradaNegativoException {
        if (yInicial < 0){
            throw new ValorDeEntradaNegativoException();
        }
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

    public void setRgbString(String rgbString) {
        this.rgbString = rgbString;
    }

    public String getRgbString() {
        return rgbString;
    }

    public abstract String toString();

}
