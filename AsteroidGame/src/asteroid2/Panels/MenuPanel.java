package asteroid2.Panels;

import asteroid2.Asteroid2Game;
import asteroid2.InstructionMenu;
import asteroid2.ScoreBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {

    private final Asteroid2Game game;

    public MenuPanel(Asteroid2Game game) {
        this.game = game;
        int w = game.getWidth();
        int h = game.getHeight();

        setBackground(Color.black);
        setPreferredSize(game.getSize());
        setFocusable(true);
        setLayout(null);

        JButton playButton = new JButton("Play");
        playButton.setBounds((int) (w - w * 0.25) / 2, (int) (h * 0.3), (int) (w * 0.25), (int) (h * 0.1));
        playButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.WHITE);

        JButton scoresButton = new JButton("Scores");
        scoresButton.setBounds((int) (w - w * 0.25) / 2, (int) (h * 0.45), (int) (w * 0.25), (int) (h * 0.1));
        scoresButton.setBackground(Color.BLACK);
        scoresButton.setForeground(Color.WHITE);

        JButton instructionButton = new JButton("Instructions");
        instructionButton.setBounds((int) (w - w * 0.25) / 2, (int) (h * 0.6), (int) (w * 0.25), (int) (h * 0.1));
        instructionButton.setBackground(Color.BLACK);
        instructionButton.setForeground(Color.WHITE);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds((int) (w - w * 0.25) / 2, (int) (h * 0.75), (int) (w * 0.25), (int) (h * 0.1));
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);

        playButton.setActionCommand("play");
        scoresButton.setActionCommand("scores");
        instructionButton.setActionCommand("instructions");
        quitButton.setActionCommand("quit");

        playButton.addActionListener(this);
        scoresButton.addActionListener(this);
        instructionButton.addActionListener(this);
        quitButton.addActionListener(this);

        add(playButton);
        add(scoresButton);
        add(instructionButton);
        add(quitButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.15)));
        g2.drawString("Asteroids", (int) (game.getWidth() * 0.5) / 2, (int) (game.getHeight() * 0.2));
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case ("play"):
                game.changePanel("game");
                break;
            case ("scores"):
                new ScoreBoard(game);
                break;
            case ("instructions"):
                new InstructionMenu(game);
                break;
            case ("quit"):
                System.exit(0);
                break;
        }
    }
}
