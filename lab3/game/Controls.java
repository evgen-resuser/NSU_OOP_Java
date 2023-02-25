package game;

import game.model.Facade;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {
    private final Facade facade;

    private char direction = ' ';

    public char getDirection(){
        return direction;
    }

    public Controls(Facade facade){
        this.facade = facade;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39 -> {    // -> Right
                System.out.println("Key pressed: Right");
                direction = 'R';
            }
            case 38 -> {    // -> Top
                System.out.println("Key pressed: Up");
                direction = 'U';
            }
            case 37 -> {    // -> Left
                System.out.println("Key pressed: Left");
                direction = 'L';
            }
            case 40 -> {    // -> Bottom
                System.out.println("Key pressed: Down");
                direction = 'D';
            }
            default -> {
            }
        }
        facade.sendKey(direction);
    }
}
