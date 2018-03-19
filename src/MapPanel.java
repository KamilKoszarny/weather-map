<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {
    final static int MAP_WIDTH = 534;
    final static int MAP_HEIGHT = 494;
    static final Point2D.Double NW_IMAGE_CORNER_COORD = new Point2D.Double(54.814, 14.124);
    static final Point2D.Double SE_IMAGE_CORNER_COORD = new Point2D.Double(48.996, 24.244);
    static final int DIAMETER = 6;

    private String parameter;
    private Point clickPoint = new Point(-1, -1);
    private BufferedImage mapImage;

    private MapFrame mapFrame;
    private City[] cities;
    private CitiesDrawer citiesDrawer = new CitiesDrawer();
    private MapColorer mapColorer;

    MapPanel(City[] cities, String parameter, MapFrame mapFrame) {
        this.cities = cities;
        this.parameter = parameter;
        this.mapFrame = mapFrame;

        mapColorer = new MapColorer(cities);
        MapLoader mapLoader = new MapLoader();
        mapImage = mapLoader.getMapImage(MAP_WIDTH, MAP_HEIGHT);
        citiesDrawer.setPositions(cities);
        addMouseListener(new MapPanelMouseL(this));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        long time1 = System.currentTimeMillis();
        g.drawImage(mapImage, 0, 0, null);
        switch (parameter) {
            case "Temperature":
            case "Temperature (feel)":
                for (int x = 0; x < MAP_WIDTH; x++) {
                    for (int y = 0; y < MAP_HEIGHT; y++) {
                        mapColorer.drawPar(mapImage, g, x, y, parameter);
                    }
                }
                break;
            case "Wind":
                for (int x = 0; x < MAP_WIDTH; x += 25) {
                    for (int y = 0; y < MAP_HEIGHT; y += 25) {
                        mapColorer.drawPar(mapImage, g, x, y, parameter);
                    }
                }
                break;
            case "Precipitation":
                for (int x = 0; x < MAP_WIDTH; x ++) {
                    for (int y = 0; y < MAP_HEIGHT; y ++) {
                        mapColorer.drawPar(mapImage, g, x, y, "Humidity");
                    }
                }
                for (int x = 0; x < MAP_WIDTH; x += 25) {
                    for (int y = 0; y < MAP_HEIGHT; y += 25) {
                        mapColorer.drawPar(mapImage, g, x, y, "Precipitation");
                    }
                }
                break;
        }

        g.setColor(Color.PINK);
//        System.out.println(clickPoint.x);
        g.drawOval(clickPoint.x - DIAMETER / 2, clickPoint.y - DIAMETER / 2, DIAMETER, DIAMETER);
        citiesDrawer.drawCities(cities, g);
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
    }

    void requestFrameUpdate() {
        String temp = Double.toString(Math.round(mapColorer.getPar(clickPoint.x, clickPoint.y, "Temperature")
                * 10.)/10.) + " \u00b0C";
        String feelTemp = Double.toString(Math.round(mapColorer.getPar(clickPoint.x, clickPoint.y, "Temperature (feel)")
                * 10.)/10.) + " \u00b0C";
        String wind = Double.toString(Math.round(mapColorer.getPar(clickPoint.x, clickPoint.y, "Wind")
                * 10.)/10.) + " m/s " + mapColorer.getWindDir(clickPoint.x, clickPoint.y);
        String precipitation = "\nRain: " + Double.toString(Math.round(mapColorer.getPar(clickPoint.x, clickPoint.y, "Rain")
                * 10.)/10.) + " mm" +
                "\nSnow: " + Double.toString(Math.round(mapColorer.getPar(clickPoint.x, clickPoint.y, "Snow")
                * 10.)/10.) + " mm" +
                "\nHumidity: " + Double.toString(Math.round(mapColorer.getPar(clickPoint.x, clickPoint.y, "Humidity")
                * 10.)/10.) + " %";

        String parStrValue;
        switch (parameter) {
            case "Temperature": parStrValue = temp;break;
            case "Temperature (feel)": parStrValue = feelTemp;break;
            case "Wind": parStrValue = wind;break;
            case "Precipitation": parStrValue = " ";break;
            default: parStrValue = " ";
        }

        mapFrame.updateTextArea(temp, feelTemp, wind, precipitation);
        mapFrame.updateLabel(parStrValue);
    }

//get/set///////////////////////////////////////////////////////////////////////////////////
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Point getClickPoint() {
        return clickPoint;
    }

    public void setClickPoint(Point clickPoint) {
        this.clickPoint = clickPoint;
    }

    public void setHour(int hour){
        mapColorer.setHour(hour);
    }
}
=======
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
>>>>>>> d141ed27269fb49b2113ee3b3b1a9ce2a31a9a3c
