package excecoes;

import popup.Mensagem;

public class MovimentoInvalidoException extends Exception{
    public void mostrarPopUpDeErro(){
        Mensagem mensagemDeErro = new Mensagem("O movimento ultrapassa os limites da figura!", "Movimento Inválido", 100, 500, "recursos/icone_erro.png");
        mensagemDeErro.mostrar();
    }
}
