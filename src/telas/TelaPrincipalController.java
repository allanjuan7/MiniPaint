package telas;

import Excecoes.ValorDeEntradaNegativoException;
import formas.*;
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
import javafx.stage.Stage;
import popup.Entrada;
import popup.Mensagem;

import javax.swing.*;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class TelaPrincipalController implements EventHandler<ActionEvent> {

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
            double raio = sqrt((largura * largura) + (altura * altura) );
            novaForma = new Circulo(xInicial, yInicial, selecCor.getValue(), raio, ++nCirculos);
        }

        if (novaForma != null){
            telaPintura.desenhar(novaForma);
            figura.addForma(novaForma);
            formasListView.getItems().add(novaForma);
        }

        // Qual o lugar certo para por esse método?
        formasListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @Override
    public void handle(ActionEvent event){
        instrucoes.setText("Clique e arraste no Canvas para começar a desenhar.");

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
            telaPintura.desenhar(f);
        }
    }

    public void btnDeletarClicado(){

        Integer index = formasListView.getSelectionModel().getSelectedIndex();
        figura.deletarFormaEm(index); formasListView.getItems().clear();

        apagarQuadro();
        atualizarListViewERedesenhar();
    }

    public static void validarEntradas(Entrada entradas[]) throws ValorDeEntradaNegativoException{
        for (Entrada e : entradas){
            if (e.getValorCampo() < 0){
                throw new ValorDeEntradaNegativoException();
            }
        }

    }
    public void btnEditarClicado() throws ValorDeEntradaNegativoException {

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
}