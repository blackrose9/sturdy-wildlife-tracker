import dao.Sql2oSightingDao;
import model.Sighting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SightingDaoSql2oTest {
    private static Connection conn;
    private static Sql2oSightingDao sightingDao;

    public Sighting setUpNewTask(){
        return new Sighting("Lion", "by the big tree", "A-star Ranger");
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "pg");
        sightingDao = new Sql2oSightingDao(sql2o); //ignore me for now
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test

    public void ifNoSightingReturnsZero(){
        assertEquals(0, sightingDao.getAll().size());
    }
    @Test
    public void addingSightingSetsNewId() throws Exception {
        Sighting sighting = setUpNewTask();
        int ogSightingId = sighting.getId();
        sightingDao.add(sighting);
        assertNotEquals(ogSightingId, sighting.getId());
    }

    @Test
    public void addNewSightingToDatabase() {
        Sighting sighting = setUpNewTask();
        sightingDao.add(sighting);
        assertEquals(1, sightingDao.getAll().size());
    }
}