package asteroid2;

import asteroid2.Panels.GamePanel;
import asteroid2.Panels.MenuPanel;
import asteroid2.Panels.GameOverPanel;
import javax.swing.*;
import java.awt.*;

public class Asteroid2Game extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int PANEL_WIDTH = (int) (screenSize.getWidth() * 0.62);
    private final int PANEL_HEIGHT = (int) (screenSize.getHeight() * 0.83);
    private final JPanel panel = new JPanel();
    private final CardLayout cl = new CardLayout();
    private int score = 0;
    private final GamePanel gamePanel;
    private final MenuPanel menu;
    private final GameOverPanel gameOver;

    public Asteroid2Game() {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setTitle("Asteroids2");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gamePanel = new GamePanel(this);
        menu = new MenuPanel(this);
        gameOver = new GameOverPanel(this);

        panel.setLayout(cl);

        panel.add(menu, "menu");
        panel.add(gamePanel, "game");
        panel.add(gameOver, "gameover");

        cl.show(panel, "menu");

        add(panel);
        pack();
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void changePanel(String panelName) {
        cl.show(panel, panelName);

        if (panelName.equals("gameover")) {
            gameOver.changeScore();
        }
    }

    public static void main(String[] args) {
        Asteroid2Game game = new Asteroid2Game();
    }
}
