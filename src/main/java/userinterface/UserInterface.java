package userinterface;

import domain_model.Controller;
import domain_model.ReturnMessage;
import domain_model.Superhero;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private Controller controller;

    public UserInterface (Controller controller) {
        this.controller = controller;
    }

    private final int EXIT_NUMBER = 0;
    private final int SAVE_NUMBER = 9;


    public void createPreloadedDatabase() {
        controller.addSuperheroToDatabase(new Superhero("Fat Fury", "Herbie Popnecker", "Flying, Invisibility", 1958, true, 500));
        controller.addSuperheroToDatabase(new Superhero("Miracleman", "Michael \"Mike\" Moran", "Superhuman strength", 1954, true, 1000));
        controller.addSuperheroToDatabase(new Superhero("Madcap", "Unknown", "Insanity inducement", 1985, true, 400));
        controller.addSuperheroToDatabase(new Superhero("Atom Smasher", "Albert Rothstein", "Mass manipulation", 1996, true, 900));
        controller.addSuperheroToDatabase(new Superhero("Adam Warlock", "Adam Warlock", "Genius-Level Intelligence", 1967, false, 700));

    } // TODO Bør slettes

    public void startProgram() {
        boolean running = true;
        int inputInt;


        while (running) {

            System.out.println(mainmenu());
            inputInt = Input.scannerInt(scanner, 0, 9);

            switch (inputInt) {
                case 1 -> createMenu();
                case 2 -> editMenu();
                case 3 -> deleteMenu();
                case 4 -> {
                    System.out.println(controller.getSuperheroList());
                    System.out.println(EXIT_NUMBER + ") Exit");
                    scanner.nextLine();
                }
                case 5 -> printSortedListOfSuperhero();
                case 6 -> {

                }
                case 7 -> {

                }
                case 8 -> {
                }
                case 9 -> {
                }
                case 0 -> {

                    System.out.println("Shutting down...");
                    running = false;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void printSortedListOfSuperhero() {
        String[] criterion1 = {"", "", "", "", "", ""};
        String[] criterion2 = {"", "", "", "", "", ""};
        String[] sortOrderArrows = {" ▲"," ▼"};

        System.out.println("Number of sort criteria:\n1) One criteria\n2) Two criteria\n");
        int numberOfCriteria = Input.scannerInt(scanner, 1, 2);

        System.out.println(("Choose primary sort criterion\n" + Superhero.fieldsNumbered()));
        int sortCriterion1 = Input.scannerInt(scanner, 1, Superhero.class.getDeclaredFields().length);

        System.out.println("Choose sort order:\n1) Ascending\n2) Descending\n");
        int sortOrder1 = Input.scannerInt(scanner, 1, 2);
        criterion1[sortCriterion1-1] = sortOrderArrows[sortOrder1-1];

        int sortCriterion2 = 0;
        int sortOrder2 = 0;

        if (numberOfCriteria == 2) {
            System.out.println(("Choose secondary sort criterion\n" + Superhero.fieldsNumbered()));
            sortCriterion2 = Input.scannerInt(scanner, 1, Superhero.class.getDeclaredFields().length);

            System.out.println("Choose sort order:\n1) Ascending\n2) Descending\n");
            sortOrder2 = Input.scannerInt(scanner, 1, 2);
            criterion2[sortCriterion2-1] = sortOrderArrows[sortOrder2-1];
        }

        ArrayList<Superhero> list = (controller.sortedListFromDatabase(numberOfCriteria, sortCriterion1, sortOrder1, sortCriterion2, sortOrder2));
        StringBuilder sb = new StringBuilder();
        for (Superhero superhero : list) {
            sb.append(superhero).append("\n");
        }

        String header = String.format("%-18s%-21s%-27s%-17s%-10s%-10s",
                "Name" + criterion1[0] + criterion2[0],
                "Real Name" + criterion1[1] + criterion2[1],
                "Superpower" + criterion1[2] + criterion2[2],
                "Year Created" + criterion1[3] + criterion2[3],
                "Type" + criterion1[4] + criterion2[4],
                "Strength Level" + criterion1[5] + criterion2[5]);
        System.out.println(header + "\n" + sb + EXIT_NUMBER + ") Exit");
        scanner.nextLine();
    }

    private String mainmenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to the Superhero Database.\n");
        sb.append("1) Add superhero\n");
        sb.append("2) Edit superhero\n");
        sb.append("3) Delete superhero\n");
        sb.append("4) View database\n");
        sb.append("5) View database (sorted)\n");
        sb.append("6) Empty\n");
        sb.append("7) Empty\n");
        sb.append("8) Empty\n");
        sb.append("9) Empty\n");
        sb.append("0) Exit\n");
        return sb.toString();
    }

    private void createMenu() {
        System.out.println("Superhero name: ");
        String name = scanner.nextLine();

        System.out.println("Real name: ");
        String realName = scanner.nextLine();

        System.out.println("Superpower: ");
        String superpower = scanner.nextLine();

        System.out.println("Year created: ");
        int yearCreated = Input.scannerInt(scanner);

        System.out.println("Is superhero human?: ");
        boolean isHuman = Input.scannerBoolean(scanner);

        System.out.println("Strength level: ");
        int strengthLevel = Input.scannerInt(scanner);
        controller.addSuperheroToDatabase(new Superhero(name, realName, superpower, yearCreated, isHuman, strengthLevel));

    }

    private void editMenu() {
        int inputInt;
        ReturnMessage foundReturnMessage;

        System.out.println(chooseSuperheroText("Choose which superhero to edit:")); //Choose which superhero to edit + Numeric list of superheroes + 0 Exit

        inputInt = (Input.scannerInt(scanner, 0, controller.numberOfSuperheroesInDatabase()));
        foundReturnMessage = controller.chooseSuperheroToEdit(inputInt, EXIT_NUMBER);
        switch (foundReturnMessage) {
            case EXIT -> System.out.println("Exiting...\n");
            case FOUND -> {
                do {
                    System.out.println(editSuperheroText()); //Choose what to edit + Numeric list of fields + 9 Save + 0 Exit
                    inputInt = Input.scannerInt(scanner, 0, Superhero.class.getDeclaredFields().length, SAVE_NUMBER);
                    editSuperheroInDatabase(inputInt);
                } while (inputInt != EXIT_NUMBER && inputInt != SAVE_NUMBER);
            }
        }

    }

    private String chooseSuperheroText(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append("\n");
        sb.append(controller.superheroesNumberedFromDatabase());
        sb.append(EXIT_NUMBER).append(") Exit.");
        return sb.toString();
    }

    private String editSuperheroText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Choose what you want to edit: \n");
        sb.append(controller.currentSuperheroInDatabase().superheroFieldsNumbered());
        sb.append(SAVE_NUMBER).append(") Save and exit.\n");
        sb.append(EXIT_NUMBER).append(") Cancel and exit.");
        return sb.toString();


    }

    private void editSuperheroInDatabase(int i) {

        switch (i) {

            case 1 -> {
                System.out.println("Superhero name: ");
                controller.setCurrentSuperheroNameInDatabase(scanner.nextLine());
            }
            case 2 -> {
                System.out.println("Real name: ");
                controller.setCurrentSuperheroRealNameInDatabase(scanner.nextLine());
            }
            case 3 -> {
                System.out.println("Superpower: ");
                controller.setCurrentSuperheroSuperpowerInDatabase(scanner.nextLine());
            }
            case 4 -> {
                System.out.println("Year created: ");
                controller.setCurrentSuperheroYearCreatedInDatabase(Input.scannerInt(scanner));
            }
            case 5 -> {
                System.out.println("Is superhero human?: ");
                controller.setCurrentSuperheroHumanInDatabase(Input.scannerBoolean(scanner));
            }
            case 6 -> {
                System.out.println("Strength level: ");
                controller.setCurrentSuperheroStrengthLevelInDatabase(Input.scannerInt(scanner));
            }
            case SAVE_NUMBER -> {
                System.out.println("Changes saved...");
                controller.saveCurrentSuperhero();
            }
            case EXIT_NUMBER -> System.out.println("Exiting...");

        }


    }


    private void deleteMenu() {
        System.out.println(chooseSuperheroText("Choose which superhero to delete:"));
        int inputInt = Input.scannerInt(scanner, 0, controller.numberOfSuperheroesInDatabase());
        ReturnMessage deleteReturnMessage = controller.chooseSuperheroToDelete(inputInt, EXIT_NUMBER);
        switch (deleteReturnMessage) {
            case EXIT -> System.out.println("Exiting...\n");
            case DELETED -> System.out.println("Superhero deleted.\n");
        }
    }


}
