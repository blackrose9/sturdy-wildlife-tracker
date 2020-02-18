package model;

import java.util.Objects;

public class Sighting {
    private int id;
    private String animalName;
    private String location;
    private String rangerName;

    public Sighting(String name, String location, String ranger) {
        this.animalName = name;
        this.location = location;
        this.rangerName = ranger;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                Objects.equals(animalName, sighting.animalName) &&
                Objects.equals(location, sighting.location) &&
                Objects.equals(rangerName, sighting.rangerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalName, location, rangerName);
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }
}
