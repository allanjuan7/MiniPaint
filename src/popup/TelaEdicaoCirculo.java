package popup;

import formas.Circulo;
import formas.Forma;

public class TelaEdicaoCirculo extends TelaEdicao {

    private Entrada entradaRaio;

    public TelaEdicaoCirculo(Forma forma){
        super(forma);

        Circulo circulo = (Circulo) forma;

        entradaRaio = new Entrada("Raio", circulo.getRaio());
        vBox.getChildren().addAll(entradaRaio.getHBox());

        btnConfirmar.setOnAction(e -> {
            try{
                forma.setxInicial(entradaPosX.getValorCampo());


            }
        });
    }
}
