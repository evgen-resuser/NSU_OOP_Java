package snake;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChunkData {
    Map<Integer, Color> map;
    int cellColor;
    Chunk chunk;

    public ChunkData(int color){
        //todo add color config
        //todo change integers to strings?
        map = new HashMap<>();
        map.put(2, Color.green);
        map.put(1, Color.red);
        map.put(0, Color.white);

        cellColor = color;
        chunk = new Chunk(map.get(color));
    }

    public void changeColor(int newColor){
        chunk.chColor(map.get(newColor));
    }
}

class Chunk extends JPanel {
    public Chunk(Color color){
        this.setBackground(color);
    }

    public void chColor(Color color){
        this.setBackground(color);
        this.repaint();
    }
}
