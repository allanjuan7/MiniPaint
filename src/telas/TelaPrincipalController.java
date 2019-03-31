package telas;

import controle.Controle;
import excecoes.ArrayVazioException;
import formas.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import popup.Mensagem;
import popup.TelaOrdemDasFormas;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class TelaPrincipalController implements EventHandler<ActionEvent> {

    private ObservableList<String> modoDeDesenhoList = FXCollections.observableArrayList("Preencher", "Contornar");

    private int nQuadrilateros, nTriangulos, nCirculos;

    private String figuraSelecionada;

    @FXML
    private ListView<Forma> formasListView;

    @FXML
    private Button ferramentaQuadrilatero, ferramentaCirculo, ferramentaTriangulo;

    @FXML
    private ColorPicker selecCor;

    @FXML
    private ChoiceBox<String> modoDeDesenhoBox;

    @FXML
    private TelaPintura telaPintura;

    @FXML
    public void initialize(){

        Circle circuloDoBotao = new Circle();
        circuloDoBotao.setFill(Color.TRANSPARENT); circuloDoBotao.setStroke(Color.BLACK);
        circuloDoBotao.setRadius(10);

        ferramentaCirculo.setGraphic(circuloDoBotao);

        Rectangle retanguloDoBotao = new Rectangle();
        retanguloDoBotao.setHeight(20); retanguloDoBotao.setWidth(20);
        retanguloDoBotao.setFill(Color.TRANSPARENT); retanguloDoBotao.setStroke(Color.BLACK);

        ferramentaQuadrilatero.setGraphic(retanguloDoBotao);

        Polygon trianguloDoBotao = new Polygon();
        trianguloDoBotao.getPoints().addAll(new Double[] {
                0.00, 20.00,
                0.00, 0.00,
                20.00, 20.00
        });
        trianguloDoBotao.setFill(Color.TRANSPARENT);
        trianguloDoBotao.setStroke(Color.BLACK);

        ferramentaTriangulo.setGraphic(trianguloDoBotao);

        modoDeDesenhoBox.setItems(modoDeDesenhoList);
        modoDeDesenhoBox.setValue("Preencher");

        formasListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Controle.controle.setListView(formasListView);
        Controle.controle.setTelaPintura(telaPintura);

        Controle.controle.setCaminhoDoArquivo("arquivo.ser");
    }

    /**
     * Guarda a posição X e Y onde a tela de pintura foi clicada.
     *
     * @param event
     */
    public void onMousePressed(MouseEvent event) {
        telaPintura.setxInicial(event.getX());
        telaPintura.setyInicial(event.getY());
    }

    /**
     * Com as posições X e Y iniciais e finais e a informação sobre a figuraSelecionada,
     * instância a figura, guarda em um array e manda a telaPintura desenhar.
     *
     * @see TelaPintura
     * @param event
     * @throws Exception exceção ainda não tratada (ValoresInvalidosException)
     */

    public void onMouseReleased(MouseEvent event) throws Exception{

        double xFinal, yFinal, xInicial, yInicial, deltaX, deltaY, altura, largura;

        xFinal = event.getX();
        yFinal = event.getY();

        xInicial = telaPintura.getxInicial();
        yInicial = telaPintura.getyInicial();

        deltaX = xInicial - xFinal;
        deltaY = yInicial - yFinal;

        largura = abs(deltaX);
        altura = abs(deltaY);

        Forma novaForma = null;

        if (figuraSelecionada == "Quadrilatero") {
            xInicial = Math.min(xFinal, xInicial);
            yInicial = Math.min(yFinal, yInicial);
            novaForma = new Quadrilatero(xInicial, yInicial, selecCor.getValue(), largura, altura, ++nQuadrilateros);

        } else if (figuraSelecionada == "Triangulo") {
            String modeloDoTriangulo = null;
            if (xInicial < xFinal && yInicial < yFinal)
                modeloDoTriangulo = "A";
            if (xInicial < xFinal && yInicial > yFinal)
                modeloDoTriangulo = "B";
            if (xInicial > xFinal && yInicial > yFinal)
                modeloDoTriangulo = "C";
            if (modeloDoTriangulo == null)
                modeloDoTriangulo = "D";

            novaForma = new Triangulo(xInicial, yInicial, selecCor.getValue(), largura, altura, ++nTriangulos, modeloDoTriangulo);

        } else if (figuraSelecionada == "Circulo") {
            xInicial = Math.min(xFinal, xInicial);
            yInicial = Math.min(yFinal, yInicial);
            double raio = sqrt((largura * largura) + (altura * altura));
            novaForma = new Circulo(xInicial, yInicial, selecCor.getValue(), raio, ++nCirculos);
        }

        if (novaForma != null){
            telaPintura.desenhar(novaForma, modoDeDesenhoBox.getValue());

            Controle.controle.addForma(novaForma);
            formasListView.getItems().add(novaForma);
        }
    }

    /**
     * Método responsável pelos botões de seleção de ferramenta.
     * Recebe o evento do botão clicado e seta a figuraSelecionada.
     *
     * @param event
     */
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

    /**
     * Método responsável pelo botão de limpar a tela.
     * Chama a função reiniciarFigura do Controle.
     *
     * @see Controle
     */
    public void btnLimparClicado(){
        nQuadrilateros = 0; nTriangulos = 0; nCirculos = 0;
        Controle.controle.reiniciarFigura(formasListView, telaPintura);
    }

    /**
     * Método responsável pelo botão de deletar a forma selecionada
     * no menu do listView.
     *
     * @see Controle
     */
    public void btnDeletarClicado(){
        Controle.controle.deletarForma();
    }

    /**
     * Método responsável pelo botão de editar a forma selecionada
     * no menu do listView
     *
     * @see Controle
     */
    public void btnEditarClicado(){
        Controle.controle.editarForma();
    }

    public void mudarOrdemDasFormasClicado(){
        try{
            if (formasListView.getItems().size() == 0)
                throw new ArrayVazioException();

            Controle.controle.mudarOrdemDasFormas();

        } catch (ArrayVazioException ex){
            ex.mostrarPopUpDeErro();
        }

    }

    /**
     * Método responsável pelo botão de abrir arquivo.
     *
     * @see Controle
     */

    public void menuItemAbrirClicado(){
        /*Do jeito que esta implementado atualmente adiciona a figura que esta sendo carregada
        a figura atual, podendo, e causando duplicatas*/
        String caminho = Controle.controle.lancarFileChooser("abrir");
        Controle.controle.abrirArquivo(caminho);
    }

    /**
     * Método responsável pelo botão de salvar.
     *
     * @see Controle
     */

    public void menuItemSalvarClicado(){
        //A exceção que estava aqui já está sendo tratada no metodo serializarFigura
        Controle.controle.serializarFigura();

        Mensagem mensagem = new Mensagem("Figura salva com sucesso!", "Salvar");

        mensagem.mostrar();
    }

    /**
     * Método responsável pelo botão de salvar como.
     *
     * @see Controle
     * @throws Exception (A excecao tem que ser tratada aqui, não tem mais pra onde jogar)
     */
    public void menuItemSalvarComoClicado() throws Exception{

        String caminho = Controle.controle.lancarFileChooser("salvar");
        //O caminho pode ser null? O file chooser não me deixa não escolher um arquivo ao clicar open
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