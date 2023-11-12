package domain_model;

import data_source.FileHandler;
import domain_model.comparator.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Database {
    private FileHandler fileHandler;
    private ArrayList<Superhero> superheroList;
    private int editedSuperheroIndex;
    private Superhero editedSuperhero = new Superhero();


    public Database(String separator) {
        fileHandler = new FileHandler(separator,"superhero.csv"); // Standard filnavn hvis intet andet angives
        superheroList = fileHandler.loadSuperheroesFromCSV();
    }

    public Database(String separator, String csvFileName) {
        fileHandler = new FileHandler(separator, csvFileName);
        superheroList = fileHandler.loadSuperheroesFromCSV();
    }


    private void saveDatabaseInCSV() {
        fileHandler.saveSuperheroesInCSV(superheroList);
    }


    public void addSuperhero(Superhero superhero) {
        superheroList.add(superhero);
        saveDatabaseInCSV();
    }

    public ReturnMessage removeSuperhero(int input, int exitIndex) {
        if (input == exitIndex) return ReturnMessage.EXIT;
        superheroList.remove(input - 1);
        saveDatabaseInCSV();
        return ReturnMessage.DELETED;
    }

    public Superhero getEditedSuperhero() {
        return editedSuperhero;
    }

    public void addEditedSuperheroToList() {
        Superhero editedSuperheroInList = superheroList.get(editedSuperheroIndex);
        if (!editedSuperheroInList.fieldsAreEqualTo(editedSuperhero)) {
            editedSuperheroInList.makeCopyOf(editedSuperhero);
            saveDatabaseInCSV();
        }
    }


    public ArrayList<Superhero> sortedSuperheroList(int numberOfCriteria, int inputComparator1, int inputSortOrder1, int inputComparator2, int inputSortOrder2) {
        ArrayList<Superhero> sortedList = new ArrayList<>(superheroList);

        Comparator[] comparators = {
                new NameComparator(),
                new RealNameComparator(),
                new SuperpowerComparator(),
                new YearComparator(),
                new IsHumanComparator(),
                new StrengthLevelComparator()};

        Comparator primaryComparator = comparators[inputComparator1-1];
        if (inputSortOrder1 == 2) primaryComparator = primaryComparator.reversed();

        switch (numberOfCriteria) {
            case 1 -> sortedList.sort(primaryComparator);
            case 2 -> {
                Comparator secondaryComparator = comparators[inputComparator2-1];
                if (inputSortOrder2 == 2) secondaryComparator = secondaryComparator.reversed();
                sortedList.sort(primaryComparator.thenComparing(secondaryComparator));
            }
        }
        return sortedList;
    }


    public int superheroListSize() {
        return superheroList.size();
    }

    public String superheroesNumbered() {
        StringBuilder sb = new StringBuilder();
        if (superheroList.isEmpty()) sb.append("Empty.\n");
        else {
            for (int i = 0; i < superheroList.size(); i++) {
                sb.append(i + 1).append(") ").append(superheroList.get(i).getName()).append("\n");
            }
        }
        return sb.toString();
    }

    public ReturnMessage makeCurrentSuperheroCopyOfSuperheroFromList(int input, int exitIndex) {
        if (input == exitIndex) return ReturnMessage.EXIT;
        editedSuperhero.makeCopyOf(superheroList.get(input - 1));
        editedSuperheroIndex = (input - 1);
        return ReturnMessage.FOUND;
    }


    public ArrayList<Superhero> getSuperheroList() { // Bruges kun i UnitTest
        return superheroList;
    }

    public void setCurrentSuperheroName(String name) {
        editedSuperhero.setName(name);
    }

    public void setCurrentSuperheroRealName(String realName) {
        editedSuperhero.setRealName(realName);
    }

    public void setCurrentSuperheroSuperpower(String superpower) {
        editedSuperhero.setSuperpower(superpower);
    }

    public void setCurrentSuperheroYearCreated(int yearCreated) {
        editedSuperhero.setYearCreated(yearCreated);
    }

    public void setCurrentSuperheroHuman(boolean isHuman) {
        editedSuperhero.setHuman(isHuman);
    }

    public void setCurrentSuperheroStrengthLevel(int strengthLevel) {
        editedSuperhero.setStrengthLevel(strengthLevel);
    }


    @Override
    public String toString() {
        String header = String.format("%-18s%-21s%-27s%-17s%-10s%-10s",
                "Name", "Real Name", "Superpower", "Year Created", "Type", "Strength Level");
        StringBuilder list = new StringBuilder(header).append("\n");

        for (Superhero superhero : superheroList) {
            list.append(superhero).append("\n");
        }

        return list.toString();
    }

}
