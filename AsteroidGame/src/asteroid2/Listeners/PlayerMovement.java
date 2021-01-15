package asteroid2.Listeners;

import asteroid2.GameObjects.Player;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PlayerMovement implements MouseMotionListener {

    private final Player player;

    public PlayerMovement(Player player) {
        this.player = player;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.setOrientation((float) (Math.atan2(player.getY() - e.getY(), player.getX() - e.getX())) - Math.PI / 2);
    }
}
