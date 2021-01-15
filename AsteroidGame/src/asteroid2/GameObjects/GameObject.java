package asteroid2.GameObjects;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.*;
import java.awt.Graphics2D;

public abstract class GameObject extends Polygon {

    protected Asteroid2Game game;
    protected int x;
    protected int y;
    protected double orientation;
    protected double velocity;

    public GameObject(Asteroid2Game game, int x, int y, double orientation, double velocity) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.velocity = velocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXPoints(int[] x) {
        xpoints = x;
    }

    public void setYPoints(int[] y) {
        ypoints = y;
    }

    public void setBounds() {
        bounds = new Rectangle(x - getRange(xpoints) / 2, y, getRange(xpoints), getRange(ypoints));
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    private int getRange(int[] list) {
        int max = list[0];
        int min = list[0];

        for (int i = 0; i < list.length; i++) {
            max = Math.max(max, list[i]);
            min = Math.min(min, list[i]);
        }
        return max - min;
    }

    public abstract void paintComponent(Graphics2D g2);

    public abstract void update(GamePanel panel);
}
