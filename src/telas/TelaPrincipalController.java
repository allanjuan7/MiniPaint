package telas;

import formas.Circulo;
import formas.Quadrilatero;
import formas.Triangulo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class TelaPrincipalController implements EventHandler<ActionEvent> {

    private boolean quadrilateroSelecionado, circuloSelecionado, trianguloSelecionado;

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

        /*GraphicsContext contexto = telaPintura.getGraphicsContext2D();
        contexto.setStroke(selecCor.getValue());*/

        xInicial = Math.min(xFinal, xInicial); //Faz a função do if
        yInicial = Math.min(yFinal, yInicial); //Faz a função do if

        if (quadrilateroSelecionado) {
            //contexto.strokeRect(xInicial, yInicial, largura, altura);
            Quadrilatero q = new Quadrilatero(xInicial, yInicial, selecCor.getValue(), largura, altura);
            telaPintura.desenhar(q);
        } else if (trianguloSelecionado) {
            Triangulo t = new Triangulo(xInicial, yInicial, selecCor.getValue(), largura, altura);
            telaPintura.desenhar(t);
        } else if (circuloSelecionado) {
            double raio = sqrt((largura * largura) + (altura * altura) );
            Circulo c = new Circulo(xInicial, yInicial, selecCor.getValue(), raio);
            telaPintura.desenhar(c);
        }
    }

    /**
     * Metodo usado pelo botão btnLimpar para limpar toda a telaPintura.
     */
    public void limpar(){

        GraphicsContext context = telaPintura.getGraphicsContext2D();
        context.clearRect(0,0, telaPintura.getWidth(), telaPintura.getHeight());

    }

    @Override
    public void handle(ActionEvent event){
        instrucoes.setText("Clique no Canvas para determinar a posição da figura.");

        if (event.getSource() == ferramentaCirculo){
            circuloSelecionado = true;
            quadrilateroSelecionado = false;
            trianguloSelecionado = false;
        } else if (event.getSource() == ferramentaQuadrilatero){
            quadrilateroSelecionado = true;
            circuloSelecionado = false;
            trianguloSelecionado = false;
        } else if (event.getSource() == ferramentaTriangulo) {
            trianguloSelecionado = true;
            quadrilateroSelecionado = false;
            circuloSelecionado = false;
        }
    }
}