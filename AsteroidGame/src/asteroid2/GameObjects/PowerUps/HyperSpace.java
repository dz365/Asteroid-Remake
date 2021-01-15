package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Player;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class HyperSpace extends PowerUps implements ActionListener {

    private final Timer timer;
    private final Player player;

    public HyperSpace(Asteroid2Game game, Player player, int x, int y) {
        super(game, x, y, 4);
        this.player = player;
        timer = new Timer(10000, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40};
        int[] xLightningBolt = {x + 10, x + 23, x + 22, x + 30, x + 17, x + 18};
        int[] yLightningBolt = {y + 23, y + 3, y + 17, y + 14, y + 35, y + 20};
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.setColor(Color.GRAY);
        g2.fillPolygon(xpoints, ypoints, 4);
        g2.setColor(Color.YELLOW);
        g2.fillPolygon(xLightningBolt, yLightningBolt, 6);
    }

    @Override
    public void update(GamePanel panel) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (timer.isRepeats() && player.getHyperSpace() == true) {
            player.hyperSpace(false);
            timer.stop();
        }
    }
}
