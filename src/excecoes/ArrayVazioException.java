package excecoes;

import popup.Mensagem;

public class ArrayVazioException extends Exception {
    public void mostrarPopUpDeErro(){
        Mensagem mensagemDeErro = new Mensagem("Nenhuma forma foi criada. Tente novamente!", "Erro", 100, 500, "recursos/icone_erro.png");
        mensagemDeErro.mostrar();
    }
}
