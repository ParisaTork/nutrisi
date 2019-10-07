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
        }, new VelocityTemplateEngine());

    }
}
