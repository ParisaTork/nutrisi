import dao.Sql2oFoodDAO;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.json.JSONObject;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import kong.unirest.Unirest;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(9000);

        String connectionString = "jdbc:postgresql://localhost:5432/nutrisi";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        Sql2oFoodDAO foodDAO = new Sql2oFoodDAO(sql2o);

        get("/", (request, response)  -> {
            Map<String, Object> model = new HashMap<>();
            String joke = Unirest.get("https://api.spoonacular.com/food/jokes/random?apiKey=5d9050c0cf864963aad55a329d51e429")
                    .asString()
                    .getBody();
            JSONObject jokeJson = new JSONObject(joke);
            String jokeText = jokeJson.getString("text");
            model.put("joke", jokeText);
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

    }
}
