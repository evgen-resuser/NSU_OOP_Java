package game2048;

import javax.swing.*;
import java.awt.*;

//public class TileData {
//}

public class Tile extends JPanel {
    private int num_;
    JLabel label;

    public Tile(int number){
        num_ = number;
        this.setBackground(Color.lightGray);
        label = new JLabel();
        label.setBounds(50, 50, 20, 20);
        label.setText(" ");
        this.add(label);
    }

    public void change(){
        num_ += num_;
        label.setText(Integer.toString(num_));
        this.setBackground(Color.BLUE);
        this.repaint();
    }

    public int getNum() {
        return num_;
    }
}