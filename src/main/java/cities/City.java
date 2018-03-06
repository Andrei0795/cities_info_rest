package cities;

public class City {

    private final long id;
    private final int cityId;
    private final String name;
    private final String country;
    private final int population;
    private final double lat;
    private final double lon;

    public City(long id, int cityId, String name, String country, int population, double lat, double lon) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.country = country;
        this.population = population;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public int getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
