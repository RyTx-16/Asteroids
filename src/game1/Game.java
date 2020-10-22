package game1;

import utilities.JEasyFrame;
import utilities.Keys;
import utilities.Vector2D;
import java.util.*;

public class Game {
    public int asteroidToSpawnCount = 0;
    public List<GameObject> objects;
    public List<Asteroid> asteroidList;
    Keys ctrl;
    Ship ship;
    int score = 0;

    public Game() {
        objects = new ArrayList<>();
        asteroidList = new ArrayList<>();
        ctrl = new Keys();
        ship = new Ship(new Vector2D(350, 350), new Vector2D(), 10, ctrl);
        objects.add(ship);
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        View view = new View(game);
        new JEasyFrame(view, "CE218 Asteroid Game Project").addKeyListener(game.ctrl);
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    public void addScore(int score){
        this.score = this.score + score;
    }
    public int getScore(){
        return score;
    }

    public void update() {
        List<GameObject> Alive = new ArrayList<>(objects);
        if (ship.bullet != null){
            Alive.add(ship.bullet);
        }
        ship.bullet = null;

        checkGameState();
        for (GameObject a : objects) {
            a.update();
            if(a instanceof Bullet){
                if(((Bullet) a).giveScore == true){
                    addScore(50);
                }
            }
            if(a.change){
                a.change = false;
                if(a.radius>10) {
                    Alive.add(Asteroid.makeAsteroid(a));
                    Alive.add(Asteroid.makeAsteroid(a));
                    Alive.add(Asteroid.makeAsteroid(a));
                }
            }
            if (a.dead) {
                Alive.remove(a);
            }

            for (GameObject ab : objects) {
                if(a == ab){
                    continue;
                }
                a.collisionHandling(ab);
            }
        }

        synchronized (Game.class) {
            objects.clear();
            objects.addAll(Alive);
        }

        synchronized (Game.class){
            asteroidList.clear();
            for(GameObject a:Alive){
                if(a instanceof Asteroid){
                    asteroidList.add((Asteroid) a);
                }
            }
        }
    }

    private void checkGameState(){
        if (asteroidList.size() == 0){
            asteroidToSpawnCount ++;
            startNextLevel();
        }
    }

    private void startNextLevel(){
        for(int i =0;i<asteroidToSpawnCount;i++){
            objects.add(Asteroid.makeRandomAsteroid());
        }
    }
}