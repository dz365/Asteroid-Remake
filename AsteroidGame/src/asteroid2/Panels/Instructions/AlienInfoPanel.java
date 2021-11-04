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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlienInfoPanel extends JPanel implements ActionListener {

    private final Asteroid2Game game;
    private final InstructionMenu menu;
    private final int panelWidth;
    private final int panelHeight;

    public AlienInfoPanel(Asteroid2Game game, InstructionMenu menu) {
        this.game = game;
        this.menu = menu;
        panelWidth = (int) (game.getWidth() * 0.47);
        panelHeight = (int) (game.getHeight() * 0.75);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);
        setLayout(null);

        JButton backButton = new JButton("back");
        backButton.setBounds((int) (panelWidth * 0.05), (int) (panelHeight * 0.925), (int) (panelWidth * 0.15), (int) (panelHeight * 0.05));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setActionCommand("back");
        backButton.addActionListener(this);

        add(backButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Trebuchet MS", Font.PLAIN, (int) (game.getHeight() * 0.05)));
        g2.drawString("Aliens", (int) (panelWidth * 0.35), (int) (panelHeight * 0.1));

        int x = (int) (panelWidth * 0.455);
        int y = (int) (panelHeight * 0.15);

        int[] xCoordinates = {(int) x - 15, (int) x, (int) x + 20, (int) x + 35, (int) x + 20, (int) x, (int) x - 15, (int) x + 35, (int) x + 20, (int) x + 20, (int) x + 15, (int) x + 5, (int) x, (int) x};
        int[] yCoordinates = {(int) y + 7, (int) y, (int) y, (int) y + 7, (int) y + 13, (int) y + 13, (int) y + 7, (int) y + 7, (int) y, (int) y - 5, (int) y - 7, (int) y - 7, (int) y - 5, (int) y};
        g2.drawPolygon(xCoordinates, yCoordinates, 14);

        BufferedReader reader = null;
        g2.setFont(new Font("Trebuchet MS", Font.PLAIN, (int) (game.getHeight() * 0.02)));

        try {
            reader = new BufferedReader(new FileReader("AsteroidGame\\texts\\AlienInfo.txt"));

            String line;
            double counter = 1;

            while ((line = reader.readLine()) != null) {
                g2.drawString(line, (int) (panelWidth * 0.01), (int) (panelHeight * counter * 0.3));
                counter += 0.2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case ("back"):
                menu.changePanel("asteroid");
                break;
        }
    }
}
