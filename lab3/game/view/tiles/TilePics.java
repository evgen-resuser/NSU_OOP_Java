package game.view.tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePics extends JPanel implements Tile{

    private JLabel label;
    private BufferedImage image;

    public TilePics(){
        initTile();
    }

    public void initTile() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.lightGray);

    }

    public void change(int newNum, String value) {
        try {
            if (newNum >= 4096)
                image = ImageIO.read(new File("src/game/sprites/4096.png"));
            else
                image = ImageIO.read(new File("src/game/sprites/" + newNum + ".png"));

            this.repaint();
        } catch (IllegalArgumentException | IOException e){
            System.out.println("File "+newNum+".png not found!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
