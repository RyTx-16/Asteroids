package utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/*
    Class that loads all of the image files for the project.
    It uses pre-defined paths and file names.
 */
public class Images {
    public final static String path = "images/";
    public final static String ext = ".png";

    public static Map<String, Image> images = new HashMap<String, Image>();

    public static Image loadImage(String fName) throws IOException {
        BufferedImage img;
        img = ImageIO.read(new File(path + fName + ext));
        images.put(fName, img);
        return img;
    }
}


