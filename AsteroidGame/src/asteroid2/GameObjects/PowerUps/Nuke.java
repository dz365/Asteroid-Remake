package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Nuke extends PowerUps {

    public Nuke(Asteroid2Game game, int x, int y) {
        super(game, x, y, 5);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y + 10, 30, 30);
        g2.fillArc(x, y, 30, 20, 0, 180);
        g2.setColor(Color.BLACK);
        g2.drawLine(x + 15, y + 10, x + 15, y + 30);
        g2.drawLine(x + 10, y + 15, x + 20, y + 15);
    }

    @Override
    public void update(GamePanel panel) {
    }
}
