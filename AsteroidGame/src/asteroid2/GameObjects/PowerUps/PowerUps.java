package asteroid2.GameObjects.PowerUps;

import asteroid2.Asteroid2Game;
import asteroid2.GameObjects.GameObject;

public abstract class PowerUps extends GameObject {

    private int powerUp;
    private boolean run;

    public PowerUps(Asteroid2Game game, int x, int y, int powerUp) {
        super(game, x, y, 0, 0);
        this.powerUp = powerUp;
    }

    public boolean powerUpIsRunning() {
        return run;
    }

    public void endPowerUp(boolean end) {
        this.run = end;
    }

    public String powerUpType() {
        if (powerUp == 0) {
            return "LifeUp";
        } else if (powerUp == 1) {
            return "UnlimitedBullets";
        } else if (powerUp == 2) {
            return "Shield";
        } else if (powerUp == 3) {
            return "Bomb";
        } else if (powerUp == 4) {
            return "Hyperspace";
        } else if (powerUp == 5) {
            return "Nuke";
        } else {
            return null;
        }
    }
}
