package asteroid2;

import asteroid2.Panels.ScorePanel;
import javax.swing.JFrame;

public class ScoreBoard extends JFrame {

    private Asteroid2Game game;
    private int panelWidth;
    private int panelHeight;
    private ScorePanel panel;

    public ScoreBoard(Asteroid2Game game) {
        this.game = game;
        panelWidth = (int) (game.getWidth() * 0.26);
        panelHeight = (int) (game.getHeight() * 0.55);

        setSize(panelWidth, panelHeight);
        setTitle("Score Board");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        this.setLocation((int) (game.getScreenSize().getWidth() - panelWidth * 2), 50);

        panel = new ScorePanel(game);

        add(panel);
        pack();
    }
}
