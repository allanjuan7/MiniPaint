package telas;

import formas.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class TelaPrincipalController implements EventHandler<ActionEvent> {

    private int nQuadrilateros, nTriangulos, nCirculos;

    private Figura figura = new Figura();

    private boolean quadrilateroSelecionado, circuloSelecionado, trianguloSelecionado;

    @FXML
    private ListView<Forma> formas;

    @FXML
    private Label instrucoes;

    @FXML
    private Button ferramentaTriangulo;

    @FXML
    private Button ferramentaQuadrilatero;

    @FXML
    private Button ferramentaCirculo;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private ColorPicker selecCor;

    @FXML
    private TelaPintura telaPintura;

    /**
     * Metodo usado na TelaPintura para guardar a posição X e Y em que a tela foi clicada.
     *
     * @param event Evento do botão do mouse ser pressionado na tela.
     */
    public void onMousePressed(MouseEvent event) {
        telaPintura.setxInicial(event.getX());
        telaPintura.setyInicial(event.getY());
    }

    /**
     * Metodo usado na TelaPintura para desenhar na tela assim que o mouse para de ser arrastado.
     * Usa a cor selecionada pelo ColorPicker selecCor.
     *
     * @param event Evento do botão do mouse ser solto da tela.
     */

    public void onMouseReleased(MouseEvent event){

        double xFinal, yFinal, xInicial, yInicial, largura, altura;

        xFinal = event.getX();
        yFinal = event.getY();

        xInicial = telaPintura.getxInicial();
        yInicial = telaPintura.getyInicial();

        largura = abs(xInicial - xFinal);
        altura = abs(yInicial - yFinal);

        xInicial = Math.min(xFinal, xInicial);
        yInicial = Math.min(yFinal, yInicial);

        Forma f = null;

        if (quadrilateroSelecionado) {
            f = new Quadrilatero(xInicial, yInicial, selecCor.getValue(), largura, altura, ++nQuadrilateros);
            //telaPintura.desenhar(q);
            //figura.addForma(q);

        } else if (trianguloSelecionado) {
            f = new Triangulo(xInicial, yInicial, selecCor.getValue(), largura, altura, ++nTriangulos);
            //telaPintura.desenhar(t);
            //figura.addForma(t);

        } else if (circuloSelecionado) {
            double raio = sqrt((largura * largura) + (altura * altura) );
            f = new Circulo(xInicial, yInicial, selecCor.getValue(), raio, ++nCirculos);
            //telaPintura.desenhar(c);
            //figura.addForma(c);
        }

        telaPintura.desenhar(f);
        figura.addForma(f);
        formas.getItems().add(f);

        // Não consegui colocar este comando em outro lugar... :(
        formas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void limpar(){

        GraphicsContext context = telaPintura.getGraphicsContext2D();
        context.clearRect(0,0, telaPintura.getWidth(), telaPintura.getHeight());

        nQuadrilateros = 0; nTriangulos = 0; nCirculos = 0;
    }

    @Override
    public void handle(ActionEvent event){
        instrucoes.setText("Clique e arraste no Canvas para começar a desenhar.");

        if (event.getSource() == ferramentaQuadrilatero){
            quadrilateroSelecionado = true;
            trianguloSelecionado = false;
            circuloSelecionado = false;

        } else if (event.getSource() == ferramentaTriangulo){
            quadrilateroSelecionado = false;
            trianguloSelecionado =  true;
            circuloSelecionado = false;

        } else if (event.getSource() == ferramentaCirculo) {
            quadrilateroSelecionado = false;
            trianguloSelecionado = false;
            circuloSelecionado =  true;

        }
    }
}