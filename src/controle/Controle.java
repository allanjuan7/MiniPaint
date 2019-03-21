package controle;

import formas.Figura;
import formas.Forma;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import telas.TelaPintura;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Controle {

    /* Padrão de Projeto SINGLETON - Asseguro que haverá sempre
    uma e somente uma instância de Controle, acessível em qualquer lugar */
    public static Controle controle = new Controle();
    private TelaPintura telaPintura;
    private ListView<Forma> listView;
    private ArrayList<Forma> formas;

    public Controle() {
        formas = new ArrayList<>();
    }

    public void setTelaPintura(TelaPintura telaPintura) {
        this.telaPintura = telaPintura;
    }

    public void setListView(ListView<Forma> listView) {
        this.listView = listView;
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

    public void deletarForma(TelaPintura telaPintura, ListView listView) {
        int index = listView.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            controle.getFormas().remove(index);

            controle.apagarQuadro(telaPintura);
            controle.atualizarListViewERedesenhar(telaPintura, listView);
        }
    }

    public void limpar() {
        formas.clear();
    }

    public void apagarQuadro(TelaPintura telaPintura) {
        GraphicsContext contextoGrafico = telaPintura.getGraphicsContext2D();
        contextoGrafico.clearRect(0, 0, telaPintura.getWidth(), telaPintura.getHeight());
    }

    public void reiniciarFigura(ListView listView, TelaPintura telaPintura) {
        controle.limpar();
        listView.getItems().clear();
        controle.apagarQuadro(telaPintura);
    }

    public void atualizarListViewERedesenhar(TelaPintura telaPintura, ListView listView) {

        listView.getItems().clear();

        for (Forma f : formas) {
            listView.getItems().add(f);
            telaPintura.desenhar(f, f.getModoDeDesenho());
        }
    }
}
