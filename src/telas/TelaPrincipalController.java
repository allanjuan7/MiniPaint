package telas;

import controle.Controle;
import formas.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import popup.Mensagem;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class TelaPrincipalController implements EventHandler<ActionEvent> {

    ObservableList<String> modoDeDesenhoList = FXCollections.observableArrayList("Preencher", "Contornar");

    private int nQuadrilateros, nTriangulos, nCirculos;

    private String figuraSelecionada;

    @FXML
    private ListView<Forma> formasListView;

    @FXML
    private Button ferramentaTriangulo;

    @FXML
    private Button ferramentaQuadrilatero;

    @FXML
    private Button ferramentaCirculo;

    @FXML
    private ColorPicker selecCor;

    @FXML
    private TelaPintura telaPintura;

    @FXML
    private ChoiceBox<String> modoDeDesenhoBox;

    @FXML
    public void initialize(){

        modoDeDesenhoBox.setItems(modoDeDesenhoList);
        modoDeDesenhoBox.setValue("Preencher");

        formasListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Controle.controle.setListView(formasListView);
        Controle.controle.setTelaPintura(telaPintura);

        Controle.controle.setCaminhoDoArquivo("arquivo.ser");
    }

    public void onMousePressed(MouseEvent event) {
        telaPintura.setxInicial(event.getX());
        telaPintura.setyInicial(event.getY());
    }

    public void onMouseReleased(MouseEvent event) throws Exception{

        double xFinal, yFinal, xInicial, yInicial, largura, altura;

        xFinal = event.getX();
        yFinal = event.getY();

        xInicial = telaPintura.getxInicial();
        yInicial = telaPintura.getyInicial();

        largura = abs(xInicial - xFinal);
        altura = abs(yInicial - yFinal);

        xInicial = Math.min(xFinal, xInicial);
        yInicial = Math.min(yFinal, yInicial);

        Forma novaForma = null;

        if (figuraSelecionada == "Quadrilatero") {
            novaForma = new Quadrilatero(xInicial, yInicial, selecCor.getValue(), largura, altura, ++nQuadrilateros);

        } else if (figuraSelecionada == "Triangulo") {
            novaForma = new Triangulo(xInicial, yInicial, selecCor.getValue(), largura, altura, ++nTriangulos);

        } else if (figuraSelecionada == "Circulo") {
            double raio = sqrt((largura * largura) + (altura * altura));
            novaForma = new Circulo(xInicial, yInicial, selecCor.getValue(), raio, ++nCirculos);
        }

        if (novaForma != null){
            telaPintura.desenhar(novaForma, modoDeDesenhoBox.getValue());

            Controle.controle.addForma(novaForma);
            formasListView.getItems().add(novaForma);
        }
    }

    @Override
    public void handle(ActionEvent event){

        if (event.getSource() == ferramentaQuadrilatero){
            figuraSelecionada = "Quadrilatero";

        } else if (event.getSource() == ferramentaTriangulo){
            figuraSelecionada = "Triangulo";

        } else if (event.getSource() == ferramentaCirculo) {
            figuraSelecionada = "Circulo";
        }
    }

    public void btnLimparClicado(){
        nQuadrilateros = 0; nTriangulos = 0; nCirculos = 0;
        Controle.controle.reiniciarFigura(formasListView, telaPintura);
    }

    public void btnDeletarClicado(){
        Controle.controle.deletarForma();
    }

    public void btnEditarClicado(){
        Controle.controle.editarForma();
    }

    public void menuItemAbrirClicado(){

        String caminho = Controle.controle.lancarFileChooser("abrir");
        Controle.controle.abrirArquivo(caminho);
    }

    public void menuItemSalvarClicado() throws Exception{

        Controle.controle.serializarFigura();

        Mensagem mensagem = new Mensagem("Figura salva com sucesso!", "Salvar");

        mensagem.mostrar();
    }

    public void menuItemSalvarComoClicado() throws Exception{

        String caminho = Controle.controle.lancarFileChooser("salvar");

        if (caminho == null){
            Mensagem mensagem = new Mensagem("Erro! Nenhum arquivo foi selecionado.", "Salvar como");
            mensagem.mostrar();
        }

        else {
            Controle.controle.setCaminhoDoArquivo(caminho);
            Controle.controle.serializarFigura();
            Mensagem mensagem = new Mensagem("Arquivo salvo com sucesso!", "Salvar como");
            mensagem.mostrar();
        }
    }
}