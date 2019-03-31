package formas;

import controle.Controle;
import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import popup.Mensagem;
import popup.TelaEdicaoCirculo;
import telas.TelaPintura;

public class Circulo extends Forma{

    private double raio;

    /**
     * Construtor do círculo que recebe as coordenadas âncora e o raio.
     * Checa se o raio é válido e contrói o círculo.
     *
     * @param xInicial Coordenada X do ponto âncora do círculo.
     * @param yInicial Coordenada Y do ponto âncora do círculo.
     * @param cor Cor do círculo.
     * @param raio Medida do raio do círculo.
     * @param id id da figura2D no array.
     * @throws ValorDeEntradaNegativoException Exceção lançada caso o valor do círculo inválido.
     */
    public Circulo(double xInicial, double yInicial, Color cor, double raio, int id) throws ValorDeEntradaNegativoException {
        super(xInicial, yInicial, cor, id);
        if (raio <= 0){
            throw new ValorDeEntradaNegativoException();
        }
        this.raio = raio;
    }

    /**
     * Método get do raio.
     *
     * @return Medida do raio do círculo.
     */
    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) throws ValorDeEntradaNegativoException{
        if (raio <= 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.raio = raio;
    }

    /**
     * Método que recebe o contexto gráfico do canvas e, dependendo do modo de desenho, desenha o contorno ou a figura preenchida.
     *
     * @param contexto GraphicsContext da tela em que será desenhada a forma.
     */
    public void desenhar(GraphicsContext contexto){
        super.desenhar(contexto);

        if (modoDeDesenho.equals("Contornar"))
            contexto.strokeOval(xInicial, yInicial, raio, raio);

        if (modoDeDesenho.equals("Preencher"))
            contexto.fillOval(xInicial, yInicial, raio, raio);
    }

    /**
     * Método que cria uma janela de pop-up para editar o círculo.
     *
     * @param telaPintura  tela onde a forma está pintada
     * @param listView Lista de figuras2D
     */
    public void editar(TelaPintura telaPintura, ListView listView){
        TelaEdicaoCirculo telaEdicao = new TelaEdicaoCirculo(this);

        telaEdicao.getBtnConfirmar().setOnAction( e -> {
            try {

                setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                setRaio(telaEdicao.getEntradaRaio().getValorCampo());
                setCor(telaEdicao.getColorPicker().getValue());

                telaEdicao.getStage().close();
                Controle.controle.apagarQuadro();
                Controle.controle.atualizarListViewERedesenhar(telaPintura, listView);

            } catch (ValorDeEntradaNegativoException ex){
                ex.mostrarPopUpDeErro();
            }
        });

        telaEdicao.mostrar();
    }

    /**
     * Método toString do triângulo.
     *
     * @return String que será mostrada no listView.
     */
    public String toString(){
        return "Círculo " + id;
    }
}
