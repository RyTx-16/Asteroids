package game1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;

public class View extends JComponent {
    private Game game;
    Image image = Constants.SPACE;
    AffineTransform bgTransf;
    public static String scores;

    public View(Game game) {
        this.game = game;
        double imageWidth = image.getWidth(null);
        double imageHeight = image.getHeight(null);
        double stretchX = (imageWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imageWidth);
        double stretchY = (imageHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imageHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchX, stretchY);
    }

    /*
        Method to paint all of the objects into the frame and defines all of the sizes.
     */
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image,bgTransf,null);

        synchronized (Game.class){
            for (GameObject a : game.objects) {
                a.draw(g);
            }
        }

        g.setFont(new Font("Calibri", Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString("Score: " + Integer.toString(game.getScore()),40,30);
        g.drawString("Lives Remaining: " + Integer.toString(game.ship.getLives()),560,30);

        if(game.ship.getLives() <= 0) {
            g.setFont(new Font("Calibri", Font.BOLD, 40));
            g.drawString("Game Over", 265, 450);
            g.drawString("You Scored: " + game.getScore(), 250, 500);
            g.drawString("Thank you for Playing!", 185, 650);
            int score = game.getScore();
            scores = String.valueOf(score);
        }

        try(FileWriter fw = new FileWriter("HighScores.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}