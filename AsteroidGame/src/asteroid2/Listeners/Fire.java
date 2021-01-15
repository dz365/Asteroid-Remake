package asteroid2.Listeners;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Aliens;
import asteroid2.GameObjects.Bullet;
import asteroid2.GameObjects.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Fire implements ActionListener {

    private final Asteroid2Game game;
    private final Aliens alien;
    private final ArrayList<Bullet> bullets;
    private final Player player;

    public Fire(Asteroid2Game game, Aliens alien, Player player, ArrayList<Bullet> bullets) {
        this.game = game;
        this.alien = alien;
        this.bullets = bullets;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        bullets.add(new Bullet(game, "Alien", alien.getX(), alien.getY(), Math.atan2(player.getY() - alien.getY(), player.getX() - alien.getX()) + Math.PI / 2, 5));
    }
}
