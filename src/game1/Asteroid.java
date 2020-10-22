package game1;

import utilities.Sounds;
import utilities.Vector2D;

import java.awt.*;

public class Asteroid extends GameObject {
    public static final double MAX_SPEED = 90;
    Sprite im = new Sprite(Sprite.ASTEROID,this.position,new Vector2D(100,800),radius*2,radius*2);

    public Asteroid(Vector2D pos, Vector2D vel, double rad)
    {
        super(pos, vel, rad);
    }

    public static Asteroid makeRandomAsteroid() {
        Asteroid a = new Asteroid(
                new Vector2D(Math.random() * Constants.FRAME_WIDTH, Math.random() * Constants.FRAME_HEIGHT),
                new Vector2D(Math.random() * MAX_SPEED, Math.random() * MAX_SPEED),30);
        return a;
    }
    // Static method that is used to generate another asteroid at the other game objects position
    public static Asteroid makeAsteroid(GameObject other){
        Asteroid a = new Asteroid(new Vector2D(other.position), new Vector2D(other.velocity), other.radius);
        // Give the velocity vector random values to they go in two different directions
        a.velocity.x = Math.random() * MAX_SPEED;
        a.velocity.y = Math.random() * MAX_SPEED;
        a.radius = other.radius -10;
        return a;
    }
    // hit method
    public void hit(){
        Sounds.asteroids();
        dead = true;
        change = true;
    }

    public void draw(Graphics2D g) {
        im.draw(g);
    }

    public boolean overlap(GameObject other) {
        if(this instanceof Asteroid && other instanceof Ship) {
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