package game2048;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {
    private char direction;

    public Controls(){
        direction = ' ';
    }

    public char getDirection(){
        return direction;
    }

    public void setDirection(char newDirection){
        direction = newDirection;
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case 39:	// -> Right
                direction = 'R';
                break;
            case 38:	// -> Top
                direction = 'U';
                break;

            case 37: 	// -> Left
                direction = 'L';
                break;

            case 40:	// -> Bottom
                direction = 'D';
                break;

            default: 	break;
        }

    }
}
