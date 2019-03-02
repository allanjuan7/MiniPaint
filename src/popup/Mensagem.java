package popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mensagem {

    private String mensagem;
    private double alturaDaJanela;
    private double larguraDaJanela;

    public Mensagem(String mensagem){
        this.mensagem = mensagem;
        alturaDaJanela = 200;
        larguraDaJanela = 500;
    }

    public Mensagem(String mensagem, double alturaDaJanela, double larguraDaJanela){
        this.mensagem = mensagem;
        this.alturaDaJanela = alturaDaJanela;
        this.larguraDaJanela = larguraDaJanela;
    }

    public void mostrar(){
        Stage stage = new Stage();

        Label label = new Label(mensagem);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label);

        Scene scene = new Scene(vBox, larguraDaJanela, alturaDaJanela);
        stage.setTitle("Entrada Inválida");
        stage.setScene(scene);
        stage.show();
    }
}
