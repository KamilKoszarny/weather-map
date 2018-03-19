import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherImporter {

    private final int[] CITIES_ID_LIST = new int[16];

    WeatherImporter() {
        CITIES_ID_LIST[0] = 1197;
        CITIES_ID_LIST[1] = 3696;
        CITIES_ID_LIST[2] = 4970;
        CITIES_ID_LIST[3] = 8048;
        CITIES_ID_LIST[4] = 13095;
        CITIES_ID_LIST[5] = 13378;
        CITIES_ID_LIST[6] = 19059;
        CITIES_ID_LIST[7] = 19393;
        CITIES_ID_LIST[8] = 24210;
        CITIES_ID_LIST[9] = 24308;
        CITIES_ID_LIST[10] = 27457;
        CITIES_ID_LIST[11] = 30389;
        CITIES_ID_LIST[12] = 34668;
        CITIES_ID_LIST[13] = 36917;
        CITIES_ID_LIST[14] = 39240;
        CITIES_ID_LIST[15] = 41517;
    }

    public void importData(City[] cities){
        for (int i = 0; i < cities.length; i++) {
            importCityData(cities[i], i);
        }
    }

    private void importCityData(City city, int id) {
        URL url;
        InputStream inputStream;
        try {
            url = new URL("https://pogoda.interia.pl/prognoza-szczegolowa-xxx,cId," + CITIES_ID_LIST[id]);
            URLConnection connection = url.openConnection();
            inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            do {
                line = bufferedReader.readLine();
            } while (!line.contains("</h3>"));

            writeCityName(city, line);

            for (int hour = 0; hour < App.HOURS; hour++) {
                //hours
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("class=\"hour\""));
                writeCityHour(city, line, hour);

                //temp
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("forecast-temp"));
                writeCityTemperature(city, line, hour);

                //ftemp
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("forecast-feeltemp"));
                writeCityFeelTemperature(city, line, hour);

                //windDir
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("wind-direction"));
                writeCityWindDir(city, line, hour);

                //windSpeed
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("speed-value"));
                writeCityWindSpeed(city, line, hour);

                //clouds
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("value cloud-cover"));
                writeCityClouds(city, line, hour);

                //rain
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("value rain"));
                writeCityRain(city, line, hour);

                //snow
                do {
                    line = bufferedReader.readLine();
//                    System.out.println(line);
                } while (!(line.contains("value snow") || line.contains("entry-humidity")));
                writeCitySnow(city, line, hour);

                //humidity
                do {
                    line = bufferedReader.readLine();
                } while (!line.contains("humidity-wrap"));
                line = bufferedReader.readLine();
                writeCityHumidity(city, line, hour);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showOptionDialog(null, "Failed to download data from interia.pl"," ",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE, null, new Object[]{}, null);
        }
    }

    private void writeCityName(City city, String line) {
        line = line.substring(line.indexOf(">") + 1, line.indexOf("<"));
        city.setName(line.trim());
    }

    private void writeCityHour(City city, String line, int index) {
        line = line.substring(line.indexOf("hour") + 6, line.indexOf("</span"));
        city.setHour(Integer.parseInt(line), index);
    }

    private void writeCityTemperature(City city, String line, int index) {
        line = line.substring(line.indexOf("temp") + 6, line.indexOf("</span") - 2);
        city.setTemperatue(Integer.parseInt(line), index);
    }

    private void writeCityFeelTemperature(City city, String line, int index) {
        line = line.substring(line.indexOf("Odczuwalna") + 11, line.indexOf("</span") - 3);
        city.setFeelTemperatue(Integer.parseInt(line), index);
    }

    private void writeCityWindDir(City city, String line, int index) {
        line = line.substring(line.indexOf("direction") + 11, line.indexOf("</span"));
        city.setWindDir(line, index);
    }

    private void writeCityWindSpeed(City city, String line, int index) {
        line = line.substring(line.indexOf("value") + 7, line.indexOf("</span"));
        city.setWindSpeed(Integer.parseInt(line), index);
    }

    private void writeCityClouds(City city, String line, int index) {
        line = line.substring(line.indexOf("cover") + 7, line.indexOf("<span class=\"pre"));
        city.setClouds(Integer.parseInt(line), index);
    }

    private void writeCityRain(City city, String line, int index) {
        line = line.substring(line.indexOf("rain") + 6, line.indexOf("<span class=\"pre"));
        line = line.replace(',', '.');
        city.setRain(Double.parseDouble(line), index);
    }

    private void writeCitySnow(City city, String line, int index) {
        if (line.contains("snow")) {
            line = line.substring(line.indexOf("snow") + 6, line.indexOf("<span class=\"pre"));
            line = line.replace(',', '.');
            city.setSnow(Double.parseDouble(line), index);
        } else
            city.setSnow(0, index);
    }

    private void writeCityHumidity(City city, String line, int index) {
        line = line.trim();
        line = line.substring(0, line.indexOf('%'));
        city.setHumidity(Integer.parseInt(line), index);
    }
}
