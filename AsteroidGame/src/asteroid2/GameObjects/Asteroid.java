package asteroid2.GameObjects;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Asteroid extends GameObject {

    private int size;

    public Asteroid(Asteroid2Game game, int x, int y, double orientation, double velocity, int size) {
        super(game, x, y, orientation, velocity);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public double getVelocity() {
        return velocity;
    }
    
    @Override
    public void paintComponent(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        int[] xCoordinates = new int[6];
        int[] yCoordinates = new int[6];

        for (int i = 0; i < 6; i++) {
            xCoordinates[i] = (int) (getX() + size * 10 * Math.cos(i * 2 * Math.PI / 6));
            yCoordinates[i] = (int) (getY() + size * 10 * Math.sin(i * 2 * Math.PI / 6));
        }

        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.drawPolygon(xpoints, ypoints, 6);
    }

    @Override
    public void update(GamePanel panel) {
        this.setX((int) (getX() + Math.cos(getOrientation()) * getVelocity()));
        this.setY((int) (getY() + Math.sin(getOrientation()) * getVelocity()));

        if (getX() < 0) {
            setX(game.getWidth());
        }
        if (getX() > game.getWidth()) {
            setX(0);
        }
        if (getY() < 0) {
            setY(game.getHeight());
        }
        if (getY() > game.getHeight()) {
            setY(0);
        }
    }
}
