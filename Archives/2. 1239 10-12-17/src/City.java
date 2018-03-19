public class City {
    private int id;
    private String name;
    private int[] hour = new int[App.HOURS];
    private int[] temperature = new int[App.HOURS];
    private int[] feelTemperature = new int[App.HOURS];
    private String[] windDir = new String[App.HOURS];
    private int[] windSpeed = new int[App.HOURS];
    private int[] clouds = new int[App.HOURS];
    private double[] rain = new double[App.HOURS];
    private int[] humidity = new int[App.HOURS];
    private double longitude;
    private double latitude;


    //get/set////////////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour(int index) {
        return hour[index];
    }

    public void setHour(int hour, int index) {
        this.hour[index] = hour;
    }

    public int getTemperatue(int index) {
        return temperature[index];
    }

    public void setTemperatue(int temperature, int index) {
        this.temperature[index] = temperature;
    }

    public int getFeelTemperatue(int index) {
        return feelTemperature[index];
    }

    public void setFeelTemperatue(int temperature, int index) {
        this.feelTemperature[index] = temperature;
    }

    public String getWindDir(int index) {
        return windDir[index];
    }

    public void setWindDir(String windDir, int index) {
        this.windDir[index] = windDir;
    }

    public int getWindSpeed(int index) {
        return windSpeed[index];
    }

    public void setWindSpeed(int windSpeed, int index) {
        this.windSpeed[index] = windSpeed;
    }

    public int getClouds(int index) {
        return clouds[index];
    }

    public void setClouds(int clouds, int index) {
        this.clouds[index] = clouds;
    }

    public double getRain(int index) {
        return rain[index];
    }

    public void setRain(double rain, int index) {
        this.rain[index] = rain;
    }

    public int getHumidity(int index) {
        return humidity[index];
    }

    public void setHumidity(int humidity, int index) {
        this.humidity[index] = humidity;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
