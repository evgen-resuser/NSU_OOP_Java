package game.model;

import game.observer.IObject;
import game.observer.IObserver;

class Model extends Thread implements IObject {
    int size = 4;
    private int score;
    int[][] nums = new int[size][size];

    IObserver observer;
    private Message msg;

    char pressedKey = ' ';


    public Model(){
        score = 1;
        msg = Message.UPDATE;
    }

    private void numsPermutations(){
        if (score == 5) msg = Message.GAME_OVER;
        nums[0][0] = score;
        score++;
        notifyObserver();
    }

    @Override
    public void run() {
        while (this.isAlive()) {

            if (pressedKey != ' ') {
                System.out.println("Key pressed from model: " + pressedKey);
                pressedKey = ' ';
                numsPermutations();
            }
        }
    }

    @Override
    public void registerObserver(IObserver observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        observer.update(msg);
    }
}
