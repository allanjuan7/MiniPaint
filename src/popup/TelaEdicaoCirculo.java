package popup;

import formas.Circulo;
import formas.Forma;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class TelaEdicaoCirculo extends TelaEdicao {

    private Entrada entradaRaio;

    public Entrada getEntradaRaio() {
        return entradaRaio;
    }

    public TelaEdicaoCirculo(Forma forma){
        super(forma);

        Circulo circulo = (Circulo) forma;

        entradaRaio = new Entrada("Raio", circulo.getRaio());
        vBox.getChildren().addAll(entradaRaio.getHBox(), colorPicker);

        HBox hBoxInferior = new HBox(10);
        hBoxInferior.getChildren().addAll(btnConfirmar, btnCancelar);
        hBoxInferior.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBoxInferior);
        }
    }
