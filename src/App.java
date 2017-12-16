import java.io.IOException;

public class App {

    private static final int CITIES_NUMBER = 3;
    public static final int HOURS = 71;

    public static void main(String[] args) throws IOException {
        City[] cities = new City[CITIES_NUMBER];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new City();
        }

        MapFrame mapFrame = new MapFrame();

        WeatherImporter dataImporter = new WeatherImporter();
        dataImporter.importData(cities);

        CoordinatesImporter coordinatesImporter = new CoordinatesImporter();
        coordinatesImporter.importCoordinates(cities);

        showData(cities);


    }

    private static void showData(City[] cities){
        for (int i = 0; i < cities.length; i++) {
            System.out.print(cities[i].getName());
            System.out.println("\tN: " + cities[i].getLongitude() + "\tE: " + cities[i].getLatitude());
            System.out.print("\tgodz: "+ cities[i].getHour(0));
            System.out.print("\ttemp: "+ cities[i].getTemperatue(0));
            System.out.print("\tftemp: "+ cities[i].getFeelTemperatue(0));
            System.out.print("\twindDir: "+ cities[i].getWindDir(0));
            System.out.print("\twindSpeed: "+ cities[i].getWindSpeed(0));
            System.out.print("\tclouds: "+ cities[i].getClouds(0));
            System.out.print("\train: "+ cities[i].getRain(0));
            System.out.println("\thum: "+ cities[i].getHumidity(0));
        }
    }
}
