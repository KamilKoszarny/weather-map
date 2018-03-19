public class City {
    private int id;
    private String name;
    private int temperatue;

    public City() {
        name = null;
        temperatue = 100;
    }


    //get/set////////////////////////////////////////////////////////////////////////////////////////////
    public int getTemperatue() {
        return temperatue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperatue(int temperatue) {
        this.temperatue = temperatue;
    }
}
