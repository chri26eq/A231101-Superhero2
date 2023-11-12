package userinterface;


import java.util.Scanner;

public class Input {


    public static int scannerInt(Scanner scanner) { // Input skal være en integer
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Undgå scanner bug
                return input; // Hvis input er en integer
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en integer
                System.out.println(Farve.ANSI_RED + "Input must be an integer" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }

    public static int scannerInt(Scanner scanner, int min, int max) { // Input skal være en integer med min. og maks. værdi:
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Undgå scanner bug

                if (input >= min && input <= max) return input; // Hvis input er en integer
                else
                    System.out.println(Farve.ANSI_RED + "Input must be an integer between " + min + " and " + max + Farve.ANSI_RESET);
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en integer
                System.out.println(Farve.ANSI_RED + "Input must be an integer" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }

    public static int scannerInt(Scanner scanner, int min, int max, int otherInteger) { // Input skal være en integer med min. og maks. værdi eller anden eksakt integer:
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Undgå scanner bug

                if ((input >= min && input <= max) || input == otherInteger)
                    return input; // Hvis input er en integer mellem min og max eller lig otherNumber
                else
                    System.out.println(Farve.ANSI_RED + "Input must be an integer between " + min + " and " + max + " or equal to " + otherInteger + Farve.ANSI_RESET);
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en integer
                System.out.println(Farve.ANSI_RED + "Input must be an integer" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }


    public static boolean scannerBoolean(Scanner scanner) { // Input skal være en boolean
        System.out.print(Farve.ANSI_GREEN + "Y" + Farve.ANSI_RESET + "es or " + Farve.ANSI_GREEN + "N" + Farve.ANSI_RESET + "o: \n");
        while (true) {
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println(Farve.ANSI_RED + "Input must be \"Y\" or \"N\"" + Farve.ANSI_RESET);
            }
        }
    }


}
