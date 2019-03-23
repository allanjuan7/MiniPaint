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

    /**
     * Construtor padrão, não instanciável, das formas contendo a posição inical, cor e id
     * da forma em questão.
     *
     * @param xInicial posição X inicial da forma.
     * @param yInicial posição Y inicial da forma.
     * @param cor cor da forma.
     * @param id id da forma.
     * @throws ValorDeEntradaNegativoException exceção lançada caso os valores X e Y sejam negativos.
     */
    public Forma(double xInicial, double yInicial, Color cor, int id) throws ValorDeEntradaNegativoException {

        if (xInicial < 0 || yInicial < 0) {
            throw new ValorDeEntradaNegativoException();
        }

        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.cor = cor;
        this.id = id;
    }

    /**
     * Método set do xInicial, cujo valor não pode ser negativo.
     *
     * @param xInicial posição X inicial da forma.
     * @throws ValorDeEntradaNegativoException exceção lançada caso o valor de X seja negativo.
     */
    public void setxInicial(double xInicial) throws ValorDeEntradaNegativoException {
        if (xInicial < 0){
            throw new ValorDeEntradaNegativoException();
        }
        this.xInicial = xInicial;
    }

    /**
     * Método get do xInicial.
     *
     * @return um double contendo o X inicial da forma.
     */
    public double getxInicial() {
        return xInicial;
    }

    /**
     * Método set do yInicial, cujo valor não pode ser negativo.
     *
     * @param yInicial posição Y inicial da forma.
     * @throws ValorDeEntradaNegativoException exceção lançada caso o valor de X seja negativo.
     */
    public void setyInicial(double yInicial) throws ValorDeEntradaNegativoException {
        if (yInicial < 0){
            throw new ValorDeEntradaNegativoException();
        }
        this.yInicial = yInicial;
    }

    /**
     * Método get do yInicial.
     *
     * @return retorna um double contendo o Y inicial da forma.
     */
    public double getyInicial() {
        return yInicial;
    }

    /**
     * Método set da cor.
     *
     * @param cor objeto do tipo Color com a cor da forma.
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }

    /**
     * Método get da cor
     *
     * @return retorna um Color contendo a cor da forma.
     */
    public Color getCor() {
        return cor;
    }

    /**
     * Método get do modo de desenho.
     *
     * @return retorna uma string contendo "Preencher" ou "Contornar".
     */
    public String getModoDeDesenho() {
        return modoDeDesenho;
    }

    /**
     * Método set do modo de desenho.
     *
     * @param modoDeDesenho requer uma string contendo "Preencher" ou "Contornar".
     */
    public void setModoDeDesenho(String modoDeDesenho) {
        this.modoDeDesenho = modoDeDesenho;
    }

    /**
     * Método set da rgbString.
     * Como os atributos Color não podem ser serializados a cor será guardada como
     * o toString do Color
     *
     * @param rgbString String contendo o toString de um Color.
     */
    public void setRgbString(String rgbString) {
        this.rgbString = rgbString;
    }

    /**
     * Método get da rgbString
     *
     * @return String contendo o toString de um Color.
     */
    public String getRgbString() {
        return rgbString;
    }

    /**
     * Método responsável por desenhar as formas na tela de pintura.
     * Será ampliado por cada tipo de forma específica a ser desenhada.
     * A superclasse Forma é responsável por setar a cor que a forma será desenhada.
     *
     * @param contexto GraphicsContext da tela em que será desenhada a forma.
     */
    public void desenhar(GraphicsContext contexto){
        if (modoDeDesenho.equals("Contornar"))
            contexto.setStroke(cor);

        if (modoDeDesenho.equals("Preencher"))
            contexto.setFill(cor);
    }

    /**
     * Método responsável por editar as formas implementado pelas classes filhas.
     *
     * @see Circulo
     * @see Quadrilatero
     * @see Triangulo
     * @param telaPintura  tela onde a forma está pintada
     * @param listView
     */
    public abstract void editar(TelaPintura telaPintura, ListView listView);

    /**
     * Método toString implementado pelas classes filhas.
     *
     * @see Circulo
     * @see Quadrilatero
     * @see Triangulo
     * @return String com informações sobre a forma
     */
    public abstract String toString();

}
