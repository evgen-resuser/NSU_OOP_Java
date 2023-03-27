package game.model;

import game.Message;
import game.observer.IObject;
import game.observer.IObserver;

import java.util.Arrays;
import java.util.Random;

class Model extends Thread implements IObject {

    //####################### OBSERVER PART #######################

    IObserver observer;
    private Message msg;

    @Override
    public void registerObserver(IObserver observer) {
        this.observer = observer;
        processing.observer = observer;
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
                gridPermutations();
                pressedKey = ' ';
            }
        }
    }

    //#############################################################

    char pressedKey = ' ';

    int size;
    int score;
    int[][] nums;

    boolean[][] occupiedCells;
    int occCount;

    LineProcessing processing;

    public Model(int size){
        this.size = size;

        processing = new LineProcessing(size);

        startGame();

        occCount = 1;

        generateCell();

    }

    public void startGame(){
        isPlaying = true;
        score = 0;
        nums = new int[size][size];
        occupiedCells = new boolean[size][size];
        occCount = 0;

        //nums[0][0] = nums[0][1] = 2048;

        msg = Message.UPDATE;
    }

    //WARNING! SCARY CODE!

    boolean isPlaying;

    private void gridPermutations(){

        doPressedKey();

        try {
            sleep(20);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
            currentThread().interrupt();
        }

        if (!generateCell()) {
            System.out.println("The last board:");
            printArr();
            isPlaying = false;
            msg = Message.GAME_OVER;
        }
        else msg = Message.UPDATE;

        System.out.println("Score: "+score+", OccCount: "+occCount);
        printArr();

        notifyObserver();
    }

    private void doPressedKey(){

        if (!isPlaying) {
            startGame();
            notifyObserver();
            return;
        }

        int[] tmp;
        boolean[] tmpB = new boolean[size];
        occCount = 0;

        switch (pressedKey) {
            case 'R' -> {
                for (int i = 0; i != size; i++) {
                    Arrays.fill(occupiedCells[i], false);
                    score += processing.collapseRight(nums[i], occupiedCells[i]);
                    occCount += processing.getOccCount();
                }
            }
            case 'L' -> {
                for (int i = 0; i != size; i++) {
                    Arrays.fill(occupiedCells[i], false);
                    score += processing.collapseLeft(nums[i], occupiedCells[i]);
                    occCount += processing.getOccCount();
                }
            }
            case 'D' -> {
                for (int i = 0; i != size; i++) {
                    tmp = generateCol(i);
                    score += processing.collapseRight(tmp, tmpB);
                    insertNewCol(tmp, i);
                    occCount += processing.getOccCount();
                }
            }
            case 'U' -> {
                for (int i = 0; i != size; i++) {
                    tmp = generateCol(i);
                    score += processing.collapseLeft(tmp, tmpB);
                    insertNewCol(tmp, i);
                    occCount += processing.getOccCount();
                }
            }
        }
    }

    private final Random random = new Random();
    int[] lastGenerated;

    private boolean generateCell(){

        int[] pos = new int[2];

        do {
            if (occCount == size*size) return false;
            pos[0] = random.nextInt(size);
            pos[1] = random.nextInt(size);

        } while (occupiedCells[pos[0]][pos[1]]);
        occupiedCells[pos[0]][pos[1]] = true;

        int chance = random.nextInt(10);
        if (chance < 9) nums[pos[0]][pos[1]] = 2;
        else nums[pos[0]][pos[1]] = 4;

        lastGenerated = pos;
        occCount++;

        return true;
    }

    private void printArr(){
        for (int i = 0; i != size; i++){
            System.out.println(Arrays.toString(nums[i])+"   "+Arrays.toString(occupiedCells[i]));
        }
        System.out.println("-------------------");
    }

    public int[] generateCol(int col){
        int[] tmp = new int[size];
        for (int i = 0; i != size; i++){
            tmp [i] = nums[i][col];
        }
        return tmp;
    }

    public void insertNewCol(int[] tmp, int col){
        for (int i = 0; i != size; i++){
            nums[i][col] = tmp [i];
            occupiedCells[i][col] = (tmp[i] != 0);
        }
    }

}
