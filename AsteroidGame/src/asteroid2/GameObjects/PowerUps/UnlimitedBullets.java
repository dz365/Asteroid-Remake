package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.Panels.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class UnlimitedBullets extends PowerUps implements ActionListener {

    private final Timer timer;
    
    public UnlimitedBullets(Asteroid2Game game, int x, int y) {
        super(game, x, y, 1);
        timer = new Timer(10000, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics2D g2) {
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40}; 
        setXPoints(xCoordinates);
        setYPoints(yCoordinates);
        setBounds();
        g2.setColor(Color.WHITE);
        g2.fillOval(x, y, 50, 50);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.05)));
        g2.drawString(Character.toString('\u221e'), x + 2, y + 37);
    }
    
    @Override
    public void update(GamePanel panel) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (timer.isRepeats() && powerUpIsRunning() == false) {
            endPowerUp(true);
            timer.stop();
        }
    }
}
