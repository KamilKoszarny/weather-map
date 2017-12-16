import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CoordinatesImporter {

    public void importCoordinates(City[] cities) throws IOException {
        for (int i = 0; i < cities.length; i++) {
            importCityCoordinates(cities[i]);
        }
    }

    private void importCityCoordinates(City city) throws IOException {

        URL url = getURLAddress(city);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String cityName = setCityName(city);

        do {
            line = bufferedReader.readLine();
        } while (!line.contains(cityName));

        writeCoordinates(city, line);
    }

    private URL getURLAddress(City city){
        String firstLetter = String.valueOf(city.getName().charAt(0));
        firstLetter = firstLetter.toLowerCase();
        if (firstLetter.equals("ć"))
            firstLetter = "cc";
        if (firstLetter.equals("ł"))
            firstLetter = "ll";
        if (firstLetter.equals("ś"))
            firstLetter = "ss";
        if (firstLetter.equals("ź"))
            firstLetter = "zz";
        if (firstLetter.equals("ż"))
            firstLetter = "zzz";

        try {
            URL url = new URL("http://cybermoon.pl/wiedza/wspolrzedne/wspolrzedne_polskich_miejscowosci_" + firstLetter + ".html");
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String setCityName(City city){
        String cityName = city.getName().replace('ą', '�');
        cityName = cityName.replace('ć', '�');
        cityName = cityName.replace('ę', '�');
        cityName = cityName.replace('ł', '�');
        cityName = cityName.replace('ń', '�');
        cityName = cityName.replace('ó', '�');
        cityName = cityName.replace('ś', '�');
        cityName = cityName.replace('ź', '�');
        cityName = cityName.replace('ż', '�');
        return cityName;
    }

    private void writeCoordinates(City city, String line){
        String longitude = line.substring(line.indexOf("&deg;N") - 8, line.indexOf("&deg;N") - 1);
        city.setLongitude(Double.parseDouble(longitude));

        String latitude = line.substring(line.indexOf("&deg;E") - 8, line.indexOf("&deg;E") - 1);
        city.setLatitude(Double.parseDouble(latitude));
    }
}
