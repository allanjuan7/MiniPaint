package formas;

import java.util.ArrayList;
import java.util.List;

public class Figura {

    private List<Forma> formas;

    public Figura(){
        formas = new ArrayList<>();
    }

    public void addForma(Forma f){
        formas.add(f);
    }

    public void limpar(){
        formas.clear();
    }

    public List<Forma> getFormas() {
        return formas;
    }

    public void deletarFormaEm(int index){
        formas.remove(index);
    }
}
