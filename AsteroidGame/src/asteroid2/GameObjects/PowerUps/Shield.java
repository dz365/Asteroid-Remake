package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Player;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Shield extends PowerUps {

    private Player player;

    public Shield(Asteroid2Game game, Player player, int x, int y) {
        super(game, x, y, 2);
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40};
        int[] xShield = {x, x + 10, x + 20, x + 30, x + 40, x + 35, x + 20, x + 5};
        int[] yShield = {y + 10, y + 7, y, y + 7, y + 10, y + 27, y + 40, y + 27};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.setColor(Color.CYAN);
        g2.fillPolygon(xShield, yShield, 8);
    }

    @Override
    public void update(GamePanel panel) {
    }
}
