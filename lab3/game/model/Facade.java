package game.model;

import game.observer.IObject;

public class Facade {
    private final Model core;
    private final int size;
    private boolean block;

    public Facade(int size){
        this.size = size;
        core = new Model(size);
        core.start();
    }

    public int getSize() {
        return size;
    }

    public int[][] getNums(){
        return core.nums;
    }

    public IObject getObject(){
        return core;
    }

    public int getScore(){
        return core.score;
    }

    public void sendKey(char key){
        if (!block) core.pressedKey = key;
    }

    public int[] getLastGenerated(){
        return core.lastGenerated;
    }

    public void blockInput(boolean val){
        block = val;
    }
}
