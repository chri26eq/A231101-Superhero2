package data_source;

import domain_model.Superhero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final String CSV_FILENAME;
    private final String SEPARATOR;


    public FileHandler(String separator, String csvFileName) {
        this.SEPARATOR = separator;
        this.CSV_FILENAME = csvFileName;
    }

    public void saveSuperheroesInCSV(ArrayList<Superhero> superheroList) {

        try (PrintStream printStream = new PrintStream(CSV_FILENAME)) {
            // Skriv CSV-header
            printStream.println("Superhero name" + SEPARATOR + " Real name" + SEPARATOR + " Superpower" + SEPARATOR + " Year created" + SEPARATOR + " Is human" + SEPARATOR + " Strength level");

            // Skriv superhelte-data til CSV-filen
            for (Superhero superhero : superheroList) {
                printStream.println(superhero.getName() + SEPARATOR +
                        superhero.getRealName() + SEPARATOR +
                        superhero.getSuperpower() + SEPARATOR +
                        superhero.getYearCreated() + SEPARATOR +
                        superhero.isHuman() + SEPARATOR +
                        superhero.getStrengthLevel());
            }

            // System.out.println("Superhero saved in " + csvFileName); //TODO skal slettes
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public ArrayList<Superhero> loadSuperheroesFromCSV() {
        ArrayList<Superhero> superheroList = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(CSV_FILENAME));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException(e);
        }
        if (scanner.hasNextLine()) scanner.nextLine(); //Skip header
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] attributes = line.split(SEPARATOR);

            Superhero readSuperhero = new Superhero(
                    attributes[0],
                    attributes[1],
                    attributes[2],
                    Integer.parseInt(attributes[3]),
                    Boolean.parseBoolean(attributes[4]),
                    Integer.parseInt(attributes[5])
            );
            superheroList.add(readSuperhero);

        }
        return superheroList;
    }
}