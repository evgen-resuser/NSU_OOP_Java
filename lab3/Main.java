import game.Controls;
import game.model.Facade;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade(4);
        new Controls(facade);
    }
}