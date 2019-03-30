package Program.popup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Entrada {

    private String nome;
    private double valorPadrao;
    private TextField campo;
    private HBox hBox;

    public Entrada(String nome, double valorPadrao) {
        this.nome = nome;
        this.valorPadrao = valorPadrao;

        Label lbl = new Label(nome);
        campo = new TextField();
        campo.setText(String.valueOf(valorPadrao));


        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(lbl, campo);
    }

    public double getValorCampo(){
        return Double.parseDouble(campo.getText());
    }


    public HBox getHBox(){
        return hBox;
    }
}
