package utilities;

import javax.swing.*;
import java.awt.*;

/*
    Class to create the application for the game.
    Different values have been added to ensure the best viewing experience for the application.
 */
public class JEasyFrame extends JFrame {

    public Component comp;

    public JEasyFrame(Component comp, String title) {
        super(title);
        this.comp = comp;
        getContentPane().add(BorderLayout.CENTER, comp);
        pack();

        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }
}


