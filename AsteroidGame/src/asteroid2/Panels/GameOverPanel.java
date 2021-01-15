package asteroid2.Panels;

import asteroid2.Asteroid2Game;
import asteroid2.Listeners.RequestFocus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameOverPanel extends JPanel implements ActionListener {

    private final Asteroid2Game game;
    private final JLabel gameOverLabel;
    private final JLabel scoreLabel;
    private final JTextField nameField;
    private final JButton menuButton;
    private final JButton submitButton;

    public GameOverPanel(Asteroid2Game game) {
        this.game = game;
        int w = game.getWidth();
        int h = game.getHeight();

        addComponentListener(new RequestFocus(this));
        setBackground(Color.black);
        setPreferredSize(game.getSize());
        setFocusable(true);
        setLayout(null);

        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.15)));
        gameOverLabel.setBounds((int) (w - w * 0.6) / 2, (int) (h * 0.05), (int) (w * 0.75), (int) (h * 0.25));

        scoreLabel = new JLabel("Score: " + game.getScore());
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.05)));
        scoreLabel.setBounds((int) (w - w * 0.15) / 2, (int) (h * 0.19), (int) (w * 0.25), (int) (h * 0.25));

        nameField = new JTextField();
        nameField.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.02)));
        nameField.setBounds((int) (w - w * 0.145) / 2, (int) (h * 0.39), (int) (w * 0.15), (int) (h * 0.05));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);

        menuButton = new JButton("Main Menu");
        menuButton.setBounds((int) (w - w * 0.25) / 2, (int) (h * 0.6), (int) (w * 0.25), (int) (h * 0.1));
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);

        submitButton = new JButton("Submit");
        submitButton.setBounds((int) (w - w * 0.25) / 2, (int) (h * 0.45), (int) (w * 0.25), (int) (h * 0.1));
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);

        menuButton.setActionCommand("menu");
        submitButton.setActionCommand("submit");

        menuButton.addActionListener(this);
        submitButton.addActionListener(this);

        add(gameOverLabel);
        add(scoreLabel);
        add(nameField);
        add(menuButton);
        add(submitButton);
    }

    public void changeScore() {
        scoreLabel.setText("Score: " + game.getScore());
    }

    public void submitScore() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> scores = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("texts\\HighScores.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line.split(" ")[0]);
                scores.add(line.split(" ")[1]);
            }

            for (int i = 0; i < scores.size(); i++) {
                if (game.getScore() >= Integer.parseInt(scores.get(i))) {
                    scores.add(i, String.valueOf(game.getScore()));
                    if (nameField.getText().length() > 0) {
                        names.add(i, nameField.getText().replace(" ", ""));
                    } else {
                        names.add(i, "unknown");
                    }
                    scores.remove(scores.size() - 1);
                    names.remove(names.size() - 1);
                    break;
                }
            }

            writer = new BufferedWriter(new FileWriter("texts\\HighScores.txt"));

            for (int i = 0; i < scores.size(); i++) {
                writer.append(names.get(i) + " " + scores.get(i));
                writer.newLine();
            }
            writer.close();
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
            case ("menu"):
                new Asteroid2Game();
                this.game.dispose();
                break;
            case ("submit"):
                submitScore();
                submitButton.setEnabled(false);
                break;
        }
    }
}
