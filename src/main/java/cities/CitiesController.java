package cities;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
public class CitiesController {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping("/city")
    public City city(@RequestParam(value="cityName", defaultValue="City not entered or not found!") String name) {
        
        City city = getCity(counter.incrementAndGet(), name);
        if (city != null) {
            System.out.println("OK");
            return city;
        } else {
            name = "City not entered or not found!";
        }
        return new City(counter.incrementAndGet(), 0, name, "", 0, 0.0, 0.0);
    }
    
    private synchronized static City getCity(long counter, String cityName) {
        return connectToDB(counter, cityName, 0);
    }
    
    private synchronized static City getCity(long counter, int cityId) {
        return connectToDB(counter, "", cityId);
    }
    
    private synchronized static City connectToDB(long counter, String cityName, int cityId) {
        System.out.println("ENTERED");
        
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/cities/CitiesDatabase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select * from `CITIES` where NAME = '%s' LIMIT 1;", cityName));
            System.out.println(String.format("select * from `CITIES` where NAME = '%s' LIMIT 1;", cityName));
            
            while (rs.next()) {
                int id = rs.getInt("CITYID");
                String name = rs.getString("NAME");
                String country = rs.getString("COUNTRY");
                int population = rs.getInt("POPULATION");
                double lat = rs.getDouble("LAT");
                double lon = rs.getDouble("LON");
                System.out.println(String.format("%s", name));
                
                rs.close();
                stmt.close();
                c.close();
                return new City(counter, id, name, country, population, lat, lon);
            }
            return null;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            return null;
        }
    }
}

