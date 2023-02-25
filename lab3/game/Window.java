package game;

import game.model.Facade;
import game.model.Message;
import game.observer.IObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Window extends JFrame implements IObserver{
    Facade facade;
    private Tile[][] tiles;
    private final int size;

    JPanel grid;
    GridLayout layout;

    JPanel panel;

    char direction = ' ';

    public void setDirection(char direction) {
        this.direction = direction;
        panel.repaint();
    }

    public Window(Facade facade){
        size = 4;
        this.facade = facade;
        this.facade.setGridSize(size);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGrid();

        facade.getObject().registerObserver(this);

    }

    public int getGridSize() {
        return size;
    }

    private void initGrid(){
        tiles = new Tile[size][size];
        grid = new JPanel();
        grid.setSize(520,520);
        layout = new GridLayout(size,size, 5, 5);
        grid.setLayout(layout);

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                tiles[j][i] = new Tile(i, j);
                grid.add(tiles[j][i]);
            }
        }

        this.add(grid);
    }

    @Override
    public void update(Message msg) {
        switch (msg){
            case UPDATE -> {
                int[][] newGrid = facade.getNums();
                System.out.println(newGrid[0][0]);
            }
            case GAME_OVER -> {
                System.out.println("Game Over!");
            }
        }

    }
}
