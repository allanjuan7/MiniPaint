package formas;

import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.paint.Color;

public class Triangulo extends Forma {

    private double base, altura;

    public Triangulo(double xInicial, double yInicial, Color cor, double base, double altura, int id) throws Exception{
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

    public String toString(){
        return "Triângulo " + id;
    }
}
