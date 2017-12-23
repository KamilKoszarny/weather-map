import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

//MouseListener///////////////////////////////////////////////////////////////////////////////////
public class MapPanelMouseL implements MouseListener {

    MapPanel mapPanel;
    MapPanelMouseL(MapPanel mapPanel){
        this.mapPanel = mapPanel;
    }


    @Override
    public void mouseClicked (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
//        System.out.println(x + " " + y);
        Point oldOval = mapPanel.getClickPoint();
        mapPanel.setClickPoint(new Point(x,y));
        mapPanel.repaint(oldOval.x - MapPanel.DIAMETER/2,oldOval.y - MapPanel.DIAMETER/2, MapPanel.DIAMETER + 1, MapPanel.DIAMETER + 1);
        mapPanel.repaint(x - MapPanel.DIAMETER/2,y - MapPanel.DIAMETER/2, MapPanel.DIAMETER + 1, MapPanel.DIAMETER + 1);
        mapPanel.requestFrameUpdate();
    }

    @Override
    public void mousePressed (MouseEvent e){
    }
    @Override
    public void mouseReleased (MouseEvent e){
    }
    @Override
    public void mouseEntered (MouseEvent e){
    }
    @Override
    public void mouseExited (MouseEvent e){

    }
}