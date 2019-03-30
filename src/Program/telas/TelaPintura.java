package Program.telas;

import Program.formas.Forma;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * TelaPintura é uma subclasse de Canvas que guarda a ultima posição clicada.
 *
 */
public class TelaPintura extends Canvas {

    private double xInicial, yInicial;

    /**
     * Método get do atributo xInicial.
     *
     * @return double contendo o xInicial.
     */
    public double getxInicial() {
        return xInicial;
    }

    /**
     * Método set do atributo xInicial.
     * @param xInicial double contendo o xInical.
     */
    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    /**
     * Método get do atributo yInicial.
     *
     * @return double contendo o yInicial.
     */
    public double getyInicial() {
        return yInicial;
    }
    /**
     * Método get do atributo yInicial.
     *
     * @param yInicial double contendo o yInicial.
     */
    public void setyInicial(double yInicial) {
        this.yInicial = yInicial;
    }

    /**
     * Método chamado para desenhar Program.formas na tela. Redireciona o
     * modo de desenho e o contexto gráfico para a forma.
     *
     * @see Forma
     * @param forma objeto do tipo forma a ser desenhado.
     * @param modoDeDesenho modo de desenho selecionado, preencher ou contornar.
     */
    public void desenhar(Forma forma, String modoDeDesenho){

        GraphicsContext contexto = getGraphicsContext2D();
        forma.setModoDeDesenho(modoDeDesenho);
        forma.desenhar(contexto);
    }
}
