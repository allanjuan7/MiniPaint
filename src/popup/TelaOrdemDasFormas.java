package popup;

import controle.Controle;
import excecoes.MovimentoInvalidoException;
import formas.Forma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TelaOrdemDasFormas {

    private ArrayList<Forma> formas;
    private VBox vBox;

    public TelaOrdemDasFormas(ArrayList<Forma> formas){
        this.formas = formas;

        vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.TOP_CENTER);

        Label label = new Label("Selecione uma forma:");

        ChoiceBox<Forma> choiceBox = new ChoiceBox<>();
        for (Forma f : formas){
            choiceBox.getItems().add(f);
        }

        choiceBox.setValue(formas.get(0));

        Button btnSubir = new Button("Subir");
        Button btnDescer = new Button("Descer");

        HBox hBoxInferior = new HBox(10);

        btnSubir.setOnAction(e -> {
            try{
                int index = choiceBox.getSelectionModel().getSelectedIndex();
                if (index <= 0)
                    throw new MovimentoInvalidoException();


                Controle.controle.permutarFormas(index, index-1, choiceBox);

            } catch (MovimentoInvalidoException ex){
                ex.mostrarPopUpDeErro();
            }

        });

        btnDescer.setOnAction(e ->{
            try{
                int index = choiceBox.getSelectionModel().getSelectedIndex();

                if (index >= formas.size()-1)
                    throw new MovimentoInvalidoException();

                Controle.controle.permutarFormas(index, index+1, choiceBox);

            } catch(MovimentoInvalidoException ex){
                ex.mostrarPopUpDeErro();
            }

        });

        hBoxInferior.setAlignment(Pos.CENTER);
        hBoxInferior.getChildren().addAll(btnSubir, btnDescer);
        vBox.getChildren().addAll(label, choiceBox, hBoxInferior);
    }

    public void mostrar(){
        Stage stage = new Stage();
        stage.setResizable(false);
        Scene scene = new Scene(vBox, 400, 155);
        stage.getIcons().add(new Image("recursos/icone_ordem_formas.png"));

        /* Esse comando faz com que o foco fique travado na tela de edição, impedindo que o
         * usuário utilize o programa até ter terminado de editar a forma*/
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.setTitle("Mudar ordem das formas");
        stage.show();

    }


}
