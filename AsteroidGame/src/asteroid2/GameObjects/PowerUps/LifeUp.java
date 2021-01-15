package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class LifeUp extends PowerUps {

    public LifeUp(Asteroid2Game game, int x, int y) {
        super(game, x, y, 0);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.setColor(Color.WHITE);
        g2.fillPolygon(xpoints, ypoints, 4);
        g2.drawLine(x + 5, y, x + 5, y - 5);
        g2.drawLine(x + 35, y, x + 35, y - 5);
        g2.drawLine(x + 5, y - 5, x + 35, y - 5);
        g2.setColor(Color.RED);
        g2.fillRect(x + 15, y + 5, 10, 30);
        g2.fillRect(x + 5, y + 15, 30, 10);
    }

    @Override
    public void update(GamePanel panel) {
    }
}
