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
        String connectionString = "jdbc:h2:~/tracker.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);
        Map<String, Object> model = new HashMap<String, Object>();

        get("/", (request, response) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/view_sightings", (request, response) -> {
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            return new ModelAndView(model, "view-sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/record_sighting/new", (request, response) -> {
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/record_sighting", (request, response) -> {
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");

            Sighting sighting = new Sighting(name, location, ranger);
            sightingDao.add(sighting);
            response.redirect("/");

            return null;
        }, new HandlebarsTemplateEngine());

//        post("/record_animal", (request, response) -> {
//
//        }, new HandlebarsTemplateEngine());
    }
}
