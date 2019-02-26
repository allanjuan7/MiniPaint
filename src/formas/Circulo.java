package formas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import popup.Entrada;

public class Circulo extends Forma{

    private double raio;

    // No caso do círculo, as coordenadas iniciais representam o seu centro.
    public Circulo(double xInicial, double yInicial, Color cor, double raio, int id){
        super(xInicial, yInicial, cor, id);
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    // A FAZER
    public void editar(){
        /* O cru do método já foi feito, porém ainda é necessário formatar a janela
        * popup e configurar o tratamento de entradas.
        *
        * O método editar está servindo apenas para alterar os valores da nossa Forma.
        * Com a nova implementação, a responsabilidade de desenhá-la será passada para a TelaPintura.*/
        VBox vbox = new VBox(10);

        // Adiciona as entradas, que são um label e uma textField que tem o valor padrão passado no construtor
        Entrada entradaPosX = new Entrada("PosX", xInicial);
        vbox.getChildren().add(entradaPosX.getHBox());

        Entrada entradaPosY = new Entrada("PosY", yInicial);
        vbox.getChildren().add(entradaPosY.getHBox());

        Entrada entradaRaio = new Entrada("Raio", raio);
        vbox.getChildren().add(entradaRaio.getHBox());

        ColorPicker colorPicker = new ColorPicker();
        vbox.getChildren().add(colorPicker);

        Button btnConfirmar = new Button("Confirmar");
        btnConfirmar.setOnAction(e -> {
            xInicial = entradaPosX.getValorCampo();
            yInicial = entradaPosY.getValorCampo();
            raio = entradaRaio.getValorCampo();
            cor = colorPicker.getValue();
        });

        Stage stage = new Stage();

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> {
            stage.close();
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnConfirmar, btnCancelar);

        vbox.getChildren().add(hbox);

        stage.setScene(new Scene(vbox, 800, 600));
        stage.show();
    }

    public String toString(){
        return "Círculo " + id;
    }
}
