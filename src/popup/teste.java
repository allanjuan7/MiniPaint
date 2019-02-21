package popup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Esta classe foi criada apenas para testar o funcionamento dos decorators e deverá ser apagada até o fim do projeto.

public class teste extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Entrada("Altura", 200).getHBox());
        vbox.getChildren().add(new Entrada("Largura", 150).getHBox());

        Stage window = primaryStage;
        Scene scene = new Scene(vbox, 400, 400);
        window.setScene(scene);
        window.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
