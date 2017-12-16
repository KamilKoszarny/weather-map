import javax.swing.*;
import

public class MapFrame extends JFrame {

    public MapFrame(){
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new MiGLayout);

        MapPanel mapPanel = new MapPanel();
        add(mapPanel);

        setVisible(true);
    }
}
