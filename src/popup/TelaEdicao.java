package popup;

import formas.Forma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

        btnCancelar.setOnAction(e -> {
            stage.close();
        });

        vBox.getChildren().addAll(entradaPosX.getHBox(), entradaPosY.getHBox());
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Entrada getEntradaPosX() {
        return entradaPosX;
    }

    public void setEntradaPosX(Entrada entradaPosX) {
        this.entradaPosX = entradaPosX;
    }

    public Entrada getEntradaPosY() {
        return entradaPosY;
    }

    public void setEntradaPosY(Entrada entradaPosY) {
        this.entradaPosY = entradaPosY;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public Button getBtnConfirmar() {
        return btnConfirmar;
    }

    public void setBtnConfirmar(Button btnConfirmar) {
        this.btnConfirmar = btnConfirmar;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void mostrar(){
        Scene scene = new Scene(vBox, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Edição - " + forma.toString());
        stage.show();
    }
}
