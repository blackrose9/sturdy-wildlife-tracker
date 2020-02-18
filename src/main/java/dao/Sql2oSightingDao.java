import database.Database;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSightingDao implements SightingDao{
    public Database dbConn;
    private final Sql2o sql2o;
    public Sql2oSightingDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public ArrayList<Sighting> getAll() {
        try (Connection conn = sql2o.open()) {
            return (ArrayList<Sighting>) conn.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }
    @Override
    public void add(Sighting sighting) {
        String sql = "INSERT INTO sightings (animalName, location, rangerName) VALUES (:name, :location, :ranger)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(sighting)
                    .executeUpdate()
                    .getKey();
            sighting.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
