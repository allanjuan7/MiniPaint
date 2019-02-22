package formas;

import java.util.ArrayList;

public class Figura {

    private ArrayList<Forma> formas;

    public Figura(){
        formas = new ArrayList<>();
    }

    public void addForma(Forma f){
        formas.add(f);
    }

    public void editarFormaEm(int index){
        formas.get(index).editar();

        /* É necessário adicionar o tratamento de erros
        em caso de acesso inválido no array */
    }

    public void deletarFormaEm(int index){
        /*
        Antes de implementar essa Forma, é preciso definir
        se utilizaremos um sistema de camadas.

        Se sim, basta excluir a camada referente à forma escolhida.

        Se não, será preciso limpar o quadro e desenhar novamente todas as
        formas, excetuando a forma excluída.
         */
    }
}
