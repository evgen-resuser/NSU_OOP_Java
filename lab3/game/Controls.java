package game;

import game.model.Facade;
import game.observer.IObserver;
import game.view.Window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter implements IObserver {
    private final Facade facade;
    private final Window window;

    /**
     * Creates controller of the game, which processes keyboard
     * input, updates the window with messages from the core.
     * @param facade facade of Game Core
     */
    public Controls(Facade facade){
        this.facade = facade;
        this.window = new Window(facade, 0, this);
        facade.getObject().registerObserver(this);

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

    @Override
    public void update(Message msg){
        window.update(msg);
    }
}
