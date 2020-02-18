package dao;

import model.Sighting;

import java.util.List;

public interface SightingDao {
    List<Sighting> getAll();
    void add(Sighting sighting);

//     model.Sighting findById(int id);
//     void update(int id, String name, String location, String rangerName);
//     void deleteById(int id);
//     void clearAllTasks();
}