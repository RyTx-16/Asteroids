package utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/*
    Class is used to define all of the Keys which the game will be using:
    • UP
    • Down
    • Left
    • Right

    Only allows for space key to shoot every .5 seconds.
 */
public class Keys extends KeyAdapter implements Controller {
    Action action;

    Timer t = new Timer();
    TimerTask tt;

    public Keys() {
        action = new Action();
    }

    public Action action() {
        return action;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 1;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = -1;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = +1;
                break;
            case KeyEvent.VK_SPACE:
                if(tt != null){
                    return;
                }
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        action.shoot = true;
                    }
                };
                t.scheduleAtFixedRate(tt,0,500);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 0;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = 0;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                tt.cancel();
                tt = null;
                action.shoot = false;
                break;
        }
    }
}