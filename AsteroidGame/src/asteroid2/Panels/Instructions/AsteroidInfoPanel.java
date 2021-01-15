package asteroid2.Panels.Instructions;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Asteroid;
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

public class AsteroidInfoPanel extends JPanel implements ActionListener {

    private final Asteroid2Game game;
    private final InstructionMenu menu;
    private final int panelWidth;
    private final int panelHeight;

    public AsteroidInfoPanel(Asteroid2Game game, InstructionMenu menu) {
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
        g2.drawString("Asteroids", (int) (panelWidth * 0.3), (int) (panelHeight * 0.1));

        Asteroid asteroid = new Asteroid(game, (int) (panelWidth * 0.475), (int) (panelHeight * 0.25), 0, 0, 7);
        asteroid.paintComponent(g2);

        BufferedReader reader = null;
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.02)));

        try {
            reader = new BufferedReader(new FileReader("texts\\AsteroidInfo.txt"));

            String line;
            double counter = 1;

            while ((line = reader.readLine()) != null) {
                g2.drawString(line, (int) (panelWidth * 0.01), (int) (panelHeight * counter * 0.4));
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
            case ("next"):
                menu.changePanel("alien");
                break;
            case ("back"):
                menu.changePanel("powerup");
                break;
        }
    }
}
