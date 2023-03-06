package game.view;

import game.model.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameOverWindow implements ActionListener {

    private final JWindow window;

    private final Facade facade;

    GameOverWindow(Facade facade){
        this.facade = facade;

        facade.blockInput(true);

        window = new JWindow();

        JLabel label1 = new JLabel("Game Over!");
        JLabel label2 = new JLabel( "You score: "+this.facade.getScore()+"\n");

        JButton button = new JButton("Retry");
        button.addActionListener(this);

        JPanel panel = new JPanel();

        label1.setFont(new Font("Arial", Font.BOLD, 36));
        label2.setFont(new Font("Arial", Font.PLAIN, 30));
        label1.setVerticalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label1);
        panel.add(label2);
        panel.add(button);

        window.add(panel);
        window.setSize(230, 125);
        window.setLocation(155, 300);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        facade.blockInput(false);
        facade.sendKey('R');
        window.setVisible(false);
    }
}
