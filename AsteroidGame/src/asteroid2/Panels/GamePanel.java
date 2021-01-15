package asteroid2.Panels;

import asteroid2.Listeners.PlayerBullet;
import asteroid2.Listeners.PlayerMovement;
import asteroid2.Listeners.RequestFocus;
import asteroid2.Listeners.ControlPlayer;
import asteroid2.GameObjects.Bullet;
import asteroid2.GameObjects.Player;
import asteroid2.GameObjects.Asteroid;
import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.Aliens;
import asteroid2.GameObjects.PowerUps.Bomb;
import asteroid2.GameObjects.PowerUps.HyperSpace;
import asteroid2.GameObjects.PowerUps.LifeUp;
import asteroid2.GameObjects.PowerUps.Nuke;
import asteroid2.GameObjects.PowerUps.PowerUps;
import asteroid2.GameObjects.PowerUps.Shield;
import asteroid2.GameObjects.PowerUps.UnlimitedBullets;
import asteroid2.Listeners.AsteroidGenerate;
import java.awt.*;
import java.util.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable {

    private final Asteroid2Game game;
    private final ArrayList<Asteroid> asteroids = new ArrayList<>();
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Bullet> alienBullets = new ArrayList<>();
    private final ArrayList<Bullet> bomb = new ArrayList<>();
    private final ArrayList<Aliens> aliens = new ArrayList<>();
    private final ArrayList<PowerUps> powerUps = new ArrayList<>();
    private boolean run = false;
    private final Player player;
    private int delay = 10000;
    private int checkpoint = 500;
    private final Timer timer;
    private int capacity = 5;
    private boolean canPlaySound = false;
    private Clip clip;

    public GamePanel(Asteroid2Game game) {
        this.game = game;
        player = new Player(game, game.getWidth() / 2, game.getHeight() / 2, 0, 1, 3, 0);
        asteroids.add(new Asteroid(game, new Random().nextInt(game.getWidth()), 0, Math.random(), 5, 3));
        player.setBomb(2);
        addComponentListener(new RequestFocus(this));
        addMouseListener(new PlayerBullet(game, this, player, bullets, bomb));
        addMouseMotionListener(new PlayerMovement(player));
        addKeyListener(new ControlPlayer(game, this, player, bullets, bomb));
        setBackground(Color.black);
        setPreferredSize(game.getSize());
        setFocusable(true);

        timer = new Timer(delay, new AsteroidGenerate(game, asteroids));
        timer.start();
        start();
    }

    private void generateAliens() {
        aliens.add(new Aliens(game, player, 0, new Random().nextInt(game.getHeight() - 100), 0, 3, alienBullets));
    }

    public ArrayList<Asteroid> asteroids() {
        return asteroids;
    }

    public ArrayList<Bullet> bullets() {
        return bullets;
    }

    public ArrayList<Bullet> alienBullets() {
        return alienBullets;
    }

    public ArrayList<Aliens> aliens() {
        return aliens;
    }

    private void intersection() {
        ArrayList<Asteroid> asteroidRemove = new ArrayList<>();
        ArrayList<Asteroid> asteroidAdd = new ArrayList<>();
        ArrayList<Bullet> bulletRemove = new ArrayList<>();
        ArrayList<Bullet> alienBulletRemove = new ArrayList<>();
        ArrayList<Bullet> bombRemove = new ArrayList<>();
        ArrayList<Aliens> alienRemove = new ArrayList<>();
        ArrayList<PowerUps> addPowerUps = new ArrayList<>();
        ArrayList<PowerUps> removePowerUps = new ArrayList<>();

        for (Asteroid i : asteroids) {
            if (i.getBounds().intersects(player.getBounds())) {
                if (player.getShield() >= 1) {
                    player.setShield(player.getShield() - 1);
                } else {
                    player.setLives(player.getLives() - 1);
                    player.setX(game.getWidth() / 2);
                    player.setY(game.getHeight() / 2);
                    if (player.getLives() == 0) {
                        run = false;
                        setPlaySound(true);
                        if (clip() != null) {
                            clip().stop();
                        }
                        game.changePanel("gameover");
                    }
                }
                asteroidRemove.add(i);
            }

            for (Bullet j : bullets) {
                if (i.getBounds().intersects(j.getBounds())) {
                    if (i.getSize() == 3) {
                        int random = new Random().nextInt(6);
                        if (random == 0) {
                            addPowerUps.add(new LifeUp(game, i.getX(), i.getY()));
                        } else if (random == 1) {
                            addPowerUps.add(new UnlimitedBullets(game, i.getX(), i.getY()));
                        } else if (random == 2) {
                            addPowerUps.add(new Shield(game, player, i.getX(), i.getY()));
                        } else if (random == 3) {
                            addPowerUps.add(new Bomb(game, i.getX(), i.getY()));
                        } else if (random == 4) {
                            addPowerUps.add(new HyperSpace(game, player, i.getX(), i.getY()));
                        } else if (random == 5) {
                            addPowerUps.add(new Nuke(game, i.getX(), i.getY()));
                        }
                    }

                    if (i.getSize() == 1) {
                        game.setScore(game.getScore() + 20);
                        asteroidRemove.add(i);
                        bulletRemove.add(j);
                    } else {
                        if (i.getSize() == 2) {
                            game.setScore(game.getScore() + 30);
                        } else {
                            game.setScore(game.getScore() + 50);
                        }
                        asteroidRemove.add(i);
                        bulletRemove.add(j);
                        asteroidAdd.add(new Asteroid(game, i.getX() - 50, i.getY(), j.getOrientation(), i.getVelocity() + 2, i.getSize() - 1));
                        asteroidAdd.add(new Asteroid(game, i.getX() + 50, i.getY(), -j.getOrientation(), i.getVelocity() + 2, i.getSize() - 1));
                    }
                    if (game.getScore() >= checkpoint) {
                        checkpoint += 500;
                        if (delay > 1000) {
                            delay -= 1000;
                        }
                        timer.setDelay(delay);
                    }
                }
            }
            for (Bullet j : alienBullets) {
                if (i.getBounds().intersects(j.getBounds())) {
                    if (i.getSize() == 3) {
                        int random = new Random().nextInt(6);
                        if (random == 0) {
                            addPowerUps.add(new LifeUp(game, i.getX(), i.getY()));
                        } else if (random == 1) {
                            addPowerUps.add(new UnlimitedBullets(game, i.getX(), i.getY()));
                        } else if (random == 2) {
                            addPowerUps.add(new Shield(game, player, i.getX(), i.getY()));
                        } else if (random == 3) {
                            addPowerUps.add(new Bomb(game, i.getX(), i.getY()));
                        } else if (random == 4) {
                            addPowerUps.add(new HyperSpace(game, player, i.getX(), i.getY()));
                        } else if (random == 5) {
                            addPowerUps.add(new Nuke(game, i.getX(), i.getY()));
                        }
                    }
                    if (i.getSize() == 1) {
                        asteroidRemove.add(i);
                        alienBulletRemove.add(j);
                    } else {
                        asteroidRemove.add(i);
                        alienBulletRemove.add(j);
                        asteroidAdd.add(new Asteroid(game, i.getX() - 50, i.getY(), j.getOrientation(), i.getVelocity() + 2, i.getSize() - 1));
                        asteroidAdd.add(new Asteroid(game, i.getX() + 50, i.getY(), -j.getOrientation(), i.getVelocity() + 2, i.getSize() - 1));
                    }
                }
            }
            for (Bullet j : bomb) {
                if (j.getX() > game.getWidth() || j.getX() < 0 || j.getY() > game.getHeight() || j.getY() < 0) {
                    bombRemove.add(j);
                }
                if (i.getBounds().intersects(j.getBounds())) {
                    game.setScore(game.getScore() + 150);
                    asteroidRemove.add(i);
                    bombRemove.add(j);
                }
            }
        }

        for (Aliens k : aliens) {
            if (k.getBounds().intersects(player.getBounds())) {
                if (player.getShield() >= 1) {
                    player.setShield(player.getShield() - 1);
                } else {
                    player.setLives(player.getLives() - 1);
                    player.setX(game.getWidth() / 2);
                    player.setY(game.getHeight() / 2);
                    if (player.getLives() == 0) {
                        run = false;
                        if (clip() != null) {
                            clip().stop();
                        }
                        game.changePanel("gameover");
                    }
                }
                k.hit();
                alienRemove.add(k);
            }
            for (Bullet j : bullets) {
                if (j.getBounds().intersects(k.getBounds())) {
                    alienRemove.add(k);
                    k.hit();
                    bulletRemove.add(j);
                }
            }
            for (Bullet j : bomb) {
                if (j.getBounds().intersects(k.getBounds())) {
                    alienRemove.add(k);
                    k.hit();
                    bombRemove.add(j);
                }
            }
        }
        for (Bullet j : alienBullets) {
            if (j.getBounds().intersects(player.getBounds())) {
                if (player.getShield() >= 1) {
                    player.setShield(player.getShield() - 1);
                } else {
                    player.setLives(player.getLives() - 1);
                    player.setX(game.getWidth() / 2);
                    player.setY(game.getHeight() / 2);

                    if (player.getLives() == 0) {
                        run = false;
                        if (clip() != null) {
                            clip().stop();
                        }
                        game.changePanel("gameover");
                    }
                }
                alienBulletRemove.add(j);
            }
        }

        for (Bullet j : bullets) {
            if (j.getX() > game.getWidth() || j.getX() < 0 || j.getY() > game.getHeight() || j.getY() < 0) {
                bulletRemove.add(j);
            }
        }

        for (Bullet j : alienBullets) {
            if (j.getX() > game.getWidth() || j.getX() < 0 || j.getY() > game.getHeight() || j.getY() < 0) {
                bulletRemove.add(j);
            }
        }

        for (Bullet j : bomb) {
            if (j.getX() > game.getWidth() || j.getX() < 0 || j.getY() > game.getHeight() || j.getY() < 0) {
                bombRemove.add(j);
            }
        }

        for (PowerUps i : powerUps) {
            if (player.getBounds().intersects(i.getBounds())) {
                if (i.powerUpType() == "LifeUp") {
                    player.setLives(player.getLives() + 1);
                } else if (i.powerUpType() == "UnlimitedBullets") {
                    i.endPowerUp(false);
                } else if (i.powerUpType() == "Shield") {
                    player.setShield(player.getShield() + 5);
                } else if (i.powerUpType() == "Bomb") {
                    player.setBomb(player.getBomb() + 1);
                } else if (i.powerUpType() == "Hyperspace") {
                    player.hyperSpace(true);
                } else if (i.powerUpType() == "Nuke") {
                    player.setNuke(player.getNuke() + 1);
                }
                removePowerUps.add(i);
            }
        }

        if (game.getScore() != 0 && game.getScore() % 40 == 0) {
            generateAliens();
            game.setScore(game.getScore() + 50);
        }

        asteroids.addAll(asteroidAdd);
        asteroids.removeAll(asteroidRemove);
        bomb.removeAll(bombRemove);
        bullets.removeAll(bulletRemove);
        alienBullets.removeAll(alienBulletRemove);
        aliens.removeAll(alienRemove);
        powerUps.addAll(addPowerUps);
        powerUps.removeAll(removePowerUps);
    }

    public void drawLife(Graphics2D g2, int lives) {
        for (int i = 1; i <= lives; i++) {
            int[] yLifeCoordinates = {25, 25 + 30, 25 + 20, 25 + 20, 25 + 30};
            int[] xLifeCoordinates = {25 * i, 25 * i - 10, 25 * i - 3, 25 * i + 3, 25 * i + 10};
            g2.fillPolygon(xLifeCoordinates, yLifeCoordinates, 5);
        }
    }

    public void setBulletCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBulletCapacity() {
        return capacity;
    }

    public void start() {
        Thread thread = new Thread(this);
        run = true;
        thread.start();
    }

    public void stop() {
        run = false;
    }

    @Override
    public void run() {
        while (run) {
            try {
                repaint();
                Thread.sleep(17);
            } catch (Exception e) {
            }
        }
    }

    public boolean canPlaySound() {
        return canPlaySound;
    }

    public void setPlaySound(boolean sound) {
        this.canPlaySound = sound;
    }

    public Clip clip() {
        return clip;
    }

    public void playSound(int sound) {
        String file = "";

        switch (sound) {
            case 0:
                file = "thrust";
                break;
            case 1:
                file = "fire";
                break;
        }
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(file + ".wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.05)));
        g2.drawString(String.valueOf(game.getScore()), (int) (game.getWidth() * 0.45), (int) (game.getHeight() * 0.05));
        g2.setFont(new Font("roboto", Font.PLAIN, (int) (game.getHeight() * 0.025)));
        g2.drawString("Shield: " + player.getShield(), (int) (game.getWidth() * 0.01), (int) (game.getHeight() * 0.1));
        g2.drawString("Bombs: " + player.getBomb(), (int) (game.getWidth() * 0.01), (int) (game.getHeight() * 0.15));
        g2.drawString("Nukes: " + player.getNuke(), (int) (game.getWidth() * 0.01), (int) (game.getHeight() * 0.2));
        drawLife(g2, player.getLives());

        AffineTransform old = g2.getTransform();
        player.paintComponent(g2);
        player.update(this);

        g2.setTransform(old);

        for (Asteroid i : asteroids) {
            i.paintComponent(g2);
            i.update(this);
        }

        for (Bullet i : bullets) {
            i.paintComponent(g2);
            i.update(this);
        }

        for (Bullet i : alienBullets) {
            i.paintComponent(g2);
            i.update(this);
        }

        for (Bullet i : bomb) {
            i.paintComponent(g2);
            i.update(this);
        }

        for (Aliens i : aliens) {
            i.paintComponent(g2);
            i.update(this);
        }

        for (PowerUps i : powerUps) {
            if (i.powerUpType() == "UnlimitedBullets" && i.powerUpIsRunning() == false) {
                setBulletCapacity(9000);
            } else {
                setBulletCapacity(5);
            }
            i.paintComponent(g2);
            i.update(this);
        }
        intersection();
    }
}
