import dao.Sql2oSightingDao;
import model.Sighting;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("public");
        String connectionString = "jdbc:postgresql://localhost:5432/tracker";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "pg");
        Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);

        //instantiate some Animals to populate form
        Animal animal = new Animal("Endangered", "Dodo", "26", "healthy");
        Animal animal2 = new Animal("Endangered", "White Rhino", "59", "okay");
        Animal animal3 = new Animal("Normal", "Chicken", "1", "healthy");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/view_sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            return new ModelAndView(model, "view-sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/record_sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Animal> animals = Animal.getAll();
            model.put("animals", animals);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/record_sighting", (request, response) -> {
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");

            Sighting sighting = new Sighting(name, location, ranger);
            sightingDao.add(sighting);
            response.redirect("/");

            return null;
        }, new HandlebarsTemplateEngine());

        get("/record_animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/record_animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String type = request.queryParams("type");
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");

            Animal newAnimal = new Animal(type, name, age, health);

            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
