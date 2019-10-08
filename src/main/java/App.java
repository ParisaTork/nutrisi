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

        get("/advancedsearch", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/advancedsearch.vtl");
        }, new VelocityTemplateEngine());

        get("/tagresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String tag = request.queryParams("tag");
            model.put("tag", tag);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/tagresults.vtl");
        }, new VelocityTemplateEngine());

        get("/categoryresults", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           String category = request.queryParams("category");
           model.put("category", category);
           model.put("foodDAO", foodDAO);
           return new ModelAndView(model, "templates/categoryresults.vtl");
        }, new VelocityTemplateEngine());

        get("/calorierangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String calorierange = request.queryParams("calorierange");
            model.put("calorierange", calorierange);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/calorierangeresults.vtl");
        }, new VelocityTemplateEngine());

        get("/proteinrangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String proteinrange = request.queryParams("proteinrange");
            model.put("proteinrange", proteinrange);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/proteinrangeresults.vtl");
        }, new VelocityTemplateEngine());

        get("/carbrangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String carbrange = request.queryParams("carbrange");
            model.put("carbrange", carbrange);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/carbrangeresults.vtl");
        }, new VelocityTemplateEngine());

        get("/fatrangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String fatrange = request.queryParams("fatrange");
            model.put("fatrange", fatrange);
            model.put("foodDAO", foodDAO);
            return new ModelAndView(model, "templates/fatrangeresults.vtl");
        }, new VelocityTemplateEngine());

    }
}
