import game.Controls;
import game.view.Window;
import game.model.Facade;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade(4);

        Window window = new Window(facade);
        window.setSize(545, 650);
        window.setVisible(true);
        window.setName("2048");

        ImageIcon icon = new ImageIcon("src/game/sprites/logo.png");
        window.setIconImage(icon.getImage());

        Controls controls = new Controls(facade);
        window.addKeyListener(controls);

    }
}