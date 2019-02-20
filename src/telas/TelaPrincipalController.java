package telas;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import static java.lang.Math.abs;

public class TelaPrincipalController {

    @FXML
    private Label instrucoes;

    @FXML
    private Button ferramentaTriangulo;

    @FXML
    private Button ferramentaQuadrilatero;

    @FXML
    private Button ferramentaCirculo;

    @FXML
    private Button butLimpar;

    @FXML
    private ColorPicker selecCor;

    @FXML
    private TelaPintura teladepintura;


    /**
     * Metodo usado na TelaPintura para guardar a posição X e Y em que a tela foi clicada.
     *
     * @param event Evento do botão do mouse ser pressionado na tela.
     */
    public void onMousePressed(MouseEvent event) {
        teladepintura.setLastX(event.getX());
        teladepintura.setLastY(event.getY());
    }

    /**
     * Metodo usado na TelaPintura para desenhar na tela assim que o mouse para de ser arrastado.
     * Usa a cor selecionada pelo ColorPicker selecCor.
     *
     * @param event Evento do botão do mouse ser solto da tela.
     */

    public void onMouseReleased(MouseEvent event){

        double actualX, actualY, lastX, lastY, width, height;
        actualX = event.getX();
        actualY = event.getY();
        lastX = teladepintura.getLastX();
        lastY = teladepintura.getLastY();
        width = abs(lastX - actualX);
        height = abs(lastY - actualY);
        GraphicsContext context = teladepintura.getGraphicsContext2D();

        context.setStroke(selecCor.getValue());

        lastX = Math.min(actualX, lastX); //Faz a função do if
        lastY = Math.min(actualY,lastY); //Faz a função do if

        context.strokeRect(lastX, lastY, width, height);
    }

    /**
     * Metodo usado pelo botão butLimpar para limpar toda a teladepintura.
     */
    public void limpar(){

        GraphicsContext context = teladepintura.getGraphicsContext2D();
        context.clearRect(0,0,teladepintura.getWidth(), teladepintura.getHeight());

    }

    public void ferramentaClicada(){
        instrucoes.setText("Clique no Canvas para determinar a posição da figura.");
    }
}
