import dao.Sql2oFoodDAO;
import dao.Sql2oUserDAO;
import models.User;
import org.json.JSONArray;
import org.json.JSONString;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import kong.unirest.Unirest;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/nutrisi";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        Sql2oFoodDAO foodDAO = new Sql2oFoodDAO(sql2o);
        Sql2oUserDAO userDAO = new Sql2oUserDAO(sql2o);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String session = request.session().attribute("username");
            model.put("username",session);
            model.put("templates", "templates/index.vtl");

            String joke = Unirest.get("https://api.spoonacular.com/food/jokes/random?apiKey=5d9050c0cf864963aad55a329d51e429")
                    .asString()
                    .getBody();
            JSONObject jokeJson = new JSONObject(joke);
            String jokeText = jokeJson.getString("text");

            String trivia = Unirest.get( "https://api.spoonacular.com/food/trivia/?apiKey=5d9050c0cf864963aad55a329d51e429")
                    .asString()
                    .getBody();
            JSONObject triviaJson = new JSONObject(trivia);
            String triviaText = jokeJson.getString("text");
            System.out.println(triviaText);
            model.put("joke", jokeText);
            model.put("trivia", triviaText);

            String recipe = Unirest.get( "https://api.spoonacular.com/recipes/random?apiKey=5d9050c0cf864963aad55a329d51e429")
                    .asString()
                    .getBody();
            JSONObject recipeJson = new JSONObject(recipe);
            JSONObject recipeBody = recipeJson.getJSONArray("recipes")
                    .getJSONObject(0 );
            String recipeTitle = recipeBody.getString("title");
            JSONArray steps = recipeBody.getJSONArray("analyzedInstructions")
                    .getJSONObject(0)
                    .getJSONArray("steps");
            String imageurl = recipeBody.getString("image");
            ArrayList<String> array = new ArrayList();

            for (int i = 0; i < steps.length(); i++) {
                String step = steps.getJSONObject(i)
                        .getString("step");
                array.add(step);
            }

            System.out.println(array);
            System.out.println(imageurl);
            model.put("joke", jokeText);
            model.put("trivia", triviaText);
            model.put("recipe", recipeTitle);
            model.put("steps",array);
            model.put("image", imageurl);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            model.put("name", name);
            model.put("templates", "templates/searchresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/searchresults", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            model.put("name", name);
            model.put("foodDAO", foodDAO);
            model.put("templates", "templates/searchresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/login",(request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String error = request.session().attribute("errormessage");
            model.put("errormessage",error);
            request.session().removeAttribute("errormessage");
            model.put("templates", "templates/login.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
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
            model.put("templates", "templates/signup.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
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
            model.put("templates", "templates/bye.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
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
            model.put("templates", "templates/tagresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/categoryresults", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           String category = request.queryParams("category");
           model.put("category", category);
           model.put("foodDAO", foodDAO);
           model.put("templates", "templates/categoryresults.vtl");
           return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/calorierangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String calorierange = request.queryParams("calorierange");
            model.put("calorierange", calorierange);
            model.put("foodDAO", foodDAO);
            model.put("templates", "templates/calorierangeresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/proteinrangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String proteinrange = request.queryParams("proteinrange");
            model.put("proteinrange", proteinrange);
            model.put("foodDAO", foodDAO);
            model.put("templates", "templates/proteinrangeresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/carbrangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String carbrange = request.queryParams("carbrange");
            model.put("carbrange", carbrange);
            model.put("foodDAO", foodDAO);
            model.put("templates", "templates/carbrangeresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/fatrangeresults", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String fatrange = request.queryParams("fatrange");
            model.put("fatrange", fatrange);
            model.put("foodDAO", foodDAO);
            model.put("templates", "templates/fatrangeresults.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/presentation", (request,response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/presentation.vtl");
        }, new VelocityTemplateEngine());
    }
}
