package asteroid2.Panels.Instructions;

import asteroid2.Asteroid2Game;
import asteroid2.InstructionMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PowerupPanel extends JPanel implements ActionListener {

    private final Asteroid2Game game;
    private final InstructionMenu menu;
    private final int panelWidth;
    private final int panelHeight;

    public PowerupPanel(Asteroid2Game game, InstructionMenu menu) {
        this.game = game;
        this.menu = menu;
        panelWidth = (int) (game.getWidth() * 0.47);
        panelHeight = (int) (game.getHeight() * 0.75);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);
        setLayout(null);

        JButton nextButton = new JButton("next");
        nextButton.setBounds((int) (panelWidth * 0.8), (int) (panelHeight * 0.925), (int) (panelWidth * 0.15), (int) (panelHeight * 0.05));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(Color.BLACK);
        nextButton.setActionCommand("next");
        nextButton.addActionListener(this);

        JButton backButton = new JButton("back");
        backButton.setBounds((int) (panelWidth * 0.05), (int) (panelHeight * 0.925), (int) (panelWidth * 0.15), (int) (panelHeight * 0.05));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setActionCommand("back");
        backButton.addActionListener(this);

        add(nextButton);
        add(backButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Trebuchet MS", Font.PLAIN, (int) (game.getHeight() * 0.05)));
        g2.drawString("Powerups", (int) (panelWidth * 0.3), (int) (panelHeight * 0.1));

        int x = (int) (panelWidth * 0.025);
        int y = (int) (panelHeight * 0.15);

        //drawing the bomb symbol
        int[] xShipLeftFin = {x + 15, x + 10, x + 10, x + 15};
        int[] xShipRightFin = {x + 25, x + 30, x + 30, x + 25};
        int[] yLogo = {y + 33, y + 37, y + 30, y + 27};
        int[] xShipBody = {x + 15, x + 25, x + 25, x + 15};
        int[] yShipBody = {y + 33, y + 33, y + 15, y + 15};
        g2.drawPolygon(xShipLeftFin, yLogo, 4);
        g2.drawPolygon(xShipRightFin, yLogo, 4);
        g2.drawPolygon(xShipBody, yShipBody, 4);
        g2.drawArc(x + 15, y + 10, 10, 10, 0, 180);
        g2.setFont(new Font("Trebuchet MS", Font.PLAIN, (int) (game.getHeight() * 0.02)));
        g2.drawString("Bomb: Bye bye asteroids", (int) (panelWidth * 0.12), y + (int) (panelWidth * 0.06));

        //drawing the hyperspace symbol
        y = (int) (panelHeight * 0.25);
        int[] xCoordinates = {x, x + 40, x + 40, x};
        int[] yCoordinates = {y, y, y + 40, y + 40};
        int[] xLightningBolt = {x + 10, x + 23, x + 22, x + 30, x + 17, x + 18};
        int[] yLightningBolt = {y + 23, y + 3, y + 17, y + 14, y + 35, y + 20};
        g2.drawPolygon(xCoordinates, yCoordinates, 4);
        g2.fillPolygon(xLightningBolt, yLightningBolt, 6);
        g2.drawString("HyperSpace: Need for speed?", (int) (panelWidth * 0.12), y + (int) (panelWidth * 0.06));

        //drawing the lifeUp
        y = (int) (panelHeight * 0.40);
        yCoordinates[0] = y;
        yCoordinates[1] = y;
        yCoordinates[2] = y + 40;
        yCoordinates[3] = y + 40;
        g2.drawPolygon(xCoordinates, yCoordinates, 4);
        g2.drawLine(x + 5, y, x + 5, y - 5);
        g2.drawLine(x + 35, y, x + 35, y - 5);
        g2.drawLine(x + 5, y - 5, x + 35, y - 5);
        g2.fillRect(x + 15, y + 5, 10, 30);
        g2.fillRect(x + 5, y + 15, 30, 10);
        g2.drawString("Life-Up: Self-explanatory", (int) (panelWidth * 0.12), y + (int) (panelWidth * 0.06));

        //drawing shield
        y = (int) (panelHeight * 0.55);
        int[] xShield = {x, x + 10, x + 20, x + 30, x + 40, x + 35, x + 20, x + 5};
        int[] yShield = {y + 10, y + 7, y, y + 7, y + 10, y + 27, y + 40, y + 27};
        g2.fillPolygon(xShield, yShield, 8);
        g2.drawString("Shield: Withstand hits from incoming objects", (int) (panelWidth * 0.12), y + (int) (panelWidth * 0.06));

        // drawing infinite bullets
        y = (int) (panelHeight * 0.7);
        g2.fillOval((int) x, (int) y, 50, 50);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.05)));
        g2.drawString(Character.toString('\u221e'), (int) x + 2, (int) y + 37);
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.02)));
        g2.setColor(Color.WHITE);
        g2.drawString("Unlimited Firing: What's reloading?", (int) (panelWidth * 0.12), y + (int) (panelWidth * 0.06));

        y = (int) (panelHeight * 0.85);
        g2.setColor(Color.WHITE);
        g2.drawString("Nuke: Wipes map", (int) (panelWidth * 0.12), y + (int) (panelWidth * 0.06));
        g2.fillRect(x, y + 10, 30, 30);
        g2.fillArc(x, y, 30, 20, 0, 180);
        g2.setColor(Color.BLACK);
        g2.drawLine(x + 15, y + 10, x + 15, y + 30);
        g2.drawLine(x + 10, y + 15, x + 20, y + 15);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case ("next"):
                menu.changePanel("asteroid");
                break;
            case ("back"):
                menu.changePanel("movement");
                break;
        }
    }
}
