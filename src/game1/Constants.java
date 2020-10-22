package game1;

import utilities.Images;
import java.awt.*;
import java.io.IOException;

public class Constants {
    public static final int FRAME_HEIGHT = 720;
    public static final int FRAME_WIDTH = 720;
    public static final Dimension FRAME_SIZE = new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    public static final int DELAY = 20;
    public static final double DT = DELAY / 1000.0;

    public static Image ASTEROID, SPACE;
    static {
        try {
            SPACE = Images.loadImage("space");
            ASTEROID = Images.loadImage("asteroid");
        } catch (IOException e) { e.printStackTrace(); }
    }
}

