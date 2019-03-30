package controle;

import formas.Forma;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import telas.TelaPintura;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controle {

    /* Padrão de Projeto SINGLETON - Asseguro que haverá sempre
    *uma e somente uma instância de Controle, acessível em qualquer lugar */
    public static Controle controle = new Controle();

    private TelaPintura telaPintura;
    private ListView<Forma> listView;
    private ArrayList<Forma> formas;
    private String caminhoDoArquivo;

    public Controle() {
        formas = new ArrayList<>();
    }

    public void setTelaPintura(TelaPintura telaPintura) {
        this.telaPintura = telaPintura;
    }

    public void setListView(ListView<Forma> listView) {
        this.listView = listView;
    }

    public void setCaminhoDoArquivo(String caminhoDoArquivo) {
        /*Checar se o final da string tem .ser ou não
        *  no meu notebook salvei e não reconheceu depois */
        this.caminhoDoArquivo = caminhoDoArquivo;
    }

    public void addForma(Forma f) {
        formas.add(f);
    }

    public List<Forma> getFormas() {
        return formas;
    }

    public void editarForma() {
        Forma formaSelecionada = listView.getSelectionModel().getSelectedItem();
        formaSelecionada.editar(telaPintura, listView);
    }

    public void deletarForma() {
        int index = listView.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            controle.getFormas().remove(index);

            controle.apagarQuadro();
            controle.atualizarListViewERedesenhar(telaPintura, listView);
        }
    }

    public void limpar() {
        formas.clear();
    }

    public void apagarQuadro() {
        GraphicsContext contextoGrafico = telaPintura.getGraphicsContext2D();
        contextoGrafico.clearRect(0, 0, telaPintura.getWidth(), telaPintura.getHeight());
    }

    public void reiniciarFigura(ListView listView, TelaPintura telaPintura) {
        controle.limpar();
        listView.getItems().clear();
        controle.apagarQuadro();
    }

    public void atualizarListViewERedesenhar(TelaPintura telaPintura, ListView listView) {

        listView.getItems().clear();

        for (Forma f : formas) {
            listView.getItems().add(f);
            telaPintura.desenhar(f, f.getModoDeDesenho());
        }
    }

    public void serializarFigura(){
        try{

            for (Forma f : formas){
                f.setRgbString(f.getCor().toString());
            }

            FileOutputStream fileStream = new FileOutputStream(caminhoDoArquivo);

            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(formas); objectStream.close();

        }  catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Forma> desserializarFigura(String caminho){
        try {

            FileInputStream fileStream = new FileInputStream(caminho);

            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            Object objetoCarregado = objectStream.readObject();

            ArrayList<Forma> figuraCarregada = (ArrayList<Forma>) objetoCarregado;

            Color cor;

            for (Forma f : figuraCarregada){
                cor = Color.web(f.getRgbString());
                f.setCor(cor);
            }

            return figuraCarregada;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void incorporarFiguraCarregada(ArrayList<Forma> figuraCarregada){

        for (Forma f : figuraCarregada){
            formas.add(f);
        }
    }

    public String lancarFileChooser(String tarefa){
        /*Checar o que acontece ao cancelar a selecao
          Dando NullPointerException */
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("MiniPaint");
        Stage stage = new Stage();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos SER (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        if (tarefa.equals("abrir"))
            return fileChooser.showOpenDialog(stage).getAbsoluteFile().getAbsolutePath();

        return fileChooser.showSaveDialog(stage).getAbsolutePath();
    }

    public void abrirArquivo(String caminho){

        ArrayList<Forma> figuraDesserializada = controle.desserializarFigura(caminho);
        /* Controle.controle.reiniciarFigura(); para jogar a figura atual fora
        e eliminar possíveis duplicatas */
        Controle.controle.incorporarFiguraCarregada(figuraDesserializada);

        Controle.controle.apagarQuadro();

        Controle.controle.atualizarListViewERedesenhar(telaPintura, listView);
    }

}
