package formas;

import controle.Controle;
import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import popup.Mensagem;
import popup.TelaEdicaoPoligono;
import telas.TelaPintura;

public class Quadrilatero extends Forma {

    private double base, altura;

    /**
     * Construtor do quadrilátero que recebe as coordenadas âncora, base e altura do quadrilátero.
     * Checa se a base e a altura são válidas e contrói o quadrilátero.
     *
     * @param xInicial Coordenada X do ponto âncora do quadrilátero
     * @param yInicial Coordenada Y do ponto âncora do quadrilátero
     * @param cor Cor do quadrilátero
     * @param base Medida da base do quadrilátero
     * @param altura Medida da altura do quadrilátero
     * @param id Id da figura2D no array
     * @throws ValorDeEntradaNegativoException Exceção lançada caso os valores da base ou altura sejam negativos.
     */
    public Quadrilatero(double xInicial, double yInicial, Color cor, double base, double altura, int id) throws ValorDeEntradaNegativoException{
        super(xInicial, yInicial, cor, id);

        if (base < 0 || altura < 0){
            throw new ValorDeEntradaNegativoException();
        }

        this.base = base;
        this.altura = altura;
    }

    /**
     * Método set para a base. Não permite que a base seja negativa.
     *
     * @param base Medida da base do quadrilátero.
     * @throws ValorDeEntradaNegativoException Exceção lançada caso os valores da base ou altura sejam negativos.
     */
    public void setBase(double base) throws ValorDeEntradaNegativoException {
        if (base < 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.base = base;
    }

    /**
     * Método get da base.
     *
     * @return Medida da base do quadrilátero.
     */
    public double getBase() {
        return base;
    }

    /**
     * Método set da altura. Não permite que a mesma seja negativa.
     *
     * @param altura Medida da altura do quadrilátero.
     * @throws ValorDeEntradaNegativoException Exceção lançada caso os valores da base ou altura sejam negativos.
     */
    public void setAltura(double altura) throws ValorDeEntradaNegativoException {
        if (altura < 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.altura = altura;
    }

    /**
     * Método get da altura.
     *
     * @return Medida da altura do quadrilátero.
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Método que recebe o contexto gráfico do canvas e, dependendo do modo de desenho, desenha o contorno ou a figura preenchida.
     *
     * @param contexto GraphicsContext da tela em que será desenhada a forma.
     */
    public void desenhar(GraphicsContext contexto){
        super.desenhar(contexto);

        if (modoDeDesenho.equals("Contornar"))
            contexto.strokeRect(xInicial, yInicial, base, altura);

        if (modoDeDesenho.equals("Preencher"))
            contexto.fillRect(xInicial, yInicial, base, altura);
    }

    /**
     * Método que cria uma janela de pop-up para editar o quadrilátero.
     *
     * @param telaPintura  tela onde a forma está pintada.
     * @param listView Lista de figuras2D.
     */
    public void editar(TelaPintura telaPintura, ListView listView){
        TelaEdicaoPoligono telaEdicao = new TelaEdicaoPoligono(this);

        telaEdicao.getBtnConfirmar().setOnAction(e -> {

            try{

                setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                setBase(telaEdicao.getEntradaBase().getValorCampo());
                setAltura(telaEdicao.getEntradaAltura().getValorCampo());
                setCor(telaEdicao.getColorPicker().getValue());

                telaEdicao.getStage().close(); Controle.controle.apagarQuadro();
                Controle.controle.atualizarListViewERedesenhar(telaPintura, listView);

            } catch(ValorDeEntradaNegativoException ex){

                Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", "Valores invalidos", 100, 500);
                mensagemDeErro.mostrar();
            }
        });

        telaEdicao.mostrar();
    }

    /**
     * Método toString do quadrilátero.
     *
     * @return String que será mostrada no listView.
     */
    @Override
    public String toString(){
        return "Quadrilátero " + id;
    }
}
