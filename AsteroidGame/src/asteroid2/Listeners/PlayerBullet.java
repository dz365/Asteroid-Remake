package asteroid2.Listeners;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Bullet;
import asteroid2.GameObjects.Player;
import asteroid2.Panels.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PlayerBullet implements MouseListener {

    private final Asteroid2Game game;
    private final GamePanel panel;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Bullet> bombs;
    private final Player player;

    public PlayerBullet(Asteroid2Game game, GamePanel panel, Player player, ArrayList<Bullet> bullets, ArrayList<Bullet> bombs) {
        this.game = game;
        this.panel = panel;
        this.player = player;
        this.bullets = bullets;
        this.bombs = bombs;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            if (bullets.size() < panel.getBulletCapacity()) {
                bullets.add(new Bullet(game, "Player", (int) (player.getX() + 25 * Math.sin(player.getOrientation())), (int) (player.getY() - 25 * Math.cos(player.getOrientation())), player.getOrientation(), 10));
            }
        } else if (me.getButton() == MouseEvent.BUTTON3) {
            if (player.getBomb() > 0) {
                bombs.add(new Bullet(game, "Bomb", player.getX() + (int) (25 * Math.sin(player.getOrientation())), player.getY() - (int) (25 * Math.cos(player.getOrientation())), player.getOrientation(), 10));
                player.setBomb(player.getBomb() - 1);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
