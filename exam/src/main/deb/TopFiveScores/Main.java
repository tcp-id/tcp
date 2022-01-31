package main.deb.TopFiveScores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Score {
    String name;
    int points;

    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%-20s%5s", name, points);
    }
}

public class Main {

    static String scoreFile = "C:\\Users\\arnau\\Documents\\DAM\\M06\\solucions_m06_uf1\\src\\com\\company\\TopFiveScores\\scores.json";
    static String fieldSep = ":";

    public static void main(String[] args) throws IOException {
        addScore("James Gosling", 300);
        addScore("Anders Hejlsberg", 500);
        addScore("Chris Lattner", 400);
        addScore("Brendan Eich", 200);
        addScore("Bjarne Stroustrup", 600);
        addScore("Guido van Rossum", 100);
    }

    static void addScore(String name, int points) throws IOException {
        //creem una llista de resultats
        List<Score> scores = new ArrayList<>();

        //provem a crear un bufferedReader en el cas de que si...
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(scoreFile))){
            //fem un for que recorre linia per linia fins la última linia del bufferedReader
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                //creem una array de String on separarem les línies per ":"
                String[] fields = line.split(fieldSep);
                //afegim a la llista el nou resultat amb el seu corresponents noms
                scores.add(new Score(fields[0], Integer.parseInt(fields[1])));
            }
            // en el cas de que no agafem l'excepció
        } catch (Exception e) {

        }

        //en el cas de que hagues saltat no o si, afegirem igualtem el resultat amb el nom i punts otorgats
        scores.add(new Score(name, points));

        //el que estem fent es ordenar-los, limitant la llista a 5.
        scores = scores.stream()
                .sorted(Comparator.comparing(Score::getPoints).reversed())
                .limit(5)
                .collect(Collectors.toList());

        //Escriurem al nostre ficher JSON els següents resultats.
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(scoreFile))){
            for (Score score:scores) {
                bufferedWriter.write(score.name + fieldSep + score.points);
                bufferedWriter.newLine();
            }
        }

        //Els imprimirem
        System.out.println("\033[31m** TOP 5 SCORE **\033[0m\n" + scores.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n")));
    }
}