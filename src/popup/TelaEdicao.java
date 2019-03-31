package popup;

import formas.Forma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaEdicao {

    protected VBox vBox;
    protected Entrada entradaPosX, entradaPosY;
    protected ColorPicker colorPicker;
    protected Button btnConfirmar, btnCancelar;
    protected Stage stage;
    private Forma forma;

    public TelaEdicao (Forma forma){

        this.forma = forma;

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
        stage.setResizable(false);
        stage.getIcons().add(new Image("recursos/icone_edicao.png"));

        /* Esse comando faz com que o foco fique travado na tela de edição, impedindo que o
        * usuário utilize o programa até ter terminado de editar a forma*/
        stage.initModality(Modality.APPLICATION_MODAL);

        btnCancelar.setOnAction(e -> {
            stage.close();
        });

        vBox.getChildren().addAll(entradaPosX.getHBox(), entradaPosY.getHBox());
    }

    public Entrada getEntradaPosX() {
        return entradaPosX;
    }

    public Entrada getEntradaPosY() {
        return entradaPosY;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public Button getBtnConfirmar() {
        return btnConfirmar;
    }

    public Stage getStage() {
        return stage;
    }

    public void mostrar(){
        Scene scene = new Scene(vBox, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Edição - " + forma.toString());
        stage.show();
    }
}
