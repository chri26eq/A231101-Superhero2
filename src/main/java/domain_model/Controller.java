package domain_model;

import java.util.ArrayList;

public class Controller {

    private Database database;

    public Controller(String separator, String csvFileName) {
        database = new Database(separator, csvFileName);
    }

    public Controller(String separator) {
        database = new Database(separator);
    }


    public void addSuperheroToDatabase(Superhero superhero) {
        database.addSuperhero(superhero);
    }

    public ReturnMessage chooseSuperheroToEdit(int input, int exitIndex) {
        return database.makeCurrentSuperheroCopyOfSuperheroFromList(input, exitIndex);
    }

    public Superhero currentSuperheroInDatabase() {
        return database.getEditedSuperhero();
    }

    public void saveCurrentSuperhero() {
        database.addEditedSuperheroToList();
    }

    public ReturnMessage chooseSuperheroToDelete(int input, int exitIndex) {
        return database.removeSuperhero(input, exitIndex);
    }

    public ArrayList<Superhero> sortedListFromDatabase(int numberOfCriteria, int inputComparator1, int inputSortOrder1, int inputComparator2, int inputSortOrder2) {
        return database.sortedSuperheroList(numberOfCriteria, inputComparator1, inputSortOrder1, inputComparator2, inputSortOrder2);
    }


    public String superheroesNumberedFromDatabase() {
        return database.superheroesNumbered();
    }

    public Database getSuperheroList() {
        return database;
    }

    public int numberOfSuperheroesInDatabase() {
        return database.superheroListSize();
    }

    public void setCurrentSuperheroNameInDatabase(String name) {
        database.setCurrentSuperheroName(name);
    }

    public void setCurrentSuperheroRealNameInDatabase(String realName) {
        database.setCurrentSuperheroRealName(realName);
    }

    public void setCurrentSuperheroSuperpowerInDatabase(String superpower) {
        database.setCurrentSuperheroSuperpower(superpower);
    }

    public void setCurrentSuperheroYearCreatedInDatabase(int yearCreated) {
        database.setCurrentSuperheroYearCreated(yearCreated);
    }

    public void setCurrentSuperheroHumanInDatabase(boolean isHuman) {
        database.setCurrentSuperheroHuman(isHuman);
    }

    public void setCurrentSuperheroStrengthLevelInDatabase(int strengthLevel) {
        database.setCurrentSuperheroStrengthLevel(strengthLevel);
    }

}
