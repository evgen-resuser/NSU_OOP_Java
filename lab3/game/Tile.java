package game;

import javax.swing.*;
import java.awt.*;

class Tile extends JPanel {

    private final JLabel label;

    public Tile(){

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.lightGray);

        label  = new JLabel();
        label.setText(" ");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setVerticalAlignment(SwingConstants.CENTER);

        this.add(label);
    }

    public void change(int newNum, int color){

        this.setBackground(new Color(color));
        if (newNum == 0) label.setText(" ");
        else label.setText(Integer.toString(newNum));

        this.repaint();
    }
}
