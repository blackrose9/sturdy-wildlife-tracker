package dao;

import database.Database;
import model.Sighting;
import org.sql2o.*;


import java.util.List;

public class Sql2oSightingDao implements SightingDao {

    private final Sql2o sql2o;
    public Sql2oSightingDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Sighting> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }
    @Override
    public void add(Sighting sighting) {
        String sql = "INSERT INTO sightings (animalname, location, rangername) VALUES (:name, :location, :ranger)";
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
