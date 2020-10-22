package game1;

import utilities.Vector2D;
import java.awt.*;

public class Bullet extends GameObject {

    protected long created;
    protected long death;
    public boolean giveScore = false;

    public Bullet(Vector2D pos, Vector2D vel, double rad) {
        super(pos, vel, rad);
        created = System.currentTimeMillis();
        death = created + 1250;
    }

    /*
        Method to delete bullet after a certain amount of time ha passed.
        If bullet dies before time period add score.
     */
    public void update() {
        super.update();
        if (System.currentTimeMillis() >= death) {
            dead = true;
        }
        if(System.currentTimeMillis() < death && dead){
            giveScore = true;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval((int) position.x-8  - (int) radius, (int) position.y-8 - (int) radius, 2* (int) radius, 2* (int) radius);
    }

    public boolean overlap(GameObject other) {
        if(this instanceof Bullet && other instanceof Ship){
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