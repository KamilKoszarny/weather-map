import java.awt.*;

public class CitiesDrawer {

    void drawCities(City[] cities, Graphics g) {
        Point pos;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.ITALIC, 10));
        for (City city : cities) {
            pos = city.getPosition();
            if (pos.x == 0)
                setPositions(cities);
            g.drawRect(pos.x - 2, pos.y - 2, 4, 4);
            g.drawString(city.getName(), pos.x + 5, pos.y - 5);
        }
    }

    void setPositions(City[] cities){
        Point pos;
        for (City city : cities) {
            pos = calcPosition(city);
            city.setPosition(pos);
        }
    }

    private Point calcPosition(City city){
        double longitude = city.getLongitude();
        double latitude = city.getLatitude();
        int x_pos = (int)(MapPanel.MAP_WIDTH * (latitude - MapPanel.NW_IMAGE_CORNER_COORD.y)/(MapPanel.SE_IMAGE_CORNER_COORD.y - MapPanel.NW_IMAGE_CORNER_COORD.y));
        int y_pos = (int)(MapPanel.MAP_HEIGHT * (MapPanel.NW_IMAGE_CORNER_COORD.x - longitude)/(MapPanel.NW_IMAGE_CORNER_COORD.x - MapPanel.SE_IMAGE_CORNER_COORD.x));
//        System.out.println(x_pos + " "+ y_pos + " "+ longitude + " "+ latitude);
        return new Point(x_pos, y_pos);
    }

//    public Point givePosition(Point xyPoint){}
}
