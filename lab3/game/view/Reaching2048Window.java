package game.view;

import game.model.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reaching2048Window implements ActionListener {

    private final Facade facade;
    private final JWindow window;

    JButton button1;

    Reaching2048Window(Facade facade){

        this.facade = facade;
        facade.blockInput(true);

        window = new JWindow();

        JLabel label1 = new JLabel("You won by getting 2048!");
        JLabel label2 = new JLabel( "Do you wish to continue playing?");

        button1 = new JButton("Continue");
        button1.addActionListener(this);

        JPanel panel = new JPanel();

        label1.setFont(new Font("Arial", Font.BOLD, 36));
        label2.setFont(new Font("Arial", Font.PLAIN, 30));
        label1.setVerticalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label1);
        panel.add(label2);
        panel.add(button1);

        window.add(panel);
        window.setSize(500, 125);
        window.setLocation(20, 300);

        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        facade.blockInput(false);
        window.setVisible(false);

    }
}
