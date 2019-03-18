package telas;

import entradaesaida.Arquivo;
import excecoes.ValorDeEntradaNegativoException;
import formas.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import popup.Mensagem;
import popup.TelaEdicaoCirculo;
import popup.TelaEdicaoPoligono;

import java.io.File;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class TelaPrincipalController implements EventHandler<ActionEvent> {

    ObservableList<String> modoDeDesenhoList = FXCollections.observableArrayList("Preencher", "Contornar");

    private int nQuadrilateros, nTriangulos, nCirculos;

    private Figura figura = new Figura();

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

            figura.addForma(novaForma);
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

    // Apenas apaga o Canvas. É chamado em outros métodos.
    public void apagarQuadro(){
        GraphicsContext contextoGrafico = telaPintura.getGraphicsContext2D();
        contextoGrafico.clearRect(0,0, telaPintura.getWidth(), telaPintura.getHeight());
    }

    // Apaga o Canvas e reinicia as variáveis, é chamado pelo botão LIMPAR
    public void apagarTudo(){
        nQuadrilateros = 0; nTriangulos = 0; nCirculos = 0;
        figura.limpar(); formasListView.getItems().clear();
        apagarQuadro();
    }

    public void atualizarListViewERedesenhar(){

        formasListView.getItems().clear();

        for (Forma f : figura.getFormas()){
            formasListView.getItems().add(f);
            telaPintura.desenhar(f, modoDeDesenhoBox.getValue());
        }
    }

    public void btnDeletarClicado(){

        Integer index = formasListView.getSelectionModel().getSelectedIndex();

        if (index >= 0){
            figura.deletarFormaEm(index); formasListView.getItems().clear();

            apagarQuadro();
            atualizarListViewERedesenhar();
        }
    }

    public void btnEditarClicado(){

        Forma formaSelecionada = formasListView.getSelectionModel().getSelectedItem();

        if (formaSelecionada != null){

            if (formaSelecionada instanceof Circulo){

                Circulo circulo = (Circulo) formaSelecionada;

                TelaEdicaoCirculo telaEdicao = new TelaEdicaoCirculo(circulo);

                telaEdicao.getBtnConfirmar().setOnAction( e -> {
                    try {

                        circulo.setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                        circulo.setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                        circulo.setRaio(telaEdicao.getEntradaRaio().getValorCampo());
                        circulo.setCor(telaEdicao.getColorPicker().getValue());

                        telaEdicao.getStage().close();
                        apagarQuadro();
                        atualizarListViewERedesenhar();

                    } catch (ValorDeEntradaNegativoException ex){

                        Mensagem mensagemDeErro = new Mensagem("Valor de entrada inválido. Insira um valor maior que 0.", "Valores invalidos", 100, 500);
                        mensagemDeErro.mostrar();
                    }
                });

                telaEdicao.mostrar();
            }

            if (formaSelecionada instanceof Quadrilatero){

                Quadrilatero quadrilatero = (Quadrilatero) formaSelecionada;

                TelaEdicaoPoligono telaEdicao = new TelaEdicaoPoligono(formaSelecionada);

                telaEdicao.getBtnConfirmar().setOnAction(e -> {

                    try{

                        quadrilatero.setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                        quadrilatero.setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                        quadrilatero.setBase(telaEdicao.getEntradaAltura().getValorCampo());
                        quadrilatero.setAltura(telaEdicao.getEntradaBase().getValorCampo());
                        quadrilatero.setCor(telaEdicao.getColorPicker().getValue());

                        telaEdicao.getStage().close(); apagarQuadro();
                        atualizarListViewERedesenhar();

                    } catch(ValorDeEntradaNegativoException ex){

                        Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", "Valores invalidos", 100, 500);
                        mensagemDeErro.mostrar();
                    }
                });

                telaEdicao.mostrar();
            }

            if (formaSelecionada instanceof Triangulo){

                Triangulo triangulo = (Triangulo) formaSelecionada;

                TelaEdicaoPoligono telaEdicao = new TelaEdicaoPoligono(formaSelecionada);

                telaEdicao.getBtnConfirmar().setOnAction(e -> {
                    try {

                        triangulo.setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                        triangulo.setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                        triangulo.setBase(telaEdicao.getEntradaBase().getValorCampo());
                        triangulo.setAltura(telaEdicao.getEntradaAltura().getValorCampo());
                        triangulo.setCor(telaEdicao.getColorPicker().getValue());

                        telaEdicao.getStage().close(); apagarQuadro();
                        atualizarListViewERedesenhar();
                    } catch(ValorDeEntradaNegativoException ex){

                        Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", "Valores invalidos", 100, 500);
                        mensagemDeErro.mostrar();
                    }

                });

                telaEdicao.mostrar();
            }
        }
    }

    public void menuItemAbrirClicado(){

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Selecione uma figura");

        Stage stage = new Stage();

        File figuraSerializada = fileChooser.showOpenDialog(stage).getAbsoluteFile();

        Figura figuraDesserializada = Arquivo.carregarFigura(figuraSerializada.getAbsolutePath());

        figura.incorporarFiguraCarregada(figuraDesserializada);

        apagarQuadro();

        atualizarListViewERedesenhar();
    }

    public void menuItemSalvarClicado() throws Exception{

        Arquivo.salvarFigura(figura, "teste.ser", "caminho");

        Mensagem mensagem = new Mensagem("Figura salva com sucesso!", "Salvar");

        mensagem.mostrar();
    }

    public void menuItemSalvarComoClicado() throws Exception{

        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos SER (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        if (file == null){
            Mensagem mensagem = new Mensagem("null", "Salvar como");
            mensagem.mostrar();
        }
        if (file != null) {
            Arquivo.salvarFigura(figura, file.getName(), file.getAbsolutePath());
            Mensagem mensagem = new Mensagem("Salvei", "Salvar como");
            mensagem.mostrar();
        }
    }
}