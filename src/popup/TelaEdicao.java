package popup;

import formas.Forma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEdicao {

    protected VBox vBox;
    protected Entrada entradaPosX, entradaPosY;
    protected ColorPicker colorPicker;
    protected Button btnConfirmar, btnCancelar;
    protected Stage stage;

    public TelaEdicao (Forma forma){

        vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.TOP_CENTER);

        entradaPosX = new Entrada("PosX", forma.getxInicial());
        entradaPosY = new Entrada("PosY", forma.getyInicial());

        colorPicker = new ColorPicker();
        colorPicker.setValue(forma.getCor());
        colorPicker.setMinWidth(225);

        btnConfirmar = new Button("Confirmar");
        btnCancelar = new Button("Cancelar");

        stage = new Stage();

        btnCancelar.setOnAction(e -> {
            stage.close();
        });

        vBox.getChildren().addAll(entradaPosX.getHBox(), entradaPosY.getHBox());
    }
}
