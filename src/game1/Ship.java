package game1;

import utilities.Controller;
import utilities.Sounds;
import utilities.Vector2D;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static game1.Constants.DT;

public class Ship extends GameObject {
    public static final double STEER_RATE = 4 * (180 / Math.PI) * DT;
    public static final double MAG_ACC = 500;
    public static final double MAG_BUL = 400;
    public static final double DRAG = 0.01;
    public static final Color COLOR = Color.WHITE;
    Color c = new Color(0f,0f,0f,0f );

    public int lives = 5;
    public Vector2D direction;
    private Controller ctrl;

    public Bullet bullet = null;
    private int[] playerX = new int[]{0,25,0,-25};
    private int[] playerY = new int[]{-45,25,15,25};
    private int[] thrustX = new int[]{-20,0,20};
    private int[] thrustY = new int[]{70,150, 70};
    private double DRAWING_SCALE = 0.5;

    public Ship(Vector2D position, Vector2D velocity, double radius, Controller ctrl) {
        super(position, velocity, radius);
        this.ctrl = ctrl;
        this.direction = new Vector2D(1, 0);
    }

    public void update() {
        super.update();
        direction.rotate((STEER_RATE * ctrl.action().turn) * DT);
        velocity.addScaled(direction, (MAG_ACC * DT) * ctrl.action().thrust);
        velocity.mult(1 - DRAG);

        if(ctrl.action().thrust==1)Sounds.startThrust();
        else Sounds.stopThrust();

        if (ctrl.action().shoot) {
            this.mkBullet();
            ctrl.action().shoot = false;
        }
    }

    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.setColor(COLOR);
        g.setStroke(new BasicStroke(5));
        g.drawPolygon(playerX, playerY, playerX.length);
        g.setColor(c);
        g.fillPolygon(playerX, playerY, playerX.length);

        if (ctrl.action().thrust == 1) {
            g.scale(0.4, 0.4);
            g.setColor(Color.ORANGE);
            g.setStroke(new BasicStroke(20));
            g.drawPolygon(thrustX, thrustY, 3);
            g.setColor(Color.red);
            g.fillPolygon(thrustX, thrustY, 3);
        }
        g.setTransform(at);
    }

    /*
        Method to create the bullet.
     */
    private void mkBullet() {
        bullet = new Bullet(new Vector2D(position), new Vector2D(velocity), 3);
        bullet.position.add(radius, radius);
        bullet.velocity.addScaled(direction, (MAG_BUL));
        Sounds.fire();
    }

    /*
        Method that controls what happens when the player collides with an asteroid.
     */
    public void hit(){
        if(lives > 0){
            lives --;
            position = new Vector2D(400,400);
            velocity = new Vector2D();
        }
        if(lives <=0){
            dead = true;
        }
    }

    public int getLives(){
        return lives;
    }

    /*
        Method to ensure collision parameters, ignoring however the contact between the ship and bullets.
     */
    public boolean overlap(GameObject other) {
        // the collision between the ship and the bullet is ignored.
        if(this instanceof Ship && other instanceof Bullet){
            return false;
        }
        if (this.position.x + this.radius + other.radius > other.position.x &&
                this.position.x < other.position.x + this.radius + other.radius &&
                this.position.y + this.radius + other.radius > other.position.y &&
                this.position.y < other.position.y + this.radius + other.radius) {
            return true;
        }
        return false;
    }
}