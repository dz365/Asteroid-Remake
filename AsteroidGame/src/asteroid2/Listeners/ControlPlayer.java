package asteroid2.Listeners;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Aliens;
import asteroid2.GameObjects.Bullet;
import asteroid2.GameObjects.Player;
import asteroid2.Panels.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ControlPlayer implements KeyListener {

    private final Asteroid2Game game;
    private final GamePanel panel;
    private final Player player;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Bullet> bomb;

    private final Set<Integer> pressed = new HashSet<>();

    public ControlPlayer(Asteroid2Game game, GamePanel panel, Player player, ArrayList<Bullet> bullets, ArrayList<Bullet> bomb) {
        this.game = game;
        this.panel = panel;
        this.player = player;
        this.bullets = bullets;
        this.bomb = bomb;
    }

    public void keyPressed(KeyEvent event) {
        pressed.add(event.getKeyCode());

        for (Integer i : pressed) {
            switch (i) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    player.setThrust(true);
                    if (player.getHyperSpace() == false) {
                        player.setVelocity(player.getVelocity() + 1);
                    }
                    player.setX((int) (player.getX() + Math.sin(player.getOrientation()) * player.getVelocity()));
                    player.setY((int) (player.getY() - Math.cos(player.getOrientation()) * player.getVelocity()));
                    player.setOldOrientation(player.getOrientation());
                    if (panel.canPlaySound() == true) {
                        panel.playSound(0);
                        panel.setPlaySound(false);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    player.setThrust(true);
                    player.setOrientation(player.getOrientation() + Math.toRadians(15));
                    if (panel.canPlaySound() == true) {
                        panel.playSound(0);
                        panel.setPlaySound(false);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    player.setThrust(true);
                    player.setOrientation(player.getOrientation() - Math.toRadians(15));
                    if (panel.canPlaySound() == true) {
                        panel.playSound(0);
                        panel.setPlaySound(false);
                    }
                    break;
                case KeyEvent.VK_B:
                    if (player.getBomb() > 0) {
                        bomb.add(new Bullet(game, "Bomb", player.getX() + (int) (25 * Math.sin(player.getOrientation())), player.getY() - (int) (25 * Math.cos(player.getOrientation())), player.getOrientation(), 10));
                        player.setBomb(player.getBomb() - 1);
                    }
                    break;
                case KeyEvent.VK_N:
                    if (player.getNuke() > 0) {
                        panel.asteroids().clear();
                        panel.bullets().clear();
                        panel.alienBullets().clear();
                        for (Aliens a : panel.aliens()) {
                            a.hit();
                        }
                        panel.aliens().clear();
                        player.setNuke(player.getNuke() - 1);
                        game.setScore(game.getScore() + 1000);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (bullets.size() < panel.getBulletCapacity()) {
                        bullets.add(new Bullet(game, "Player", player.getX() + (int) (25 * Math.sin(player.getOrientation())), player.getY() - (int) (25 * Math.cos(player.getOrientation())), player.getOrientation(), 10));
                    }
                    if (panel.canPlaySound() == true) {
                        panel.playSound(1);
                        panel.setPlaySound(false);
                    }
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
        player.setThrust(false);
        panel.setPlaySound(true);
        if (panel.clip() != null) {
            panel.clip().stop();
        }
    }
}
