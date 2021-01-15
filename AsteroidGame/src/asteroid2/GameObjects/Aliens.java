package asteroid2.GameObjects;

import asteroid2.Asteroid2Game;
import asteroid2.Listeners.Fire;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.Timer;

public class Aliens extends GameObject {

    private boolean dead = false;
    private Timer timer;

    public Aliens(Asteroid2Game game, Player player, int x, int y, double orientation, double velocity, ArrayList<Bullet> bullets) {
        super(game, x, y, orientation, velocity);
        timer = new Timer(1000, new Fire(game, this, player, bullets));
        timer.start();
    }

    public void hit() {
        timer.stop();
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        int[] xCoordinates = {x - 15, x, x + 20, x + 35, x + 20, x, x - 15, x + 35, x + 20, x + 20, x + 15, x + 5, x, x};
        int[] yCoordinates = {y + 7, y, y, y + 7, y + 13, y + 13, y + 7, y + 7, y, y - 5, y - 7, y - 7, y - 5, y};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        g2.drawPolygon(xpoints, ypoints, 14);
        setBounds();
    }

    @Override
    public void update(GamePanel panel) {
        setX(getX() + (int) getVelocity());

        if (this.getX() > game.getWidth()) {
            setX(0);
        }
    }
}
