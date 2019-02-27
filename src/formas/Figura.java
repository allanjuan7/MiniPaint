package formas;

import java.util.ArrayList;
import java.util.List;

public class Figura {

    /* É importante observar se instanciaremos "formas" dessa maneira
    * ou com polimorfismo, como o professor sugeriu, deixando o programa mais generalizado.*/
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
    public void editarFormaEm(int index){
        formas.get(index).editar();

        /* É necessário adicionar o tratamento de erros
        em caso de acesso inválido no array */
    }

    public List<Forma> getFormas() {
        return formas;
    }

    public void deletarFormaEm(int index){
        formas.remove(index);
    }
}
