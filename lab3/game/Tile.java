package game;

import javax.swing.*;
import java.awt.*;

class Tile extends JPanel {
    private int x;
    private int y;
    private int number;
    private JLabel label = new JLabel();

    public Tile(int x, int y){
        this.x = x;
        this.y = y;

        number = 0;
        label.setText(" ");
        label.setBounds(50, 50, 20, 20);

        this.setBackground(Color.lightGray);
        this.add(label);
    }

    public void change(int newNum){
        this.number = newNum;
        label.setText(Integer.toString(newNum));
        this.repaint();
    }



}
