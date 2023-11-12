import domain_model.Controller;
import userinterface.UserInterface;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(";");
        UserInterface userInterface = new UserInterface(controller);
        //userInterface.createPreloadedDatabase();
        userInterface.startProgram();
    }
}
