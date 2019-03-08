package telas;

import entradaesaida.Arquivo;
import excecoes.ValorDeEntradaNegativoException;
import formas.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import popup.Entrada;
import popup.Mensagem;

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
    private Label instrucoes;

    @FXML
    private Button ferramentaTriangulo;

    @FXML
    private Button ferramentaQuadrilatero;

    @FXML
    private Button ferramentaCirculo;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private ColorPicker selecCor;

    @FXML
    private TelaPintura telaPintura;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuArquivo;

    @FXML
    private MenuItem menuItemSalvar;

    @FXML
    private MenuItem menuItemAbrir;

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

    public void onMouseReleased(MouseEvent event){

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
        figura.deletarFormaEm(index); formasListView.getItems().clear();

        apagarQuadro();
        atualizarListViewERedesenhar();
    }

    // Devemos passar esse método para uma outra classe, para fins de modularização? Se sim, qual?
    public static void validarEntradas(Entrada entradas[]) throws ValorDeEntradaNegativoException{
        for (Entrada e : entradas){
            if (e.getValorCampo() < 0){
                throw new ValorDeEntradaNegativoException();
            }
        }

    }

    public void btnEditarClicado(){

        Forma formaSelecionada = formasListView.getSelectionModel().getSelectedItem();

        if (formaSelecionada != null){

            VBox vBox = new VBox(10);
            vBox.setPadding(new Insets(10, 10, 10, 10));
            vBox.setAlignment(Pos.TOP_CENTER);

            Stage stage = new Stage();

            Entrada entradaPosX = new Entrada("PosX", formaSelecionada.getxInicial());
            Entrada entradaPosY = new Entrada("PosY", formaSelecionada.getyInicial());

            ColorPicker colorPicker = new ColorPicker();
            colorPicker.setValue(formaSelecionada.getCor());
            colorPicker.setMinWidth(225);

            Button btnConfirmar = new Button("Confirmar");
            Button btnCancelar = new Button("Cancelar");

            btnCancelar.setOnAction(e -> {
                stage.close();
            });

            vBox.getChildren().addAll(entradaPosX.getHBox(), entradaPosY.getHBox());

            if (formaSelecionada instanceof Circulo){

                Circulo circulo = (Circulo) formaSelecionada;
                Entrada entradaRaio = new Entrada("Raio", circulo.getRaio());

                vBox.getChildren().addAll(entradaRaio.getHBox(), colorPicker);

                btnConfirmar.setOnAction(e -> {
                    try {

                        validarEntradas(new Entrada[] {entradaPosX, entradaPosY, entradaRaio});

                        circulo.setxInicial(entradaPosX.getValorCampo());
                        circulo.setyInicial(entradaPosY.getValorCampo());
                        circulo.setRaio(entradaRaio.getValorCampo());
                        circulo.setCor(colorPicker.getValue());

                        stage.close();
                        apagarQuadro();
                        atualizarListViewERedesenhar();

                    } catch (ValorDeEntradaNegativoException ex){

                        Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", 100, 500);
                        mensagemDeErro.mostrar();
                    }
                });
            }

            if (formaSelecionada instanceof Quadrilatero){

                Quadrilatero quadrilatero = (Quadrilatero) formaSelecionada;

                Entrada entradaBase = new Entrada("Base", quadrilatero.getBase());
                Entrada entradaAltura = new Entrada("Altura", quadrilatero.getAltura());

                vBox.getChildren().addAll(entradaBase.getHBox(), entradaAltura.getHBox(), colorPicker);

                btnConfirmar.setOnAction(e -> {

                    try{

                        validarEntradas(new Entrada[] {entradaPosX, entradaPosY, entradaBase, entradaAltura});
                        quadrilatero.setxInicial(entradaPosX.getValorCampo());
                        quadrilatero.setyInicial(entradaPosY.getValorCampo());
                        quadrilatero.setBase(entradaBase.getValorCampo());
                        quadrilatero.setAltura(entradaAltura.getValorCampo());
                        quadrilatero.setCor(colorPicker.getValue());

                        stage.close(); apagarQuadro();
                        atualizarListViewERedesenhar();

                    } catch(ValorDeEntradaNegativoException ex){

                        Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", 100, 500);
                        mensagemDeErro.mostrar();
                    }
                });
            }

            if (formaSelecionada instanceof Triangulo){

                Triangulo triangulo = (Triangulo) formaSelecionada;

                Entrada entradaBase = new Entrada("Base", triangulo.getBase());
                Entrada entradaAltura = new Entrada("Altura", triangulo.getAltura());

                vBox.getChildren().addAll(entradaBase.getHBox(), entradaAltura.getHBox(), colorPicker);

                btnConfirmar.setOnAction(e -> {
                    try{
                        validarEntradas(new Entrada[] {entradaPosX, entradaPosY, entradaBase, entradaAltura});

                        triangulo.setxInicial(entradaPosX.getValorCampo());
                        triangulo.setyInicial(entradaPosY.getValorCampo());
                        triangulo.setBase(entradaBase.getValorCampo());
                        triangulo.setAltura(entradaAltura.getValorCampo());
                        triangulo.setCor(colorPicker.getValue());

                        stage.close(); apagarQuadro();
                        atualizarListViewERedesenhar();
                    } catch(ValorDeEntradaNegativoException ex){

                        Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", 100, 500);
                        mensagemDeErro.mostrar();
                    }

                });
            }

            HBox hBoxInferior = new HBox(10);
            hBoxInferior.getChildren().addAll(btnConfirmar, btnCancelar);
            hBoxInferior.setAlignment(Pos.CENTER);

            vBox.getChildren().addAll(hBoxInferior);

            Scene scene = new Scene(vBox, 400, 300);
            stage.setScene(scene);
            stage.show();
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

        Mensagem mensagem = new Mensagem("Figura salva com sucesso!");

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
            Mensagem mensagem = new Mensagem("null");
            mensagem.mostrar();
        }
        if (file != null) {
            Arquivo.salvarFigura(figura, file.getName(), file.getAbsolutePath());
            Mensagem mensagem = new Mensagem("Salvei");
            mensagem.mostrar();
        }
    }

    // Esse método está estranho pq os limites de TelaPintura não estão bem definidos. 
    public void menuItemCorDoPlanoDeFundoClicado(){

        // Aqui vamos criar uma nove janela onde o usuário vai selecionar a cor, mas por enquanto vamos usar nosso ColorPicker padrão

        GraphicsContext contexto = telaPintura.getGraphicsContext2D();
        contexto.setFill(selecCor.getValue());
        contexto.fillRect(0, 0, telaPintura.getWidth(), telaPintura.getHeight());
    }
}