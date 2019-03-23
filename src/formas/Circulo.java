package formas;

import controle.Controle;
import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import popup.Mensagem;
import popup.TelaEdicaoCirculo;
import telas.TelaPintura;

public class Circulo extends Forma{

    private double raio;

    public Circulo(double xInicial, double yInicial, Color cor, double raio, int id) throws Exception{
        super(xInicial, yInicial, cor, id);
        if (raio <= 0){
            throw new ValorDeEntradaNegativoException();
        }
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) throws ValorDeEntradaNegativoException{
        if (raio <= 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.raio = raio;
    }

    public void desenhar(GraphicsContext contexto){
        super.desenhar(contexto);

        if (modoDeDesenho.equals("Contornar"))
            contexto.strokeOval(xInicial, yInicial, raio, raio);

        if (modoDeDesenho.equals("Preencher"))
            contexto.fillOval(xInicial, yInicial, raio, raio);
    }

    public void editar(TelaPintura telaPintura, ListView listView){
        TelaEdicaoCirculo telaEdicao = new TelaEdicaoCirculo(this);

        telaEdicao.getBtnConfirmar().setOnAction( e -> {
            try {

                setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                setRaio(telaEdicao.getEntradaRaio().getValorCampo());
                setCor(telaEdicao.getColorPicker().getValue());

                telaEdicao.getStage().close();
                Controle.controle.apagarQuadro();
                Controle.controle.atualizarListViewERedesenhar(telaPintura, listView);

            } catch (ValorDeEntradaNegativoException ex){

                Mensagem mensagemDeErro = new Mensagem("Valor de entrada inválido. Insira um valor maior que 0.", "Valores invalidos", 100, 500);
                mensagemDeErro.mostrar();
            }
        });

        telaEdicao.mostrar();
    }

    public String toString(){
        return "Círculo " + id;
    }
}
