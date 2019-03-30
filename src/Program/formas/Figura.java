package Program.formas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Figura implements Serializable {

    private List<Forma> formas;
    private String nome;

    public Figura(){
        formas = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addForma(Forma f){
        formas.add(f);
    }

    public List<Forma> getFormas() {
        return formas;
    }

    public void deletarFormaEm(int index){
        formas.remove(index);
    }

    public void limpar(){
        formas.clear();
    }

    public void incorporarFiguraCarregada(Figura figuraCarregada){
        for (Forma forma : figuraCarregada.getFormas()){
            this.addForma(forma);
        }
    }
}
