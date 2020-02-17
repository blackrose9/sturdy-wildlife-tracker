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
            List<Sighting> sightings = sightingDao.getAll();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
