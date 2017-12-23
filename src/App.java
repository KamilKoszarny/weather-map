import javax.swing.*;

public class App {

    private static final int CITIES_NUMBER = 5;
    public static final int HOURS = 71;

    public static void main(String[] args){
        City[] cities = new City[CITIES_NUMBER];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new City();
        }

//        JOptionPane.showOptionDialog(null, "Downloading data..."," ",
//                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);

        System.out.println("start download");

        WeatherImporter weatherImporter = new WeatherImporter();
        weatherImporter.importData(cities);

        System.out.println("weather");

        CoordinatesImporter coordinatesImporter = new CoordinatesImporter();
        coordinatesImporter.importCoordinates(cities);

        System.out.println("coordinates");

        showData(cities);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App.operate();
                MapFrame mapFrame = new MapFrame(cities);
            }
        });
    }

    private static void operate(){

    }

    private static void showData(City[] cities){
        for (City city : cities) {
            System.out.print(city.getName());
            System.out.println("\tN: " + city.getLongitude() + "\tE: " + city.getLatitude());
            System.out.print("\tgodz: " + city.getHour(0));
            System.out.print("\ttemp: " + city.getTemperatue(0));
            System.out.print("\tftemp: " + city.getFeelTemperatue(0));
            System.out.print("\twindDir: " + city.getWindDir(0));
            System.out.print("\twindSpeed: " + city.getWindSpeed(0));
            System.out.print("\tclouds: " + city.getClouds(0));
            System.out.print("\train: " + city.getRain(0));
            System.out.print("\tsnow: " + city.getSnow(0));
            System.out.println("\thum: " + city.getHumidity(0));
        }
    }
}
