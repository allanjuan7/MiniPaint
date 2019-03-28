package formas;

import controle.Controle;
import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import popup.Mensagem;
import popup.TelaEdicaoPoligono;
import telas.TelaPintura;

public class Triangulo extends Forma {


    private double base, altura;
    private String modelo;

    /**
     * Construtor do triãngulo que recebe as coordenadas âncora, base, altura e modelo do triãngulo.
     * Checa se a base e a altura são válidas e contrói o triângulo.
     *
     * @param xInicial Coordenada X do ponto âncora do triângulo.
     * @param yInicial Coordenada Y do ponto âncora do triângulo.
     * @param cor Cor do triângulo.
     * @param base Medida da base do triângulo.
     * @param altura Medida da altura do triângulo.
     * @param id Id da figura2D no array.
     * @param modelo
     * @throws ValorDeEntradaNegativoException Exceção lançada caso os valores da base ou altura sejam negativos.
     */
    public Triangulo(double xInicial, double yInicial, Color cor, double base, double altura, int id, String modelo) throws ValorDeEntradaNegativoException{
        super(xInicial, yInicial, cor, id);

        if (base < 0 || altura < 0){
            throw new ValorDeEntradaNegativoException();
        }

        this.base = base;
        this.altura = altura;
        this.modelo = modelo;
    }

    /**
     * Método set da base. Não permite que a base seja negativa.
     *
     * @param base Medida da base do triângulo.
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
     * @return Medida da base do triângulo.
     */
    public double getBase() {
        return base;
    }

    /**
     * Método set da altura. Não permite que a altura seja negativa.
     *
     * @param altura Medida da altura do triângulo.
     * @throws ValorDeEntradaNegativoException
     */
    public void setAltura(double altura) throws ValorDeEntradaNegativoException {
        if (altura < 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.altura = altura;
    }

    /**
     * Método get da altura
     *
     * @return Medida da altura do triângulo.
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Método que recebe o contexto gráfico do canvas e, dependendo do modo de desenho, desenha o contorno ou a figura preenchida.
     *
     * @param contexto GraphicsContext da tela em que será desenhada a forma.
     */
    /* Apenas um dos 4 casos está sendo desenhado corretamente.
    * Os triângulos estão sendo desenhados com as dimensões corretas, porém em posições erradas.
    * Talvez seja necessário passar também as coordenadas do ponto final como parâmetro.*/
    public void desenhar(GraphicsContext contexto){
        super.desenhar(contexto);

        double Ax, Ay, Bx, By, Cx, Cy;

        Ax = xInicial; Ay = yInicial;
        Bx = xInicial; By = yInicial;
        Cx = xInicial; Cy = yInicial;

        if (modelo == "A"){
            By += altura;
            Cx += base; Cy += altura;
        }

        if (modelo == "B"){
            Bx += base;
            Cx += base; Cy -= altura;
        }

        if (modelo == "C"){
            Bx -= base;
            Cx -= base; Cy -= altura;
        }

        if (modelo == "D"){
            By += altura;
            Cx -= base; Cy += altura;
        }

        if (modoDeDesenho.equals("Contornar"))
            contexto.strokePolygon(new double [] {Ax, Bx, Cx},
                    new double [] {Ay, By, Cy}, 3);

        if (modoDeDesenho.equals("Preencher"))
            contexto.fillPolygon(new double [] {Ax, Bx, Cx},
                    new double [] {Ay, By, Cy}, 3);
    }

    /**
     * Método que cria uma janela de pop-up para editar o triângulo.
     *
     * @param telaPintura  tela onde a forma está pintada
     * @param listView Lista de figuras2D.
     */
    public void editar(TelaPintura telaPintura, ListView listView){
        TelaEdicaoPoligono telaEdicao = new TelaEdicaoPoligono(this);

        telaEdicao.getBtnConfirmar().setOnAction(e -> {
            try {

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
     * Método toString do triângulo.
     *
     * @return String que será mostrada no listView.
     */
    public String toString(){
        return "Triângulo " + id;
    }
}
