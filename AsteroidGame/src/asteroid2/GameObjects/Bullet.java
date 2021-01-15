package asteroid2.GameObjects;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject {

    private String shooter;

    public Bullet(Asteroid2Game game, String shooter, int x, int y, double orientation, double velocity) {
        super(game, x, y, orientation, velocity);
        this.shooter = shooter;
    }

    public String getShooter() {
        return shooter;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        if (getShooter() == "Player") {
            g2.setColor(Color.WHITE);
        } else if (getShooter() == "Bomb") {
            g2.setColor(Color.GREEN);
        } else {
            g2.setColor(Color.MAGENTA);
        }

        int[] xCoordinates = {x, x + 5, x + 5, x};
        int[] yCoordinates = {y, y, y + 5, y + 5};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.fillPolygon(xpoints, ypoints, 4);
    }

    @Override
    public void update(GamePanel panel) {
        y -= (int) (Math.cos(orientation) * 10);
        x += (int) (Math.sin(orientation) * 10);
    }
}
