package entradaesaida;

import formas.Figura;
import formas.Forma;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Arquivo {

    public static void salvarFigura(Figura figura, String nomeDoArquivo, String caminhoDoArquivo) throws Exception{

        try{

            for (Forma f : figura.getFormas()){
                f.setRgbString(f.getCor().toString());
            }

            // O caminho é passado em nomeDoArquivo
            FileOutputStream fileStream = new FileOutputStream(nomeDoArquivo);

            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(figura);

            objectStream.close();

        }  catch (Exception e){
            e.printStackTrace();
        }


    }
    public static Figura carregarFigura(String caminho) {

        try {

            FileInputStream fileStream = new FileInputStream(caminho);

            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            Object objetoCarregado = objectStream.readObject();

            Figura figuraCarregada = (Figura) objetoCarregado;

            Color cor;

            for (Forma f : figuraCarregada.getFormas()){
                cor = Color.web(f.getRgbString());
                f.setCor(cor);
            }

            return figuraCarregada;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
