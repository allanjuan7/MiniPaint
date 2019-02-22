package popup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Entrada {

    private String nome;
    private int valorPadrao;
    private TextField campo;
    private HBox hBox;

    public Entrada(String nome, int valorPadrao) {
        this.nome = nome;
        this.valorPadrao = valorPadrao;

        Label lbl = new Label(nome);
        campo = new TextField();
        campo.setText(String.valueOf(valorPadrao));

        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(lbl, campo);
    }

    public int getValorCampo(){
        return Integer.parseInt(campo.getText());
    }


    public HBox getHBox(){
        return hBox;
    }
}