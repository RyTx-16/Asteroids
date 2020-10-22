package utilities;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import java.io.File;

/*
    Class that loads all of the sound files for the project.
    It uses pre-defined paths and file names.
 */
public class Sounds {
    static int nBullet = 0;
    static boolean thrusting = false;
    final static String path = "sounds/";

    public final static Clip[] bullets = new Clip[15];
    public final static Clip explosion = getClip("explosion");
    public final static Clip thrust = getClip("thrust");
    public final static Clip powerUp = getClip("powerup");

    static {
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = getClip("fire");
    }

    public static void play(Clip clip) {
        clip.setFramePosition(0);
        clip.start();
    }

    private static Clip getClip(String filename) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream sample = AudioSystem.getAudioInputStream(new File(path
                    + filename + ".wav"));
            clip.open(sample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static void fire() {
        Clip clip = bullets[nBullet];
        clip.setFramePosition(0);
        clip.start();
        nBullet = (nBullet + 1) % bullets.length;
    }
    public static void startThrust() {
        if (!thrusting) {
            thrust.loop(-1);
            thrusting = true;
        }
    }
    public static void stopThrust() {
        thrust.loop(0);
        thrusting = false;
    }

    public static void asteroids() {
        play(explosion);
    }
    public static void powerUp(){play(powerUp);}

}