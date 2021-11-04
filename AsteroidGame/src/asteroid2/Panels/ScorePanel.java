package asteroid2.Panels;

import asteroid2.Asteroid2Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

    private Asteroid2Game game;
    private int panelWidth;
    private int panelHeight;
    private JLabel[] nameLabels, scoreLabels;

    public ScorePanel(Asteroid2Game game) {
        this.game = game;
        panelWidth = (int) (game.getWidth() * 0.35);
        panelHeight = (int) (game.getHeight() * 0.6);
        JLabel playerLabel = new JLabel("Player");
        JLabel scoreLabel = new JLabel("Score");
        nameLabels = new JLabel[10];
        scoreLabels = new JLabel[10];
        BufferedReader reader = null;

        setBackground(Color.black);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);
        setLayout(null);

        playerLabel.setBackground(Color.BLACK);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(new Font("roboto", Font.PLAIN, (int) (panelHeight * 0.05)));
        playerLabel.setBounds((int) (panelWidth - panelWidth * 0.9) / 2, 0, (int) (panelWidth * 0.2), (int) (panelHeight * 0.1));

        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("roboto", Font.PLAIN, (int) (panelHeight * 0.05)));
        scoreLabel.setBounds((int) (panelWidth - panelWidth * 0.005) / 2, 0, (int) (panelWidth * 0.2), (int) (panelHeight * 0.1));

        add(playerLabel);
        add(scoreLabel);

        try {
            reader = new BufferedReader(new FileReader("AsteroidGame\\texts\\HighScores.txt"));
            int counter = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                nameLabels[counter] = new JLabel(line.split(" ")[0]);
                scoreLabels[counter] = new JLabel(line.split(" ")[1]);
                counter++;
            }

            for (int i = 0; i < nameLabels.length; i++) {
                nameLabels[i].setBackground(Color.BLACK);
                nameLabels[i].setForeground(Color.WHITE);
                nameLabels[i].setFont(new Font("roboto", Font.PLAIN, (int) (panelHeight * 0.03)));
                nameLabels[i].setBounds((int) (panelWidth - panelWidth * 0.9) / 2, (int) (panelHeight * i * 0.1), (int) (panelWidth * 0.75), (int) (panelHeight * 0.25));
                this.add(nameLabels[i]);
                scoreLabels[i].setBackground(Color.BLACK);
                scoreLabels[i].setForeground(Color.WHITE);
                scoreLabels[i].setFont(new Font("roboto", Font.PLAIN, (int) (panelHeight * 0.03)));
                scoreLabels[i].setBounds((int) (panelWidth - panelWidth * 0.005) / 2, (int) (panelHeight * i * 0.1), (int) (panelWidth * 0.75), (int) (panelHeight * 0.25));
                this.add(scoreLabels[i]);
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
}
