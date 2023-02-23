package game2048;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Window extends JFrame{
    Tile[][] tileArr;
    JPanel grid;
    GridLayout layout;

    public Window(){

        ImageIcon icon = new ImageIcon("src/game2048/logo.png");
        this.setIconImage(icon.getImage());

        initTileArr();

        this.setBackground(Color.WHITE);
        initGrid();

        Core core = new Core();
        this.addKeyListener(core.getControls());

        core.start();
    }

    private void initGrid(){
        grid = new JPanel();
        layout = new GridLayout(4,4, 5, 5);
        grid.setLayout(layout);

        for (int i = 0; i != 4; i++){
            for (int j = 0; j != 4; j++)
                grid.add(tileArr[j][i]);
        }

        this.add(grid);
        this.pack();
    }

    private void initTileArr(){
        tileArr = new Tile[4][4];
        for (int i = 0; i != 4; i++){
            for (int j = 0; j != 4; j++) {
                tileArr[j][i] = new Tile(0);
            }
        }
    }
}
