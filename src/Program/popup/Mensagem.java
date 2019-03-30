package Program.popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mensagem {

    private String mensagem;
    private double alturaDaJanela;
    private double larguraDaJanela;
    private String titulo;

    public Mensagem(String mensagem, String titulo){
        this.mensagem = mensagem;
        this.titulo = titulo;
        alturaDaJanela = 200;
        larguraDaJanela = 500;
    }

    public Mensagem(String mensagem, String titulo, double alturaDaJanela, double larguraDaJanela){
        this.mensagem = mensagem;
        this.titulo = titulo;
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
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }
}
