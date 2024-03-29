package popup;

import formas.Forma;
import formas.Quadrilatero;
import formas.Triangulo;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class TelaEdicaoPoligono extends TelaEdicao {

    private Entrada entradaBase, entradaAltura;

    public TelaEdicaoPoligono(Forma forma){

        super(forma);

        if (forma instanceof Quadrilatero){
            Quadrilatero formaCastada = (Quadrilatero) forma;
            entradaBase = new Entrada("Base", formaCastada.getBase());
            entradaAltura = new Entrada("Altura", formaCastada.getAltura());
        }

        if (forma instanceof Triangulo){
            Triangulo formaCastada = (Triangulo) forma;
            entradaBase = new Entrada("Base", formaCastada.getBase());
            entradaAltura = new Entrada("Altura", formaCastada.getAltura());
        }

        vBox.getChildren().addAll(entradaBase.getHBox(), entradaAltura.getHBox(), colorPicker);

        /* Como o label altura é maior que os demais, é preciso esse comando para que a TextBox
        * fique alinhada com as outras.*/
        entradaAltura.getHBox().setSpacing(3);

        HBox hBoxInferior = new HBox(10);
        hBoxInferior.getChildren().addAll(btnConfirmar, btnCancelar);
        hBoxInferior.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBoxInferior);
    }

    public Entrada getEntradaBase() {
        return entradaBase;
    }

    public Entrada getEntradaAltura() {
        return entradaAltura;
    }
}
