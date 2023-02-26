package game.model;

import game.Message;
import game.observer.IObject;
import game.observer.IObserver;

import java.util.Random;

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
                numsPermutations();
                pressedKey = ' ';
            }
        }
    }

    //#############################################################

    char pressedKey = ' ';

    int size;
    int score;
    int[][] nums;

    LineCollider collider;
    boolean[][] occupiedCells;
    int counter;

    public Model(int size){
        this.size = size;

        nums = new int[size][size];
        occupiedCells = new boolean[size][size];
        counter = 0;
//        nums = new int[][]{
//                {0, 2, 4, 8},
//                {16, 32, 64, 128},
//                {256, 512, 1024, 2048},
//                {5000, 0, 0, 0}
//        };

        generateCell();

        msg = Message.UPDATE;

        score = 0;

        collider = new LineCollider(nums);


    }

    private void numsPermutations(){

        if (!generateCell()) msg = Message.GAME_OVER;
        else msg = Message.UPDATE;

        notifyObserver();
    }

    private boolean generateCell(){
        Random random = new Random();
        int[] pos = new int[2];

        if (counter == size*size) return false;

        do {
            pos[0] = random.nextInt(4);
            pos[1] = random.nextInt(4);
        } while (occupiedCells[pos[0]][pos[1]]);
        occupiedCells[pos[0]][pos[1]] = true;
        counter++;

        int chance = random.nextInt(10);
        if (chance < 5) nums[pos[0]][pos[1]] = 2;
        else nums[pos[0]][pos[1]] = 4;

        return true;
    }

    public void generateTestField(){

        nums = new int[][]{
            {0, 2, 4, 8},
            {16, 32, 64, 128},
            {256, 512, 1024},
            {2048, 4096, 0, 0}
        };
        counter = 12;
        occupiedCells = new boolean[][]{
                {false, true, true, true},
                {true, true, true, true},
                {true, true, true},
                {true, true, false, false}
        };
    }

}
