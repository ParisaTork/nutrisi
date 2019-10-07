import dao.Sql2oFoodDAO;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost:5432/nutrisi";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        Sql2oFoodDAO foodDAO = new Sql2oFoodDAO(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());

        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            model.put("name", name);
            return new ModelAndView(model, "templates/searchresults.vtl");
        }, new VelocityTemplateEngine());

        get("/searchresults", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            model.put("name", name);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/searchresults.vtl");
        }, new VelocityTemplateEngine());

        get("/tagresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String tag = request.queryParams("tag");
            model.put("tag", tag);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/tagresults.vtl");
        }, new VelocityTemplateEngine());


    }
}
