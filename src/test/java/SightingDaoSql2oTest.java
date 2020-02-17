import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;

public class SightingDaoSql2oTest {
    private Connection conn;
    private Sql2oSightingDao sightingDao;

    public Sighting setUpNewTask(){
        return new Sighting("Lion", "by the big tree", "A-star Ranger");
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        sightingDao = new Sql2oSightingDao(sql2o); //ignore me for now
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingSightingSetsNewId() throws Exception {
        Sighting sighting = setUpNewTask();
        int ogSightingId = sighting.getId();
    }
}