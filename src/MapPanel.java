import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    public MapPanel(){
        setSize(1068/2, 988/2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor (Color.BLUE);
        g.fillRect (0,0, getWidth(), getHeight());
        g.setColor (Color.YELLOW);
        g.drawString ("To jest tekst",30,60);
        g.setColor (Color.GREEN);
        g.drawRect (30,80,60,20);
    }
}
