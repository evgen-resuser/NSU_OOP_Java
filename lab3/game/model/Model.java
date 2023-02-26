package game.model;

import game.Message;
import game.observer.IObject;
import game.observer.IObserver;

class Model extends Thread implements IObject {

    //####################### OBSERVER PART #######################

    IObserver observer;
    private Message msg;

    @Override
    public void registerObserver(IObserver observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        observer.update(msg);
    }

    //#############################################################

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

    //#############################################################

    char pressedKey = ' ';

    int size;
    int score;
    int[][] nums;

    LineCollider collider;

    public Model(int size){
        this.size = size;
        nums = new int[size][size];
        score = 0;
        msg = Message.UPDATE;
        collider = new LineCollider(nums);
    }

    private void numsPermutations(){
        nums = collider.test;
        notifyObserver();
    }


}
