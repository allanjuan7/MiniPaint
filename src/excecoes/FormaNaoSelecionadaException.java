package excecoes;

import popup.Mensagem;

public class FormaNaoSelecionadaException extends NullPointerException {
    public void mostrarPopUpDeErro(){
        Mensagem mensagemDeErro = new Mensagem("Nenhuma forma foi selecionada. Tente novamente!", "Erro", 100, 500, "recursos/icone_erro.png");
        mensagemDeErro.mostrar();
    }
}
