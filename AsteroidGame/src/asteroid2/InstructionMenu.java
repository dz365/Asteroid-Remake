package asteroid2;

import asteroid2.Panels.Instructions.AlienInfoPanel;
import asteroid2.Panels.Instructions.AsteroidInfoPanel;
import asteroid2.Panels.Instructions.IntroductionPanel;
import asteroid2.Panels.Instructions.MovementPanel;
import asteroid2.Panels.Instructions.PowerupPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InstructionMenu extends JFrame {

    private Asteroid2Game game;
    private int panelWidth;
    private int panelHeight;
    private JPanel panel = new JPanel();
    private CardLayout cl = new CardLayout();

    public InstructionMenu(Asteroid2Game game) {
        this.game = game;
        panelWidth = (int) (game.getWidth() * 0.45);
        panelHeight = (int) (game.getHeight() * 0.6);

        setSize(panelWidth, panelHeight);
        setTitle("Instructions");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        this.setLocation((int) (game.getScreenSize().getWidth() - panelWidth * 1.2), 50);

        IntroductionPanel intro = new IntroductionPanel(game, this);
        MovementPanel move = new MovementPanel(game, this);
        PowerupPanel powerup = new PowerupPanel(game, this);
        AsteroidInfoPanel asteroidInfo = new AsteroidInfoPanel(game, this);
        AlienInfoPanel alienInfo = new AlienInfoPanel(game, this);

        panel.setLayout(cl);

        panel.add(intro, "intro");
        panel.add(move, "movement");
        panel.add(powerup, "powerup");
        panel.add(asteroidInfo, "asteroid");
        panel.add(alienInfo, "alien");

        cl.show(panel, "intro");

        add(panel);
        pack();
    }

    public void changePanel(String panelName) {
        cl.show(panel, panelName);
    }
}
