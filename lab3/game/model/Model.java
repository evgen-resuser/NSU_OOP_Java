package game.model;

import game.Message;
import game.observer.IObject;
import game.observer.IObserver;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

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

    boolean[][] occupiedCells;

    public Model(int size){
        this.size = size;

        score = 0;
        nums = new int[size][size];
        occupiedCells = new boolean[size][size];

        generateCell();

        msg = Message.UPDATE;

    }

    //WARNING!

    private void numsPermutations(){

        System.out.println("Before moving: ");
        printArr();

        int[] tmp;
        boolean[] tmpB = new boolean[size];

        switch (pressedKey) {
            case 'R' -> {
                for (int i = 0; i != size; i++) {
                    Arrays.fill(occupiedCells[i], false);
                    collapseRight(nums[i], occupiedCells[i]);
                }
            }
            case 'L' -> {
                for (int i = 0; i != size; i++) {
                    Arrays.fill(occupiedCells[i], false);
                    collapseLeft(nums[i], occupiedCells[i]);
                }
            }
            case 'D' -> {
                for (int i = 0; i != size; i++) {
                    tmp = generateCol(i);
                    collapseRight(tmp, tmpB);
                    insertNewCol(tmp, tmpB, i);
                }
            }
            case 'U' -> {
                for (int i = 0; i != size; i++) {
                    tmp = generateCol(i);
                    collapseLeft(tmp, tmpB);
                    insertNewCol(tmp, tmpB, i);
                }
            }
        }

        System.out.println(score);

        if (!generateCell()) msg = Message.GAME_OVER;
        else msg = Message.UPDATE;

        notifyObserver();
    }

    private final Random random = new Random();

    private boolean generateCell(){

        int[] pos = new int[2];
        int counter = 0;

        if (counter == size*size) return false;

        do {
            counter++;
            pos[0] = random.nextInt(4);
            pos[1] = random.nextInt(4);
            if (counter == size*size) return false;
        } while (occupiedCells[pos[0]][pos[1]]);
        occupiedCells[pos[0]][pos[1]] = true;

        int chance = random.nextInt(10);
        if (chance < 9) nums[pos[0]][pos[1]] = 2;
        else nums[pos[0]][pos[1]] = 4;

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

    public void insertNewCol(int[] tmp, boolean[] tmpB, int col){
        for (int i = 0; i != size; i++){
            nums[i][col] = tmp [i];
            occupiedCells[i][col] = tmpB[i];
        }
    }

    private void collapseLeft(int[] arr, boolean[] tmpB){
        int temp = 0;
        boolean wasAdded = false;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i != size; i++){
            if (arr[i] == 0) continue;

            if (!stack.isEmpty()) temp = stack.peek();

            if (temp == arr[i] && !wasAdded){
                stack.pop();
                stack.push(arr[i] + temp);
                score += arr[i] + temp;
                arr[i] = 0;
                wasAdded = true;

            } else {
                wasAdded = false;
                stack.push(arr[i]);
                arr[i] = 0;
            }
        }

        int shift = stack.size()-1;
        for (int i = shift; i != -1 ; i--) {
            arr[i] = stack.pop();
            tmpB[i] = true;
        }

    }

    private void collapseRight(int[] arr, boolean[] tmpB){
        int temp = 0;
        boolean wasAdded = false;
        Stack<Integer> stack = new Stack<>();

        for (int i = size-1; i != -1; i--){
            if (arr[i] == 0) continue;

            if (!stack.isEmpty()) temp = stack.peek();

            if (temp == arr[i] && !wasAdded){
                stack.pop();
                stack.push(arr[i] + temp);
                score += arr[i] + temp;
                arr[i] = 0;
                wasAdded = true;
            } else {
                wasAdded = false;
                stack.push(arr[i]);
                arr[i] = 0;
            }
        }

        int shift = stack.size();
        for (int i = size-shift; i != size; i++) {
            arr[i] = stack.pop();
            tmpB[i] = true;
        }

    }

}
