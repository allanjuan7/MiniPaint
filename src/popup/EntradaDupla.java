package popup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/* Nesta classe teremos dois Labels e dois TextFields numa mesma horizontal.
 * Essa implementação será utilizada para a criação dos campos "X Inicial" e "Y Inicial"*/

public class EntradaDupla {
    private String nome1, nome2;
    private int valorPadrao1, valorPadrao2;
    private TextField campo1, campo2;
    private HBox hBox;

    public EntradaDupla(String nome1, String nome2, int valorPadrao1, int valorPadrao2,
                        TextField campo1, TextField campo2) {
        this.nome1 = nome1;
        this.nome2 = nome2;

        this.valorPadrao1 = valorPadrao1;
        this.valorPadrao2 = valorPadrao2;

        this.campo1 = campo1;
        this.campo2 = campo2;

        Label lbl1 = new Label(nome1);
        Label lbl2 = new Label(nome2);

        campo1 = new TextField();
        campo1.setText(String.valueOf(valorPadrao1));
        campo2 = new TextField();
        campo2.setText(String.valueOf(valorPadrao2));

        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(lbl1, campo1, lbl2, campo2);
    }

    public int getValorCampo1(){
        return Integer.parseInt(campo1.getText());
    }

    public int getValorCampo2(){
        return Integer.parseInt(campo2.getText());
    }

    public HBox getHBox(){
        return hBox;
    }
}
