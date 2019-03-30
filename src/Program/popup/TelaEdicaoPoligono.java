package Program.popup;

import Program.formas.Forma;
import Program.formas.Quadrilatero;
import Program.formas.Triangulo;
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
