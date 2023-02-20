package snake;

import java.util.ArrayList;

public class Core {
    int direction = 'R';
    Control controller;

    int size = 3;
    int speed = 100;

    Position snakeHead;
    Position applePos;

    ArrayList<Position> positions = new ArrayList<>();

    public Core(){
        controller = new Control();
    }
}
