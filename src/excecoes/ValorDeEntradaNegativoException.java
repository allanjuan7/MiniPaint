package excecoes;

import popup.Mensagem;

public class ValorDeEntradaNegativoException extends Exception {
    public void mostrarPopUpDeErro(){
        Mensagem mensagemDeErro = new Mensagem("Valor de entrada inválido. Insira um valor maior que 0.", "Valores inválidos", 100, 500, "recursos/icone_erro.png");
        mensagemDeErro.mostrar();
    }
}
