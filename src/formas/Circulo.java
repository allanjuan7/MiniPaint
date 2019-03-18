package formas;

import excecoes.ValorDeEntradaNegativoException;
import javafx.scene.paint.Color;

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

    public String toString(){
        return "Círculo " + id;
    }
}
