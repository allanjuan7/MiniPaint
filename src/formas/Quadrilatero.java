package formas;

import controle.Controle;
import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import popup.Mensagem;
import popup.TelaEdicaoPoligono;
import telas.TelaPintura;

public class Quadrilatero extends Forma {

    private double base, altura;

    public Quadrilatero(double xInicial, double yInicial, Color cor, double base, double altura, int id) throws Exception{
        super(xInicial, yInicial, cor, id);

        if (base < 0 || altura < 0){
            throw new ValorDeEntradaNegativoException();
        }

        this.base = base;
        this.altura = altura;
    }

    public void setBase(double base) throws ValorDeEntradaNegativoException {
        if (base < 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.base = base;
    }

    public double getBase() {
        return base;
    }

    public void setAltura(double altura) throws ValorDeEntradaNegativoException {
        if (altura < 0) {
            throw new ValorDeEntradaNegativoException();
        }
        this.altura = altura;
    }

    public double getAltura() {
        return altura;
    }

    public void desenhar(GraphicsContext contexto){
        super.desenhar(contexto);

        if (modoDeDesenho.equals("Contornar"))
            contexto.strokeRect(xInicial, yInicial, base, altura);

        if (modoDeDesenho.equals("Preencher"))
            contexto.fillRect(xInicial, yInicial, base, altura);
    }

    public void editar(TelaPintura telaPintura, ListView listView){
        TelaEdicaoPoligono telaEdicao = new TelaEdicaoPoligono(this);

        telaEdicao.getBtnConfirmar().setOnAction(e -> {

            try{

                setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                setBase(telaEdicao.getEntradaAltura().getValorCampo());
                setAltura(telaEdicao.getEntradaBase().getValorCampo());
                setCor(telaEdicao.getColorPicker().getValue());

                telaEdicao.getStage().close(); Controle.controle.apagarQuadro();
                Controle.controle.atualizarListViewERedesenhar(telaPintura, listView);

            } catch(ValorDeEntradaNegativoException ex){

                Mensagem mensagemDeErro = new Mensagem("Valor de entrada negativo. Insira um valor válido", "Valores invalidos", 100, 500);
                mensagemDeErro.mostrar();
            }
        });

        telaEdicao.mostrar();
    }
    public String toString(){
        return "Quadrilátero " + id;
    }
}
