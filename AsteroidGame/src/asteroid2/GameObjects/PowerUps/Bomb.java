package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.Timer;

public class Bomb extends PowerUps {

    public Bomb(Asteroid2Game game, int x, int y) {
        super(game, x, y, 3);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.setColor(Color.ORANGE);
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40};

        int[] xShipLeftFin = {x + 15, x + 10, x + 10, x + 15};
        int[] xShipRightFin = {x + 25, x + 30, x + 30, x + 25};
        int[] yLogo = {y + 33, y + 37, y + 30, y + 27};
        int[] xShipBody = {x + 15, x + 25, x + 25, x + 15};
        int[] yShipBody = {y + 33, y + 33, y + 15, y + 15};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.drawPolygon(xShipLeftFin, yLogo, 4);
        g2.drawPolygon(xShipRightFin, yLogo, 4);
        g2.drawPolygon(xShipBody, yShipBody, 4);
        g2.drawArc(x + 15, y + 10, 10, 10, 0, 180);
    }

    @Override
    public void update(GamePanel panel) {
    }
}
