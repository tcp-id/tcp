package ex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.lines;


public class Ordi {
    String numeroSerie;
    int velocitat;
    boolean portatil;
    public Ordi() {
    }
    public Ordi(String numeroSerie, int velocitat, boolean portatil) {
        this.numeroSerie = numeroSerie;
        this.velocitat = velocitat;
        this.portatil = portatil;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public boolean isPortatil() {
        return portatil;
    }

    public void imp(String ruta, List<Ordi> to) throws IOException {
        // Lista con los gets de los ordis
        Path rutados = Paths.get(ruta);
        BufferedWriter bf = Files.newBufferedWriter(rutados);

        for (Ordi o : to) {
            bf.write(o.getNumeroSerie() + o.getVelocitat() + o.isPortatil());
            bf.newLine();
        }
        bf.close();
    }

    public List<Ordi> leer (String ruta) throws IOException {
        String cadena;
        FileReader f = new FileReader(ruta);
        BufferedReader b = new BufferedReader(f);

       List<Ordi> lista = new ArrayList<Ordi>();
               b.lines.collect(Collectors.toList());
        //for(String s : lista){
        while((cadena = b.readLine())!=null) {
           lista.add((Ordi) lista);
        }
        b.close();
        return lista;

    }
}








































