package game.model;

import game.Controls;
import game.Window;
import game.model.Model;
import game.observer.IObject;

public class Facade {
    private Model core = new Model();

    public Facade(){
        //core.setSize(4);
        core.start();
    }

    public int[][] getNums(){
        return core.nums;
    }

    public IObject getObject(){
        return core;
    }

    public void setGridSize(int size){
        core.size = size;
    }

    public void sendKey(char key){
        core.pressedKey = key;
    }
}
