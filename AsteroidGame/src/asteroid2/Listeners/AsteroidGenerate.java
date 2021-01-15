package asteroid2.Listeners;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Asteroid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidGenerate implements ActionListener {

    Asteroid2Game game;
    ArrayList<Asteroid> asteroids;

    public AsteroidGenerate(Asteroid2Game game, ArrayList<Asteroid> asteroids) {
        this.game = game;
        this.asteroids = asteroids;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (new Random().nextInt(2) == 0) {
            asteroids.add(new Asteroid(game, new Random().nextInt(game.getWidth()), 0, Math.random(), new Random().nextInt(10) + 1, new Random().nextInt(3) + 1));
        } else {
            asteroids.add(new Asteroid(game, new Random().nextInt(game.getWidth()), game.getHeight() + 1, Math.random(), -new Random().nextInt(10) + 2, new Random().nextInt(3) + 1));
        }
    }
}
