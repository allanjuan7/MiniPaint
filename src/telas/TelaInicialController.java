package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private Button novoProjeto;

    @FXML
    public void novoProjetoClicado() throws IOException {

        //Fechando a Tela Inicial
        Stage telaInicial = (Stage) novoProjeto.getScene().getWindow();
        telaInicial.close();

        //Abrindo a Tela Principal
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        Stage telaPrincipal = new Stage();
        telaPrincipal.setTitle("MiniPaint");
        telaPrincipal.setScene(new Scene(root, 800, 600));
        telaPrincipal.show();
    }
}
