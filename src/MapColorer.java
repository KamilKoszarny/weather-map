import java.awt.*;
import java.awt.image.BufferedImage;

public class MapColorer {

    City[] cities;

    MapColorer(City[] cities) {
        this.cities = cities;
    }

    void drawPar(BufferedImage img, Graphics g, int x, int y, String par) {
        int clr = img.getRGB(x, y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        if (red == 255 && green == 255 && blue == 255) {
            switch (par) {
                case "Temperature":
                case "Temperature (feel)":
                    if ((int) getPar(x, y, par) != (int) getPar(x + 1, y, par) ||
                            (int) getPar(x, y, par) != (int) getPar(x, y + 1, par)) {
                        g.setColor(Color.BLACK);
                        g.drawLine(x, y, x, y);
                    } else {
                        g.setColor(getColor(x, y, par));
                        g.drawLine(x, y, x, y);
                    }
                    break;
                case "Wind":
                    g.setColor(getColor(x, y, "Temperature"));
                    double lenght = getPar(x, y, "Wind");
                    double direction = getPar(x, y, "Wind (dir)");
                    int arrowAngle = 25;
                    int x1 = x + (int) (Math.sin(Math.toRadians(direction)) * lenght / 2);
                    int x2 = x - (int) (Math.sin(Math.toRadians(direction)) * lenght / 2);
                    int y1 = y - (int) (Math.cos(Math.toRadians(direction)) * lenght / 2);
                    int y2 = y + (int) (Math.cos(Math.toRadians(direction)) * lenght / 2);
                    g.drawLine(x1, y1, x2, y2);
                    int x3 = x1 - (int) (Math.sin(Math.toRadians(direction + arrowAngle)) * lenght / 2);
                    int y3 = y1 + (int) (Math.cos(Math.toRadians(direction + arrowAngle)) * lenght / 2);
                    g.drawLine(x1, y1, x3, y3);
                    int x4 = x1 - (int) (Math.sin(Math.toRadians(direction - arrowAngle)) * lenght / 2);
                    int y4 = y1 + (int) (Math.cos(Math.toRadians(direction - arrowAngle)) * lenght / 2);
                    g.drawLine(x1, y1, x4, y4);
                    break;
                case "Humidity":
                    if ((int) (getPar(x, y, "Humidity") / 10) != (int) (getPar(x + 1, y, "Humidity") / 10) ||
                            (int) (getPar(x, y, "Humidity") / 10) != (int) (getPar(x, y + 1, "Humidity") / 10)) {
                        g.setColor(Color.BLACK);
                        g.drawLine(x, y, x, y);
                    } else {
                        g.setColor(getColor(x, y, "Humidity"));
                        g.drawLine(x, y, x, y);
                    }
                    break;
                case "Precipitation":
                    double rainH = getPar(x, y, "Rain");
                    if (rainH > 0.1) {
                        g.setColor(Color.GRAY);
                        g.drawLine(x, y, x, y + (int) (rainH * 2));
                    }
                    double snowH = getPar(x, y, "Snow");
                    if (snowH > 0.1) {
                        g.setColor(Color.WHITE);
                        g.drawLine(x, y, x, y - (int) (snowH * 2));
                    }
                    break;
            }
        }
    }


    private Color getColor(int x, int y, String par) {
        Color color;
        double parValue = getPar(x, y, par);
        ParToColorConverter ptcConverter = new ParToColorConverter();
        color = ptcConverter.convert(parValue, par);
        return color;
    }

    public String getWindDir(int x, int y, String par) {
        double angle = getPar(x, y, "Wind (dir)");
        double s = 11.25;
        if (isBetween(angle, 0, s) || isBetween(angle, 31 * s, 32 * s)) return "S";
        else if (isBetween(angle, s, 3 * s)) return "SSW";
        else if (isBetween(angle, 3 * s, 5 * s)) return "SW";
        else if (isBetween(angle, 5 * s, 7 * s)) return "WSW";
        else if (isBetween(angle, 7 * s, 9 * s)) return "W";
        else if (isBetween(angle, 9 * s, 11 * s)) return "WNW";
        else if (isBetween(angle, 11 * s, 13 * s)) return "NW";
        else if (isBetween(angle, 13 * s, 15 * s)) return "NNW";
        else if (isBetween(angle, 15 * s, 17 * s)) return "N";
        else if (isBetween(angle, 17 * s, 19 * s)) return "NNE";
        else if (isBetween(angle, 19 * s, 21 * s)) return "NE";
        else if (isBetween(angle, 21 * s, 23 * s)) return "ENE";
        else if (isBetween(angle, 23 * s, 25 * s)) return "E";
        else if (isBetween(angle, 25 * s, 27 * s)) return "ESE";
        else if (isBetween(angle, 27 * s, 29 * s)) return "SSE";
        return " ";
    }

    public double getPar(int x, int y, String par) {
        double parValue;

        int hour = 0;
        double numeral = 0;
        double denominator = 0;
        for (City city : cities) {
//position
            int cityX = city.getPosition().x;
            int cityY = city.getPosition().y;
            double cityDist = Math.sqrt((x - cityX) * (x - cityX) + (y - cityY) * (y - cityY));
//parameter
            double cityPar = 100;
            switch (par) {
                case "Temperature":
                    cityPar = city.getTemperatue(hour);
                    break;
                case "Temperature (feel)":
                    cityPar = city.getFeelTemperatue(hour);
                    break;
                case "Wind":
                    cityPar = city.getWindSpeed(hour);
                    break;
                case "Wind (dir)":
                    String dir = city.getWindDir(hour);
                    switch (dir) {
                        case "S":
                            cityPar = 0;
                            break;
                        case "SSW":
                            cityPar = 22.5;
                            break;
                        case "SW":
                            cityPar = 45;
                            break;
                        case "WSW":
                            cityPar = 67.5;
                            break;
                        case "W":
                            cityPar = 90;
                            break;
                        case "WNW":
                            cityPar = 112.5;
                            break;
                        case "NW":
                            cityPar = 135;
                            break;
                        case "NNW":
                            cityPar = 157.5;
                            break;
                        case "N":
                            cityPar = 180;
                            break;
                        case "NNE":
                            cityPar = 202.5;
                            break;
                        case "NE":
                            cityPar = 225;
                            break;
                        case "ENE":
                            cityPar = 247.5;
                            break;
                        case "E":
                            cityPar = 270;
                            break;
                        case "ESE":
                            cityPar = 292.5;
                            break;
                        case "SE":
                            cityPar = 315;
                            break;
                        case "SSE":
                            cityPar = 337.5;
                            break;
                    }
                    break;
                case "Rain":
                    cityPar = city.getRain(hour);
                    break;
                case "Snow":
                    cityPar = city.getSnow(hour);
                    break;
                case "Humidity":
                    cityPar = city.getHumidity(hour);
                    break;
            }
//average
            if (cityDist == 0)
                cityDist = 1;
            numeral += cityPar / Math.pow(cityDist, 3);
            denominator += 1 / Math.pow(cityDist, 3);
        }

        parValue = numeral / denominator;
//        System.out.println(temp);

        return parValue;
    }

    private static boolean isBetween(double x, double lower, double upper) {
        return lower <= x && x <= upper;
    }
}
