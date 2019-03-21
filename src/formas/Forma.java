package formas;

import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import telas.TelaPintura;

import java.io.Serializable;
import java.util.List;

public abstract class Forma implements Serializable {

    protected double xInicial, yInicial;
    transient protected Color cor;
    protected String rgbString, modoDeDesenho;
    protected int id;

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

    public String getModoDeDesenho() {
        return modoDeDesenho;
    }

    public void setModoDeDesenho(String modoDeDesenho) {
        this.modoDeDesenho = modoDeDesenho;
    }

    public void setRgbString(String rgbString) {
        this.rgbString = rgbString;
    }

    public String getRgbString() {
        return rgbString;
    }

    public void desenhar(GraphicsContext contexto){
        if (modoDeDesenho.equals("Contornar"))
            contexto.setStroke(cor);

        if (modoDeDesenho.equals("Preencher"))
            contexto.setFill(cor);
    }

    public abstract void editar(TelaPintura telaPintura, ListView listView);

    public abstract String toString();

}
