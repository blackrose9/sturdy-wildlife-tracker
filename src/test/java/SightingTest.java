import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SightingTest {
    public Sighting setUpNewTask(){
        return new Sighting("Lion", "by the big tree", "A-star Ranger");
    }

    @Test
    public void aSightingIsCorrectlyCreated() throws Exception {
        Sighting sighting = setUpNewTask();
    }

    @Test
    public void sightingInitiatedWithCorrectLocation() throws Exception {
        Sighting sighting = setUpNewTask();
        assertEquals("by the Big Tree", sighting.getLocation());
    }
}