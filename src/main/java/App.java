import dao.Sql2oFoodDAO;
import dao.Sql2oUserDAO;
import models.User;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(9000);
        String connectionString = "jdbc:postgresql://localhost:5432/nutrisi";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        Sql2oFoodDAO foodDAO = new Sql2oFoodDAO(sql2o);
        Sql2oUserDAO userDAO = new Sql2oUserDAO(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String session = request.session().attribute("username");
            model.put("username",session);
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());

        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
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

        get("/login",(request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String error = request.session().attribute("errormessage");
            model.put("errormessage",error);
            request.session().removeAttribute("errormessage");
            return new ModelAndView(model, "templates/login.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String email = request.queryParams("email");
            String password = request.queryParams("password");
            User user = userDAO.authenticate(email, password);
            if( user ==null ) {
                String error = userDAO.getError();
                request.session().attribute("errormessage",error);
                response.redirect("/login");

            } else {
                String username = user.getName();
                request.session().attribute("username", username);

                response.redirect("/");
            }
//            model.put("user", user);

            return null;
        });

        get("/signup", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/signup.vtl");
        }, new VelocityTemplateEngine());

        post("/signup", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String password = request.queryParams("password");
            User user = userDAO.create(name, email, password);
            response.redirect("/login");
            return null;
        });

        get("/logout", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            request.session().removeAttribute("username");
            return new ModelAndView(model, "templates/bye.vtl");

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
