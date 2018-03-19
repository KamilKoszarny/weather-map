import java.awt.*;

public class ParToColorConverter {

    private final static int SCALE = 71;
    private final static int TEMP_MIN = -30;
    private double[] temps = new double[SCALE];
    private Color[] colors = {
            new Color(255, 0, 0),
            new Color(255, 10, 0),
            new Color(255, 20, 0),
            new Color(255, 30, 0),
            new Color(255, 40, 0),
            new Color(255, 50, 0),
            new Color(255, 60, 0),
            new Color(255, 70, 0),
            new Color(255, 80, 0),
            new Color(255, 90, 0),
            new Color(255, 100, 0),
            new Color(255, 110, 0),
            new Color(255, 120, 0),
            new Color(255, 130, 0),
            new Color(255, 140, 0),
            new Color(255, 150, 0),
            new Color(255, 160, 0),
            new Color(255, 170, 0),
            new Color(255, 180, 0),
            new Color(255, 190, 0),
            new Color(255, 200, 0),
            new Color(255, 210, 0),
            new Color(255, 220, 0),
            new Color(255, 230, 0),
            new Color(255, 240, 0),
            new Color(255, 250, 0),
            new Color(253, 255, 0),
            new Color(215, 255, 0),
            new Color(176, 255, 0),
            new Color(138, 255, 0),
            new Color(101, 255, 0),
            new Color(62, 255, 0),
            new Color(23, 255, 0),
            new Color(0, 255, 16),
            new Color(0, 255, 54),
            new Color(0, 255, 92),
            new Color(0, 255, 131),
            new Color(0, 255, 168),
            new Color(0, 255, 208),
            new Color(0, 255, 244),
            new Color(0, 228, 255),
            new Color(0, 212, 255),
            new Color(0, 196, 255),
            new Color(0, 180, 255),
            new Color(0, 164, 255),
            new Color(0, 148, 255),
            new Color(0, 132, 255),
            new Color(0, 116, 255),
            new Color(0, 100, 255),
            new Color(0, 84, 255),
            new Color(0, 68, 255),
            new Color(0, 50, 255),
            new Color(0, 34, 255),
            new Color(0, 18, 255),
            new Color(0, 2, 255),
            new Color(0, 0, 255),
            new Color(10, 0, 255),
            new Color(20, 0, 255),
            new Color(30, 0, 255),
            new Color(40, 0, 255),
            new Color(50, 0, 255),
            new Color(60, 0, 255),
            new Color(70, 0, 255),
            new Color(80, 0, 255),
            new Color(90, 0, 255),
            new Color(100, 0, 255),
            new Color(110, 0, 255),
            new Color(120, 0, 255),
            new Color(130, 0, 255),
            new Color(140, 0, 255),
            new Color(150, 0, 255),
            new Color(160, 0, 255)
    };


    ParToColorConverter() {
        for (int i = 0; i < temps.length; i++) {
            temps[i] = TEMP_MIN + i;
        }
    }


    Color convert(double parValue, String par) {
//        if (Math.abs(temp - (int)temp) < 0.01)
//            return Color.BLACK;
        if (par.equals("Humidity"))
            parValue = 40 - parValue/2;

        for (int i = 0; i < temps.length; i++) {
            Color low, high, realColor;
            int lowR, lowG, lowB, highR, highG, highB, R, G, B;
            if ((int) parValue == temps[i]) {
                low = colors[SCALE - i];
                if (SCALE - i - 1 >= 0)
                    high = colors[SCALE - i - 1];
                else
                    high = low;
                lowR = (low.getRGB() & 0x00ff0000) >> 16;
                highR = (high.getRGB() & 0x00ff0000) >> 16;
                lowG = (low.getRGB() & 0x0000ff00) >> 8;
                highG = (high.getRGB() & 0x0000ff00) >> 8;
                lowB = low.getRGB() & 0x000000ff;
                highB = high.getRGB() & 0x000000ff;
                R = (int) (lowR + (highR - lowR) * (parValue - (int) parValue));
                G = (int) (lowG + (highG - lowG) * (parValue - (int) parValue));
                B = (int) (lowB + (highB - lowB) * (parValue - (int) parValue));
                realColor = new Color(R, G, B);
                return realColor;
            }
        }
        return Color.WHITE;
    }

}