package game.view.tiles;

import javax.swing.*;
import java.awt.*;

public class TileColors extends JPanel implements Tile{

    private JLabel label = null;


    public TileColors(){
        initTile();
    }

    public void initTile(){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.lightGray);

        label  = new JLabel();
        label.setText(" ");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setVerticalAlignment(SwingConstants.CENTER);

        this.add(label);
    }

    public void change(int newNum, String value){

        this.setBackground(new Color(Integer.parseInt(value, 16)));
        if (newNum == 0) label.setText(" ");
        else label.setText(Integer.toString(newNum));

        this.repaint();
    }
}
