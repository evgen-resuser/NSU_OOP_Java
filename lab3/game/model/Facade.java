package game.model;

import game.observer.IObject;

public class Facade {
    private final Model core;
    private final int size;

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

    public void setGridSize(int size){
        core.size = size;
    }

    public void sendKey(char key){
        core.pressedKey = key;
    }

    public int[] getLastGenerated(){
        return core.lastGenerated;
    }
}
