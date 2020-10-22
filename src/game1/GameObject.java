package game1;

import utilities.Vector2D;
import java.awt.*;
import static game1.Constants.DT;
import static game1.Constants.FRAME_WIDTH;
import static game1.Constants.FRAME_HEIGHT;

public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public double radius;
    boolean change = false;

    public GameObject(Vector2D pos, Vector2D vel, double rad) {
        this.position = pos;
        this.velocity = vel;
        this.radius = rad;
        dead = false;
    }

    public void update() {
        position.addScaled(velocity, DT);
        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }
    public abstract void draw(Graphics2D g);

    public void hit() {
        dead = true;
    }

    public boolean overlap(GameObject other) {
        if (this.position.x + this.radius + other.radius > other.position.x &&
                this.position.x < other.position.x + this.radius + other.radius &&
                this.position.y + this.radius + other.radius > other.position.y &&
                this.position.y < other.position.y + this.radius + other.radius) {
            return true;
        }
        return false;
    }

    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }
    }
}