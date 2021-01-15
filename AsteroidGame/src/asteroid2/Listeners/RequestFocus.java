package asteroid2.Listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;

public class RequestFocus implements ComponentListener {

    private final JPanel panel;

    public RequestFocus(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void componentResized(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        panel.requestFocusInWindow();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
