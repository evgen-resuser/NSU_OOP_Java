package game;

import game.model.Facade;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {
    private final Facade facade;

    public Controls(Facade facade){
        this.facade = facade;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case 39 -> facade.sendKey('R');

            case 38 -> facade.sendKey('U');

            case 37 -> facade.sendKey('L');

            case 40 -> facade.sendKey('D');

            default -> facade.sendKey(' ');
        }
    }
}
