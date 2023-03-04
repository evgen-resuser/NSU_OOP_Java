package game;

import game.model.Facade;
import game.observer.IObserver;
import game.theme.ThemeReader;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Window extends JFrame implements IObserver{
    private final Facade facade;
    private final int size;

    private Map<Integer, Integer> map;
    private Tile[][] tiles;

    JPanel grid;
    GridLayout layout;

    public Window(Facade facade){
        facade.getObject().registerObserver(this);

        size = facade.getSize();
        this.facade = facade;

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGrid();
        initColorMap();

        redrawGrid(facade.getNums());

        this.pack();
    }

    @Override
    public void update(Message msg) {
        switch (msg){
            case UPDATE ->
                redrawGrid(facade.getNums());

            case GAME_OVER -> {
                System.out.println("Game Over!");
                gameOverScreen();
            }
            case REACH_2048 -> {
                System.out.println("Reaching the 2048!");
                winScreen();
            }
        }
    }

    private void initGrid(){
        tiles = new Tile[size][size];
        grid = new JPanel();
        grid.setSize(520,520);
        layout = new GridLayout(size,size, 5, 5);
        grid.setLayout(layout);

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                tiles[j][i] = new Tile();
                grid.add(tiles[j][i]);
            }
        }

        this.add(grid);
    }

    private void redrawGrid(int[][] arr){
        int newColor;
        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                if (arr[i][j] > 2048) newColor = map.get(4096);
                else newColor = map.get(arr[i][j]);
                tiles[j][i].change(arr[i][j], newColor);
            }
        }
    }

    private void initColorMap(){
        ThemeReader tr = new ThemeReader();
        map = tr.getColorMap();
    }

    private void gameOverScreen(){

        grid.setVisible(false);

    }

    private void winScreen(){

        grid.setVisible(false);

    }
}
