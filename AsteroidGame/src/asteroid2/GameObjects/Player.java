package asteroid2.GameObjects;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends GameObject {

    private int lives;
    private int shield = 0;
    private boolean thrust;
    private int bomb;
    private boolean hyperspace;
    private double oldOrientation;
    private int nuke;

    public Player(Asteroid2Game game, int x, int y, double orientation, double velocity, int lives, int shield) {
        super(game, x, y, orientation, velocity);
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getNuke() {
        return nuke;
    }

    public void setNuke(int nuke) {
        this.nuke = nuke;
    }

    public boolean getThrust() {
        return thrust;
    }

    public void setThrust(Boolean thrust) {
        this.thrust = thrust;
    }

    public int getBomb() {
        return bomb;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }

    public void hyperSpace(boolean hyperspace) {
        this.hyperspace = hyperspace;
    }

    public boolean getHyperSpace() {
        return hyperspace;
    }

    public void setOldOrientation(double orientation) {
        this.oldOrientation = orientation;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        int[] xCoordinates = {x, x - 10, x - 3, x + 3, x + 10};
        int[] yCoordinates = {y, y + 30, y + 20, y + 20, y + 30};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();

        g2.rotate(orientation, x, y + 10);
        g2.drawPolygon(xpoints, ypoints, 5);

        if (getThrust() == true) {
            g2.setColor(Color.RED);
            g2.drawLine(getX() - 5, getY() + 32, getX(), getY() + 42);
            g2.drawLine(getX() + 5, getY() + 32, getX(), getY() + 42);
        }

        if (getShield() > 0) {
            g2.setColor(Color.CYAN);
            g2.drawOval(getX() - 25, getY() - 10, 50, 50);
        }
    }

    @Override
    public void update(GamePanel panel) {
        if (getHyperSpace() == true) {
            setVelocity(20);
        }

        if (getHyperSpace() == false) {
            setVelocity(getVelocity() * 0.98);
        }

        if (getVelocity() > 10 && getHyperSpace() == false) {
            setVelocity(10);
        }

        setX((int) (getX() + Math.sin(oldOrientation) * getVelocity()));
        setY((int) (getY() - Math.cos(oldOrientation) * getVelocity()));

        if (getY() < 0) {
            setY(game.getHeight());
        }
        if (getY() > game.getHeight()) {
            setY(0);
        }
        if (getX() < 0) {
            setX(game.getWidth());
        }
        if (getX() > game.getWidth()) {
            setX(0);
        }
    }
}
