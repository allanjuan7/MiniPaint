package formas;

import controle.Controle;
import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import popup.Mensagem;
import popup.TelaEdicaoPoligono;
import telas.TelaPintura;

public class Triangulo extends Forma {


    private double base, altura;
    private String modelo;

    public Triangulo(double xInicial, double yInicial, Color cor, double base, double altura, int id, String modelo) throws Exception{
        super(xInicial, yInicial, cor, id);

        if (base < 0 || altura < 0){
            throw new ValorDeEntradaNegativoException();
        }

        this.base = base;
        this.altura = altura;
        this.modelo = modelo;
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

    /* Apenas um dos 4 casos está sendo desenhado corretamente.
    * Os triângulos estão sendo desenhados com as dimensões corretas, porém em posições erradas.
    * Talvez seja necessário passar também as coordenadas do ponto final como parâmetro.*/
    public void desenhar(GraphicsContext contexto){
        super.desenhar(contexto);

        double Ax, Ay, Bx, By, Cx, Cy;

        Ax = xInicial; Ay = yInicial;
        Bx = xInicial; By = yInicial;
        Cx = xInicial; Cy = yInicial;

        if (modelo == "A"){
            By += altura;
            Cx += base; Cy += altura;
        }

        if (modelo == "B"){
            Bx += base;
            Cx += base; Cy -= altura;
        }

        if (modelo == "C"){
            Bx -= base;
            Cx -= base; Cy -= altura;
        }

        if (modelo == "D"){
            By += altura;
            Cx -= base; Cy += altura;
        }

        if (modoDeDesenho.equals("Contornar"))
            contexto.strokePolygon(new double [] {Ax, Bx, Cx},
                    new double [] {Ay, By, Cy}, 3);

        if (modoDeDesenho.equals("Preencher"))
            contexto.fillPolygon(new double [] {Ax, Bx, Cx},
                    new double [] {Ay, By, Cy}, 3);
    }

    public void editar(TelaPintura telaPintura, ListView listView){
        TelaEdicaoPoligono telaEdicao = new TelaEdicaoPoligono(this);

        telaEdicao.getBtnConfirmar().setOnAction(e -> {
            try {

                setxInicial(telaEdicao.getEntradaPosX().getValorCampo());
                setyInicial(telaEdicao.getEntradaPosY().getValorCampo());
                setBase(telaEdicao.getEntradaBase().getValorCampo());
                setAltura(telaEdicao.getEntradaAltura().getValorCampo());
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
        return "Triângulo " + id;
    }
}
