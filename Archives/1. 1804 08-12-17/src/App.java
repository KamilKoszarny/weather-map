import java.io.IOException;

public class App {

    private static final int CITIES_NUMBER = 16;

    public static void main(String[] args) throws IOException {
        City[] cities = new City[CITIES_NUMBER];

        DataImporter dataImporter = new DataImporter();
        dataImporter.importData(cities);


        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i].getName());
            System.out.println(cities[i].getTemperatue());
        }
    }
}
