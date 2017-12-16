import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DataImporter {
    public void importData(City[] cities) throws IOException {
        final String[] CITIES_LIST = new String[16];
        CITIES_LIST[0] = "weather_map_list_1197";
        CITIES_LIST[1] = "weather_map_list_3696";
        CITIES_LIST[2] = "weather_map_list_4970";
        CITIES_LIST[3] = "weather_map_list_8048";
        CITIES_LIST[4] = "weather_map_list_13095";
        CITIES_LIST[5] = "weather_map_list_13378";
        CITIES_LIST[6] = "weather_map_list_19059";
        CITIES_LIST[7] = "weather_map_list_19393";
        CITIES_LIST[8] = "weather_map_list_24210";
        CITIES_LIST[9] = "weather_map_list_24308";
        CITIES_LIST[10] = "weather_map_list_27457";
        CITIES_LIST[11] = "weather_map_list_30389";
        CITIES_LIST[12] = "weather_map_list_34668";
        CITIES_LIST[13] = "weather_map_list_36917";
        CITIES_LIST[14] = "weather_map_list_39240";
        CITIES_LIST[15] = "weather_map_list_41517";




        URL url = new URL("https://pogoda.interia.pl/polska");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;

        for (int i = 0; i < cities.length; i++) {
            cities[i] = new City();

            do {
                line = bufferedReader.readLine();
            } while (!line.contains(CITIES_LIST[i]));

            line = bufferedReader.readLine();
            writeCityName(cities[i], line);

            line = bufferedReader.readLine();
            writeCityTemperature(cities[i], line);
        }

    }

    private void writeCityName(City city, String line){
        city.setName(line.trim().substring(0, line.trim().indexOf('<')).trim());

    }

    private void writeCityTemperature(City city, String line) {
        city.setTemperatue(Integer.parseInt(line.trim().substring(line.trim().indexOf('>') + 1, line.trim().indexOf('°'))));
    }
}
