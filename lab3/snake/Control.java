package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

    public Control() {
    }

    public char keyPressed(KeyEvent e, char curPos){
        switch (e.getKeyChar()) {
            case 'R' -> {
                if (curPos != 'L') return 'R';
            }
            case 'L' -> {
                if (curPos != 'R') return 'L';
            }
            case 'U' -> {
                if (curPos != 'D') return 'U';
            }
            case 'D' -> {
                if (curPos != 'U') return 'D';
            }
        }
        return curPos;
    }
}
